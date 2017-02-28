package redbacks.robot.actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
		boolean invertDrive = SmartDashboard.getBoolean("Invert Drive", true);
		SmartDashboard.putBoolean("Invert Drive", invertDrive);
		if(!Robot.isIndivDriveControl) arcadeDrive(OI.axis_d_RY.get() * (invertDrive ? 1 : -1), OI.axis_d_LX.get());
	}
	
	/**
	 * Pseudo-arcade drive method of control. Calculate adjustments to the inputs in this method.
	 * 
	 * @param sp The parallel speed and direction of the robot.
	 * @param rotation The speed at which the robot should rotate.
	 */
	public void arcadeDrive(double sp, double rotation) {
		double mod = minR + difR * Math.pow(1 - Math.abs(sp), 2);
		double r = Math.pow(rotation, 1) * mod;
		Robot.driver.drivetrain.tankDrive(- sp - r, - sp + r);
	}
}
