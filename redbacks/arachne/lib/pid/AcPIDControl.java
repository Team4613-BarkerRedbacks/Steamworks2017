package redbacks.arachne.lib.pid;

import edu.wpi.first.wpilibj.*;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.pid.Tolerances.Percentage;

/**
 * An action for a {@link PIDController}. With a set of PID parameters, it will output to any number of valid {@link PIDOutput PIDOutputs}.
 *
 * @author Sean Zammit
 */
public class AcPIDControl extends Action
{
	double target, minIn, maxIn, minOut, maxOut;
	boolean isContinuous, shouldFinish;

	Tolerances tolerance;
	PIDSource input;
	PIDSourceType type;
	PIDController[] controllers;

	/**
	 * Constructor for an action for a {@link PIDController}. It will end once it reaches its target.
	 * 
	 * @param p P multiplier.
	 * @param i I multiplier.
	 * @param d D multiplier.
	 * @param target The target value for the PIDController.
	 * @param tolerance The tolerance on the target value, used to determine whether the PIDController is at its target.
	 * @param input The sensor that the PIDController is using to determine its values.
	 * @param outputs Any {@link PIDOutput PIDOutputs} to be set by the PIDController.
	 */
	public AcPIDControl(double p, double i, double d, double target, Tolerances tolerance, PIDSource input, PIDOutput... outputs) {
		this(new ChFalse(), true, p, i, d, 0, target, tolerance, input, false, 0, 0, PIDSourceType.kDisplacement, -1, 1, outputs);
	}

	/**
	 * Constructor for an action for a {@link PIDController}. It will end once it reaches its target.
	 * 
	 * @param p P multiplier.
	 * @param i I multiplier.
	 * @param d D multiplier.
	 * @param target The target value for the PIDController.
	 * @param tolerance The tolerance on the target value, used to determine whether the PIDController is at its target.
	 * @param input The sensor that the PIDController is using to determine its values.
	 * @param isContinuous Whether the sensor wraps around at a certain point, such as a gyro going from 180 to -180.
	 * @param minIn The minimum value from the sensor. Defaults to 0 - this will be ignored.
	 * @param maxIn The maximum value from the sensor. Defaults to 0 - this will be ignored.
	 * @param outputs Any {@link PIDOutput PIDOutputs} to be set by the PIDController.
	 */
	public AcPIDControl(double p, double i, double d, double target, Tolerances tolerance, PIDSource input, boolean isContinuous, double minIn, double maxIn, PIDOutput... outputs) {
		this(new ChFalse(), true, p, i, d, 0, target, tolerance, input, isContinuous, minIn, maxIn, PIDSourceType.kDisplacement, -1, 1, outputs);
	}

	/**
	 * Constructor for an action for a {@link PIDController}. It can be set to end once it reaches its target, or when a check is true.
	 * 
	 * @param check A check that will determine whether the PIDController should stop.
	 * @param shouldFinish Whether the action should finish once the PIDController is on target.
	 * @param p P multiplier.
	 * @param i I multiplier.
	 * @param d D multiplier.
	 * @param target The target value for the PIDController.
	 * @param tolerance The tolerance on the target value, used to determine whether the PIDController is at its target.
	 * @param input The sensor that the PIDController is using to determine its values.
	 * @param isContinuous Whether the sensor wraps around at a certain point, such as a gyro going from 180 to -180.
	 * @param minIn The minimum value from the sensor. Defaults to 0 - this will be ignored.
	 * @param maxIn The maximum value from the sensor. Defaults to 0 - this will be ignored.
	 * @param type The {@link PIDSourceType} for the sensor. This can be either rate or displacement.
	 * @param minOut The minimum output to the {@link PIDOutput PIDOutputs}.
	 * @param maxOut The maximum output to the PIDOutputs.
	 * @param outputs AnyPIDOutputs to be set by the PIDController.
	 */
	public AcPIDControl(Check check, boolean shouldFinish, double p, double i, double d, double target, Tolerances tolerance, PIDSource input, boolean isContinuous, double minIn, double maxIn, PIDSourceType type, double minOut, double maxOut, PIDOutput... outputs) {
		this(check, shouldFinish, p, i, d, 0, target, tolerance, input, isContinuous, minIn, maxIn, type, minOut, maxOut, outputs);
	}

	/**
	 * Constructor for an action for a {@link PIDController}. It can be set to end once it reaches its target, or when a check is true.
	 * 
	 * @param check A check that will determine whether the PIDController should stop.
	 * @param shouldFinish Whether the action should finish once the PIDController is on target.
	 * @param p P multiplier.
	 * @param i I multiplier.
	 * @param d D multiplier.
	 * @param f F multiplier.
	 * @param target The target value for the PIDController.
	 * @param tolerance The tolerance on the target value, used to determine whether the PIDController is at its target.
	 * @param input The sensor that the PIDController is using to determine its values.
	 * @param isContinuous Whether the sensor wraps around at a certain point, such as a gyro going from 180 to -180.
	 * @param minIn The minimum value from the sensor. Defaults to 0 - this will be ignored.
	 * @param maxIn The maximum value from the sensor. Defaults to 0 - this will be ignored.
	 * @param type The {@link PIDSourceType} for the sensor. This can be either rate or displacement.
	 * @param minOut The minimum output to the {@link PIDOutput PIDOutputs}.
	 * @param maxOut The maximum output to the PIDOutputs.
	 * @param outputs AnyPIDOutputs to be set by the PIDController.
	 */
	public AcPIDControl(Check check, boolean shouldFinish, double p, double i, double d, double f, double target, Tolerances tolerance, PIDSource input, boolean isContinuous, double minIn, double maxIn, PIDSourceType type, double minOut, double maxOut, PIDOutput... outputs) {
		super(check);
		this.shouldFinish = shouldFinish;
		this.target = target;
		this.tolerance = tolerance;
		this.input = input;
		this.isContinuous = isContinuous;
		this.minIn = minIn;
		this.maxIn = maxIn;
		this.type = type;
		this.minOut = minOut;
		this.maxOut = maxOut;
		controllers = new PIDController[outputs.length];
		for(int idx = 0; idx < controllers.length; idx++) {
			if(outputs[idx] instanceof PIDMotor) ((PIDMotor) outputs[idx]).setAction(this);
			controllers[idx] = new PIDController(p, i, d, f, input, outputs[idx]);
		}
	}

	public void onStart() {
		input.setPIDSourceType(type);

		for(PIDController controller : controllers) {
			controller.setContinuous(isContinuous);
			controller.setInputRange(minIn, maxIn);
			controller.setOutputRange(minOut, maxOut);
			if(tolerance instanceof Percentage) controller.setPercentTolerance(tolerance.value);
			else controller.setAbsoluteTolerance(tolerance.value);
			controller.setToleranceBuffer(15);
			controller.setSetpoint(target);
			controller.enable();
		}
	}

	public void onFinish() {
		for(PIDController controller : controllers)
			controller.disable();
	}

	public boolean isDone() {
		return shouldFinish ? (controllers[0].onTarget() ? true : false) : false;
	}
}
