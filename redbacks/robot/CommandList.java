package redbacks.robot;

import redbacks.arachne.core.references.CommandListStart;
import redbacks.arachne.lib.actions.actuators.AcMotor;
import redbacks.arachne.lib.checks.*;
import redbacks.arachne.lib.commands.CommandSetup;
import redbacks.robot.actions.*;

import static redbacks.robot.Robot.*;

public class CommandList extends CommandListStart
{
	static {subsystemToUse = null;}
	
	static {subsystemToUse = sensors;}
	public static CommandSetup
		readSensors = newCom(new AcReadSensors());
	
	static {subsystemToUse = driver;}
	public static CommandSetup
		drive = newCom(new AcDrive());
	
	static {subsystemToUse = shooter;}
	public static CommandSetup
		shoot = newCom(
			new AcMotor.Set(shooter.shooter, 1, new ChTrue()),
			new AcMotor.Set(shooter.feeder, 1, new ChFalse())
		);
	
	static {subsystemToUse = intake;}
	
	static {subsystemToUse = climber;}
	
	static {subsystemToUse = sequencer;}
	
}
