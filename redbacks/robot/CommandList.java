package redbacks.robot;

import redbacks.arachne.core.references.CommandListStart;
import redbacks.arachne.lib.actions.*;
import redbacks.arachne.lib.actions.actuators.*;
import redbacks.arachne.lib.checks.*;
import redbacks.arachne.lib.checks.digital.ChGettableBoolean;
import redbacks.arachne.lib.commands.CommandSetup;
import redbacks.arachne.lib.pid.*;
import redbacks.arachne.lib.trajectories.AcTrajectory;
import redbacks.robot.actions.*;

import static redbacks.robot.Robot.*;
import static redbacks.robot.RobotMap.*;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandList extends CommandListStart
{
	static {subsystemToUse = null;}
	public static CommandSetup
		interruptDriver = newCom(new AcInterrupt.KillSubsystem(driver)),
		switchDir = newCom(driver.new AcInvertDrive()),
		resetSensors = newCom(sensors.new AcReset()),
		killAll = newCom(new AcInterrupt.KillAllCommands()),

		intakeDown = newCom(new AcSolenoid.Single(intake.intakeSol, false)),
		intakeUp = newCom(new AcSolenoid.Single(intake.intakeSol, true)),

		spitterDown = newCom(new AcSolenoid.Single(spitter.spitterSol, true)),
		spitterUp = newCom(new AcSolenoid.Single(spitter.spitterSol, false)),

		hopperOut = newCom(new AcSolenoid.Single(hopper.hopperSol, true)),
		hopperIn = newCom(new AcSolenoid.Single(hopper.hopperSol, false)),
		
		hopperVibrate = newCom(new AcVibrateHopper(new ChFalse(), 0)
		);
	
	static {subsystemToUse = sensors;}
	public static CommandSetup
		readSensors = newCom(new AcReadSensors());
	
	static {subsystemToUse = driver;}
	public static CommandSetup
		drive = newCom(new AcDrive()),
		pidExample = newCom(new AcPIDControl(drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, 0, new Tolerances.Absolute(50), sensors.centreEncoderDis, new PIDMotor(driver.leftMotor).setMultiplier(-1), new PIDMotor(driver.rightMotor))),
		multiAxisExample = newCom(
				new AcMulti(
						new AcMultiPID(new ChFalse(), true, new PIDMotor(driver.leftMotor), new double[]{-1, -1}, 
								new PIDParams(drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, 0, new Tolerances.Absolute(50), sensors.centreEncoderDis),
								new PIDParams(0.025, 0.0001, 0.001, 0, new Tolerances.Absolute(3), sensors.yaw)
						),
						new AcMultiPID(new ChFalse(), true, new PIDMotor(driver.rightMotor), new double[]{1, -1},
								new PIDParams(drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, 0, new Tolerances.Absolute(50), sensors.centreEncoderDis),
								new PIDParams(0.025, 0.0001, 0.001, 0, new Tolerances.Absolute(3), sensors.yaw)
						)
				)
		);
	
	static {subsystemToUse = intake;}
	private static double intakeFast = 1D, intakeMid = 0.60D, intakeSlow = 0.3D, iPS = 0.50D, iPF = 0.50D;
	public static CommandSetup
		intakeIn = newCom(new AcMotor.Set(intake.intakeMotor, intakeFast, new ChFalse())),
		intakeOut = newCom(new AcMotor.Set(intake.intakeMotor, -intakeFast, new ChFalse()));
	
	static {subsystemToUse = hopper;}
	private static double hopperFast = 0.6D;
	public static CommandSetup
		hopperFeed = newCom(
				new AcMotor.Set(hopper.hopperMotor, hopperFast, new ChTrue()),
				new AcMotor.Set(intake.intakeMotor, iPS, new ChTrue()),
				new AcMotor.RampTime(intake.intakeMotor, iPF, 10, new ChFalse(), false)
//				new AcMotor.Set(intake.motIntakeB, intakeMid, new ChTrue()),
//				new AcMotor.Set(intake.motIntakeF, intakeMid, new ChFalse())
		),
		hopperOn = newCom(new AcMotor.Set(hopper.hopperMotor, hopperFast, new ChFalse()));
	
	static {subsystemToUse = shooter;}
	private static double shootFast = 1.0D, shootPIDBase = 0.7D, shootFeed = 0.25D;
	public static CommandSetup
		shootSpeed = newCom(
//				new AcMotor.Set(shooter.shooterMotor, 1, new ChFalse())
//				new AcMotor.RampTime(shooter.shooterMotor, shootFast, 2D, new ChFalse(), false)
				new AcMotor.RampTime(shooter.shooterMotor, shootPIDBase, 2D, new ChFalse(), true),
				new AcMulti(
						new AcPIDControl(0.01D, new ChFalse(), false, 
								3.0E-6, 0, 5.0E-5, 0.000004D, -22000,
								new Tolerances.Percentage(1.0), sensors.shooterEncoderRateL, false, 0, 0, PIDSourceType.kRate, -1D, -0.5D, new PIDMotor(shooter.motShootL).setMultiplier(-1)
						),
						new AcPIDControl(0.01D, new ChFalse(), false, 
								3.0E-6, 0, 5.0E-5, 0.000004D, -22000,
								new Tolerances.Percentage(1.0), sensors.shooterEncoderRateR, false, 0, 0, PIDSourceType.kRate, -1D, -0.5D, new PIDMotor(shooter.motShootR).setMultiplier(-1)
						)
				)
//				new AcMotor.RampTime(shooter.shooterMotor, shootPIDBase, 2D, new ChFalse(), true),
//				new AcPIDControl(0.01D, new ChFalse(), false, 
//						1.0E-6, 0, 5.0E-5, 0.000008D, -5750,
//						new Tolerances.Percentage(1.0), sensors.shooterEncoderRate, false, 0, 0, PIDSourceType.kRate, -1D, -0.75D, new PIDMotor(shooter.shooterMotor).setMultiplier(-1)
//				)
//				new AcMotor.RampTime(shooter.shooterMotor, shootPIDBase, 2D, new ChFalse(), true),
//				new AcPIDControl(0.01D, new ChFalse(), false, 
//						1.0E-6, 0, 5.0E-5, 0.000008D, -7400,
//						new Tolerances.Percentage(1.0), sensors.shooterEncoderRate, false, 0, 0, PIDSourceType.kRate, -1D, -0.75D, new PIDMotor(shooter.shooterMotor).setMultiplier(-1)
//				)
		),
		rel_shoot = newCom(
				new AcMotor.Set(shooter.shooterMotor, shootPIDBase, new ChTrue()),
				new AcMotor.RampTime(shooter.shooterMotor, 0, 2)
		),
		shooterFeedHopper = newCom(
				new AcMotor.Set(intake.intakeMotor, intakeFast, new ChTrue()),
				new AcMotor.RampTime(shooter.shooterMotor, shootFeed, 0.5D, new ChFalse(), false)
		),
		rel_shooterFeedHopper = newCom(
				new AcMotor.Set(shooter.shooterMotor, shootFeed, new ChTrue()),
				new AcMotor.RampTime(shooter.shooterMotor, 0, 0.5D)
		),
		shooterIn = newCom(
				new AcMotor.RampTime(shooter.shooterMotor, -0.5D, 0.5D, new ChFalse(), false)
		),
		rel_shooterIn = newCom(
				new AcMotor.Set(shooter.shooterMotor, -0.5D, new ChTrue()),
				new AcMotor.RampTime(shooter.shooterMotor, 0, 0.5D)
		);

	static {subsystemToUse = climber;}
	public static CommandSetup
		climbSlow = newCom(new AcMotor.Set(climber.climberMotor, 0.2D, new ChFalse())),
		climb = newCom(new AcMotor.Set(climber.climberMotor, 1.0D, new ChFalse())),
		climbRamp = newCom(new AcMotor.RampTime(climber.climberMotor, 1D, 2D, new ChFalse(), false));
	
	static {subsystemToUse = spitter;}
	private static double spitFast = 1.0D, spitMid = 0.7D, spitSlow = 0.3D;
	public static CommandSetup
		deflect = newCom(
				new AcSeq.Parallel(spitterDown),
				new AcMotor.Set(spitter.spitterMotor, spitFast, new ChFalse())
		),
		rel_deflect = newCom(
				new AcSeq.Parallel(spitterUp),
				new AcMotor.Set(spitter.spitterMotor, 0, new ChFalse())
		),
		spitOut = newCom(new AcMotor.Set(spitter.spitterMotor, spitSlow, new ChFalse())),
		spitIn = newCom(new AcMotor.Set(spitter.spitterMotor, -spitSlow, new ChFalse())),
		
		gearFromGround = newCom(
				new AcSeq.Parallel(spitterDown),
				new AcMotor.Set(spitter.spitterMotor, -spitSlow, new ChFalse()),//TODO ChGettableBoolean(sensors.gearLight, true)),
				new AcSeq.Parallel(spitterUp),
				new AcWait(1D)
		),
		gearFromHP = newCom(
				new AcSeq.Parallel(spitterUp),
				new AcMotor.Set(spitter.spitterMotor, -spitMid, new ChFalse()),//TODO ChGettableBoolean(sensors.gearLight, true)),
				new AcWait(0.5D)
		),
		gearPlace = newCom(
				new AcSeq.Parallel(spitterDown),
				new AcMotor.Set(spitter.spitterMotor, spitSlow, new ChFalse()),//TODO new ChGettableBoolean(sensors.gearLight, false)),
				new AcWait(0.5D)
		);
	
	static {subsystemToUse = sequencer;}
}
