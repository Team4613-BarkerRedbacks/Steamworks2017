package redbacks.robot;

import redbacks.arachne.core.references.CommandListStart;
import redbacks.arachne.lib.actions.*;
import redbacks.arachne.lib.actions.actuators.*;
import redbacks.arachne.lib.checks.*;
import redbacks.arachne.lib.checks.digital.*;
import redbacks.arachne.lib.checks.analog.*;
import redbacks.arachne.lib.commands.CommandSetup;
import redbacks.robot.actions.*;
import redbacks.robot.pid.AcMultiPID;
import redbacks.robot.pid.AcPIDControl;
import redbacks.robot.pid.AcRotatingPIDDrive;
import redbacks.robot.pid.AcTestPID;
import redbacks.robot.pid.PIDMotor;
import redbacks.robot.pid.Tolerances;

import static redbacks.robot.Robot.*;
import static redbacks.robot.RobotMap.*;

import edu.wpi.first.wpilibj.PIDSourceType;

public class CommandList extends CommandListStart
{
	static {subsystemToUse = null;}
	public static CommandSetup
		encodersZero = newCom(new AcSetNumSen(sensors.driveLEncoderDis, 0.0D), new AcSetNumSen(sensors.driveREncoderDis, 0.0D)),
		reset = newCom(sensors.new AcReset());
	
	static {subsystemToUse = sensors;}
	public static CommandSetup
		readSensors = newCom(new AcReadSensors());
	
	static {subsystemToUse = driver;}
	public static CommandSetup
		drive = newCom(new AcDrive()),
		pidtest = newCom(new AcTestPID()),
		pidtest2 = newCom(new AcPIDControl(drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, 0, new Tolerances.Absolute(50), sensors.driveREncoderDis, new PIDMotor(driver.left).setMultiplier(-1), new PIDMotor(driver.right))),
		rotatingpiddrivetest = newCom(new AcRotatingPIDDrive()),
		regainDriverControl = newCom(new AcInterrupt.KillSubsystem(driver)),
		multiAxisTest = newCom(
				new AcMulti(
						new AcMultiPID(new ChFalse(), true, new PIDMotor(driver.left), new double[]{-1, -1}, 
								new AcMultiPID.PIDParams(drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, 0, new Tolerances.Absolute(50), sensors.driveREncoderDis),
								new AcMultiPID.PIDParams(0.025, 0.0001, 0.001, 0, new Tolerances.Absolute(3), sensors.yaw)
						),
						new AcMultiPID(new ChFalse(), true, new PIDMotor(driver.right), new double[]{1, -1},
								new AcMultiPID.PIDParams(drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, 0, new Tolerances.Absolute(50), sensors.driveREncoderDis),
								new AcMultiPID.PIDParams(0.025, 0.0001, 0.001, 0, new Tolerances.Absolute(3), sensors.yaw)
						)
				)
		);
	
	static {subsystemToUse = shooter;}
	public static CommandSetup
		shoot = newCom(
			new AcMotor.RampTime(shooter.shooter, 0.75D, 2),
			new AcSeq.Parallel(feeder, new AcMotor.Set(feeder.feeder, 0.6D, new ChGettableBoolean(shootButton, false))),
			new AcPIDControl(new ChGettableBoolean(shootButton, false), false, 1.0E-5, 0, 5.0E-5, 0.000010D, -6300, new Tolerances.Percentage(1.0), sensors.driveLEncoderRate, false, 0, 0, PIDSourceType.kRate, -1D, -0.75D, new PIDMotor(shooter.shooter).setMultiplier(-1)),
			new AcMotor.Set(shooter.shooter, 0.75D, new ChTrue()),
			new AcMotor.RampTime(shooter.shooter, 0, 2)
		),
		shooterFeedHopper = newCom(
			new AcSeq.Parallel(feeder, new AcMotor.Set(feeder.feeder, 0.4D, new ChGettableBoolean(feedButton, false))),
			new AcMotor.RampTime(shooter.shooter, 0.25D, 0.5D),
			new AcDoNothing(new ChGettableBoolean(feedButton, false)),
			new AcMotor.RampTime(shooter.shooter, 0, 0.5D)
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
