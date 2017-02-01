package redbacks.robot;

import redbacks.arachne.core.references.CommandListStart;
import redbacks.arachne.lib.actions.*;
import redbacks.arachne.lib.actions.actuators.*;
import redbacks.arachne.lib.checks.*;
import redbacks.arachne.lib.commands.CommandSetup;
import redbacks.arachne.lib.pid.*;
import redbacks.arachne.lib.trajectories.AcTrajectory;
import redbacks.robot.actions.*;

import static redbacks.robot.Robot.*;
import static redbacks.robot.RobotMap.*;

import edu.wpi.first.wpilibj.PIDSourceType;

public class CommandList extends CommandListStart
{
	static {subsystemToUse = null;}
	public static CommandSetup
		encodersZero = newCom(new AcSetNumSen(sensors.leftEncoderDis, 0.0D), new AcSetNumSen(sensors.rightEncoderDis, 0.0D)),
		interruptDriver = newCom(new AcInterrupt.KillSubsystem(driver)),
		reset = newCom(sensors.new AcReset()),

		intakeDown = newCom(new AcSolenoid.Single(intake.intakeSol, false)),
		intakeUp = newCom(new AcSolenoid.Single(intake.intakeSol, true)),

		spitterDown = newCom(new AcSolenoid.Single(spitter.spitterSol, false)),
		spitterUp = newCom(new AcSolenoid.Single(spitter.spitterSol, true));
	
	static {subsystemToUse = sensors;}
	public static CommandSetup
		readSensors = newCom(new AcReadSensors());
	
	static {subsystemToUse = driver;}
	public static CommandSetup
		drive = newCom(new AcDrive()),
		pidExample = newCom(new AcPIDControl(drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, 0, new Tolerances.Absolute(50), sensors.rightEncoderDis, new PIDMotor(driver.leftMotor).setMultiplier(-1), new PIDMotor(driver.rightMotor))),
		multiAxisExample = newCom(
				new AcMulti(
						new AcMultiPID(new ChFalse(), true, new PIDMotor(driver.leftMotor), new double[]{-1, -1}, 
								new PIDParams(drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, 0, new Tolerances.Absolute(50), sensors.rightEncoderDis),
								new PIDParams(0.025, 0.0001, 0.001, 0, new Tolerances.Absolute(3), sensors.yaw)
						),
						new AcMultiPID(new ChFalse(), true, new PIDMotor(driver.rightMotor), new double[]{1, -1},
								new PIDParams(drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, 0, new Tolerances.Absolute(50), sensors.rightEncoderDis),
								new PIDParams(0.025, 0.0001, 0.001, 0, new Tolerances.Absolute(3), sensors.yaw)
						)
				)
		);
	
	static {subsystemToUse = shooter;}
	public static CommandSetup
		shoot = newCom(
				new AcMotor.RampTime(shooter.shooterMotor, 0.85D, 2),
				new AcMotor.Set(intake.intakeMotor, 0.5D, new ChTrue()),
				new AcPIDControl(new ChFalse(), false, 1.0E-5, 0, 5.0E-5, 0.00001D, -6600, new Tolerances.Percentage(1.0), sensors.shooterEncoderRate, false, 0, 0, PIDSourceType.kRate, -1D, -0.75D, new PIDMotor(shooter.shooterMotor).setMultiplier(-1))
		),
		rel_shoot = newCom(
				new AcMotor.Set(shooter.shooterMotor, 0.85D, new ChTrue()),
				new AcMotor.RampTime(shooter.shooterMotor, 0, 2)
		),
		shooterFeedHopper = newCom(
				new AcMotor.Set(intake.intakeMotor, 0.5D, new ChTrue()),
				new AcMotor.RampTime(shooter.shooterMotor, 0.25D, 0.5D)
		),
		rel_shooterFeedHopper = newCom(
				new AcMotor.RampTime(shooter.shooterMotor, 0, 0.5D)
		);
	
	static {subsystemToUse = intake;}
	public static CommandSetup
		intakeIn = newCom(new AcMotor.Set(intake.intakeMotor, 1D, new ChFalse())),
		intakeOut = newCom(new AcMotor.Set(intake.intakeMotor, -1D, new ChFalse()));
	
	static {subsystemToUse = hopper;}
	public static CommandSetup
		hopperOn = newCom(new AcMotor.Set(hopper.hopperMotor, 0.6D, new ChFalse()));

	static {subsystemToUse = climber;}
	public static CommandSetup
		climbSlow = newCom(new AcMotor.Set(climber.climberMotor, 0.2D, new ChFalse())),
		climb = newCom(new AcMotor.Set(climber.climberMotor, 1.0D, new ChFalse())),
		climbRamp = newCom(new AcMotor.RampTime(climber.climberMotor, 1D, 2D, new ChFalse(), false));
	
	static {subsystemToUse = spitter;}
	public static CommandSetup
		spit = newCom(new AcMotor.Set(spitter.spitterMotor, 1.0D, new ChFalse())),
		spitIn = newCom(new AcMotor.Set(spitter.spitterMotor, -0.2D, new ChFalse()));
	
	static {subsystemToUse = sequencer;}
	
}
