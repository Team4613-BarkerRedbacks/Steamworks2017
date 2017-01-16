package redbacks.robot.actions;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.robot.OI;
import redbacks.robot.Robot;

public class AcManualFeed extends Action
{
	public AcManualFeed() {
		super(new ChFalse());
	}

	public void onRun() {
		Robot.feeder.feeder.set(OI.axis_d_LT.get() - OI.axis_d_RT.get(), command);
	}
}
