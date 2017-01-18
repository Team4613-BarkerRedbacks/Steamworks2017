package redbacks.robot.pid;

import edu.wpi.first.wpilibj.PIDController.Tolerance;
import edu.wpi.first.wpilibj.*;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.motors.CtrlDrive;
import redbacks.robot.Robot;
import redbacks.robot.pid.Tolerances.Percentage;

public class AcPIDControl extends Action
{
	double target, minIn, maxIn, minOut, maxOut;
	boolean isDriveMotor = false, isContinuous, shouldFinish;

	Tolerances tolerance;
	PIDSource input;
	PIDSourceType type;
	PIDController[] controllers;

	public AcPIDControl(double p, double i, double d, double target, Tolerances tolerance, PIDSource input, PIDOutput... outputs) {
		this(new ChFalse(), true, p, i, d, target, tolerance, input, false, 0, 0, PIDSourceType.kDisplacement, -1, 1, outputs);
	}

	public AcPIDControl(double p, double i, double d, double target, Tolerances tolerance, PIDSource input, boolean isContinuous, double minIn, double maxIn, PIDOutput... outputs) {
		this(new ChFalse(), true, p, i, d, target, tolerance, input, isContinuous, minIn, maxIn, PIDSourceType.kDisplacement, -1, 1, outputs);
	}

	public AcPIDControl(Check check, boolean shouldFinish, double p, double i, double d, double target, Tolerances tolerance, PIDSource input, boolean isContinuous, double minIn, double maxIn, PIDSourceType type, double minOut, double maxOut, PIDOutput... outputs) {
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
			controllers[idx] = new PIDController(p, i, d, input, outputs[idx]);
			if(outputs[idx] instanceof PIDMotor && ((PIDMotor) outputs[idx]).motor instanceof CtrlDrive) isDriveMotor = true;
		}
	}

	public void onStart() {
		input.setPIDSourceType(type);
		if(isDriveMotor) Robot.isIndivDriveControl = true;

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
			controller.free();
		if(isDriveMotor) Robot.isIndivDriveControl = false;
	}

	public boolean isDone() {
		return shouldFinish ? (controllers[0].onTarget() ? true : false) : false;
	}
}
