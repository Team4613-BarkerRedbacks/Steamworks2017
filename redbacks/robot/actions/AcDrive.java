package redbacks.robot.actions;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.robot.OI;
import redbacks.robot.Robot;

public class AcDrive extends Action
{
	public AcDrive() {
		super(new ChFalse());
	}
	
	double minR = 0.4D, difR = 0.5D;

	public void onRun() {
		if(!Robot.isIndivDriveControl) arcadeDrive(-OI.axis_d_RY.get(), OI.axis_d_LX.get());
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
	
	/**
	 * Pseudo-arcade drive method of control. Calculate adjustments to the inputs in this method.
	 * 
	 * @param sp The parallel speed and direction of the robot.
	 * @param rotation The speed at which the robot should rotate.
	 */
	public void arcadeDrive(double sp, double rotation) {
		double mod = minR + difR * Math.pow(1 - Math.abs(sp), 2);
		double r = Math.pow(rotation, 3) * mod;
		Robot.driver.drivetrain.tankDrive(- sp - r, - sp + r);
	}
}