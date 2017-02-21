package redbacks.robot.actions;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.Check;
import redbacks.robot.Robot;

public class AcVibrateHopper extends Action
{
	//TODO
	double loopTime = 0;
	
	public AcVibrateHopper(Check check, double loopTime) {
		super(check);
		this.loopTime = loopTime;
	}
	
	public void onRun() {
		int t = (int) this.timeSinceInitialized() % 2;
		
		if(t==0)System.out.println("Firing");
		if(t==1)System.out.println("Closing");
		
		if(t == 0) Robot.hopper.hopperSol.set(true);
		else Robot.hopper.hopperSol.set(false);
	}
}
