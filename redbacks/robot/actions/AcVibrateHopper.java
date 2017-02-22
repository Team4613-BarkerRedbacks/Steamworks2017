package redbacks.robot.actions;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.Check;
import redbacks.robot.Robot;

public class AcVibrateHopper extends Action
{
	double iterTime;
	
	public AcVibrateHopper(Check check, double iterTime) {
		super(check);
		this.iterTime = iterTime;
	}
	
	public void onRun() {
		int t = (int) (this.timeSinceInitialized() / iterTime) % 2;
		
		if(t == 0) Robot.hopper.hopperSol.set(true);
		else Robot.hopper.hopperSol.set(false);
	}
}
