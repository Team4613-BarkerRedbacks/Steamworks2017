package redbacks.robot.actions;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChTrue;
import redbacks.robot.Robot;

public class AcSetIndivDrive extends Action
{
	public AcSetIndivDrive() {
		super(new ChTrue());
	}
	
	public void onStart() {
		Robot.isIndivDriveControl = true;
	}
}
