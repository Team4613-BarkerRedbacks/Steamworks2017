package redbacks.robot.actions;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.Check;
import redbacks.robot.Robot;

/**
 * @author Sean Zammit
 */
public class AcDriveDirection extends Action
{
	double sp, target;
	final double cor = 0.02D;
	
	/**
	 * @param check The condition that will finish the action.
	 */
	public AcDriveDirection(Check check, double speed, double target) {
		super(check);
		sp = speed;
		this.target = target;
	}

	public void onRun() {
		tankDrive(-sp + (Robot.sensors.yaw.get() - target) * cor, -sp - (Robot.sensors.yaw.get() - target) * cor);
	}
	
	public void onStart() {
		Robot.isIndivDriveControl = false;
	}

	/**
	 * Tank drive method of control. Calculate adjustments to the inputs in this method.
	 * 
	 * @param l Left wheel speed.
	 * @param r Right wheel speed.
	 */
	public void tankDrive(double l, double r) {
		Robot.driver.drivetrain.tankDrive(l, r);
	}
}
