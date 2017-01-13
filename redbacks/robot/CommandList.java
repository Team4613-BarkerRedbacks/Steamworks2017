package redbacks.robot;

import redbacks.arachne.core.references.CommandListStart;
import redbacks.arachne.lib.commands.CommandSetup;
import redbacks.robot.actions.AcDrive;
import redbacks.robot.actions.AcReadSensors;

public class CommandList extends CommandListStart
{
	static {subsystemToUse = null;}
	
	static {subsystemToUse = Robot.sensors;}
	public static CommandSetup
		readSensors = newCom(new AcReadSensors());
	
	static {subsystemToUse = Robot.driver;}
	public static CommandSetup
		drive = newCom(new AcDrive());
	
	static {subsystemToUse = Robot.sequencer;}
	
}
