package redbacks.robot;

import redbacks.arachne.core.references.CommandListStart;
import redbacks.arachne.lib.actions.*;
import redbacks.arachne.lib.actions.actuators.*;
import redbacks.arachne.lib.checks.*;
import redbacks.arachne.lib.checks.digital.*;
import redbacks.arachne.lib.checks.analog.*;
import redbacks.arachne.lib.commands.CommandSetup;
import redbacks.robot.actions.*;

import static redbacks.robot.Robot.*;

public class CommandList extends CommandListStart
{
	static {subsystemToUse = null;}
	public static CommandSetup
		encodersZero = newCom(new AcSetNumSen(Robot.sensors.driveLEncoder, 0.0D), new AcSetNumSen(Robot.sensors.driveREncoder, 0.0D));
	
	static {subsystemToUse = sensors;}
	public static CommandSetup
		readSensors = newCom(new AcReadSensors());
	
	static {subsystemToUse = driver;}
	public static CommandSetup
		drive = newCom(new AcDrive());
	
	static {subsystemToUse = shooter;}
	public static CommandSetup
		shoot = newCom(
			new AcMotor.RampTime(shooter.shooter, 1, 2),
			new AcDoNothing(new ChGettableBoolean(OI.d_B, false)),
			new AcMotor.RampTime(shooter.shooter, 0, 2)
		);
	
	static {subsystemToUse = intake;}
	
	static {subsystemToUse = feeder;}
	public static CommandSetup
		feedManual = newCom(new AcManualFeed());

	static {subsystemToUse = climber;}
	
	static {subsystemToUse = sequencer;}
	
}
