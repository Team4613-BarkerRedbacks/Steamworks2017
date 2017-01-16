package redbacks.robot.actions;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.navx.NavX;
import redbacks.robot.Robot;

/**
 * @author Sean Zammit
 */
public class AcDriveStraight extends Action
{
	double sp;
	final double cor = 0.02D;
	
	/**
	 * @param check The condition that will finish the action.
	 */
	public AcDriveStraight(Check check, double speed) {
		super(check);
		sp = speed;
	}

	public void onRun() {
		tankDrive(-sp + NavX.getYaw() * cor, -sp - NavX.getYaw() * cor);
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
