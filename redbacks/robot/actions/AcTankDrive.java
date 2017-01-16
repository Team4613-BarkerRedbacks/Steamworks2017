package redbacks.robot.actions;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.Check;
import redbacks.robot.Robot;

public class AcTankDrive extends Action
{
	double l, r;
	double lEnc, rEnc;
	
	public AcTankDrive(Check check, double lSpeed, double rSpeed) {
		super(check);
		l = lSpeed;
		r = rSpeed;
	}
	
	public void onStart() {
		Robot.isIndivDriveControl = false;
	}

	public void onRun() {
		Robot.driver.drivetrain.tankDrive(-l, -r);
	}

	public void onFinish() {
		Robot.driver.drivetrain.tankDrive(-l, -r);
	}
}
