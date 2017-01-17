package redbacks.robot.pid;

import edu.wpi.first.wpilibj.PIDController.Tolerance;
import edu.wpi.first.wpilibj.*;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.Check;
import redbacks.robot.Robot;

public class AcPIDControl extends Action
{
	double target, min, max;
	boolean isDriveMotor = false, isContinuous;

	Tolerance tolerance;
	PIDSource input;
	PIDSourceType type;
	PIDController[] controllers;

	public AcPIDControl(double p, double i, double d, double target, Tolerance tolerance, PIDSource input, PIDOutput... outputs) {
		this(new ChFalse(), p, i, d, target, tolerance, input, false, -1, 1, PIDSourceType.kDisplacement, outputs);
	}
	
	public AcPIDControl(Check check, double p, double i, double d, double target, Tolerance tolerance, PIDSource input, boolean isContinuous, double min, double max, PIDSourceType type, PIDOutput... outputs) {
		super(check);
		this.target = target;
		this.tolerance = tolerance;
		this.input = input;
		this.isContinuous = isContinuous;
		this.min = min;
		this.max = max;
		this.type = type;
		controllers = new PIDController[outputs.length];
		for(int idx = 0; idx < controllers.length; idx++) controllers[idx] = new PIDController(p, i, d, input, outputs[idx]);
	}

	public void onStart() {
		input.setPIDSourceType(type);
		if(isDriveMotor) Robot.isIndivDriveControl = true;

		for(PIDController controller : controllers) {
			controller.setContinuous(isContinuous);
			controller.setOutputRange(min, max);
			controller.setTolerance(tolerance);
			controller.setToleranceBuffer(15);
			controller.setSetpoint(target);
			controller.enable();
		}
	}

	public void onFinish() {
		for(PIDController controller : controllers) controller.free();
		if(isDriveMotor) Robot.isIndivDriveControl = false;
	}

	public boolean isDone() {
		return controllers[0].onTarget() ? true : false;
	}
}
