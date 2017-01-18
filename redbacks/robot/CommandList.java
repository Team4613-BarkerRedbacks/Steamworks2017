package redbacks.robot;

import redbacks.arachne.core.references.CommandListStart;
import redbacks.arachne.lib.actions.*;
import redbacks.arachne.lib.actions.actuators.*;
import redbacks.arachne.lib.checks.*;
import redbacks.arachne.lib.checks.digital.*;
import redbacks.arachne.lib.checks.analog.*;
import redbacks.arachne.lib.commands.CommandSetup;
import redbacks.robot.actions.*;
import redbacks.robot.pid.AcPIDControl;
import redbacks.robot.pid.AcTestPID;
import redbacks.robot.pid.PIDMotor;
import redbacks.robot.pid.Tolerances;

import static redbacks.robot.Robot.*;

import edu.wpi.first.wpilibj.PIDController;

public class CommandList extends CommandListStart
{
	static {subsystemToUse = null;}
	public static CommandSetup
		encodersZero = newCom(new AcSetNumSen(Robot.sensors.driveLEncoder, 0.0D), new AcSetNumSen(Robot.sensors.driveREncoder, 0.0D)),
		reset = newCom(sensors.new AcReset());
	
	static {subsystemToUse = sensors;}
	public static CommandSetup
		readSensors = newCom(new AcReadSensors());
	
	static {subsystemToUse = driver;}
	public static CommandSetup
		drive = newCom(new AcDrive()),
		pidtest = newCom(new AcTestPID()),
		pidtest2 = newCom(new AcPIDControl(0.0005D, 0, 0.00005D, 1000, new Tolerances.Absolute(50), sensors.driveREncoder, new PIDMotor(driver.left).setMultiplier(-1), new PIDMotor(driver.right)));
	
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
	
	static {subsystemToUse = spitter;}
	public static CommandSetup
		spit = newCom(new AcMotor.Set(spitter.spitter, 1.0D, new ChFalse())),
		spitIn = newCom(new AcMotor.Set(spitter.spitter, -0.2D, new ChFalse()));
	
	static {subsystemToUse = sequencer;}
	
}
