package redbacks.robot;

import redbacks.arachne.core.references.AutoStart;
import redbacks.arachne.lib.actions.*;
import redbacks.arachne.lib.actions.actuators.*;
import redbacks.arachne.lib.checks.*;
import redbacks.arachne.lib.checks.analog.*;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.commands.CommandSetup;
import redbacks.arachne.lib.pid.Tolerances;
import redbacks.arachne.lib.trajectories.AcTrajectoryFast;
import redbacks.arachne.lib.trajectories.AcTrajectoryMid;
import redbacks.arachne.lib.trajectories.AcTrajectorySlow;
import redbacks.robot.actions.*;

import static redbacks.robot.Robot.*;
import static redbacks.robot.RobotMap.*;


import static redbacks.robot.CommandList.*;

public class Auto extends AutoStart
{
	public static CommandBase getAutonomous(int autoNumber) {
		switch(autoNumber) {
			//Left Gear
			case(1): return createAuto(
					new AcTrajectorySlow(new ChTime(5), true, TrajectoryListAmbi.ambi_leftGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D),
					new AcWait(2D),
					new AcTankDrive(new ChTime(0.5D), 0.5D, 0.5D),
					new AcWait(1D),
					new AcTankDrive(new ChTime(1.5D), -0.5D, -0.5D)
			);

			//Middle Gear
			case(2): return createAuto(
					new AcTrajectoryMid(new ChTime(3), true, TrajectoryListAmbi.ambi_middleGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.5D), 0.5D, 0.5D),
					new AcTankDrive(new ChNumSen(10, sensors.yaw), 0.7D, -0.7D),
					new AcWait(0.5D),
					new AcTankDrive(new ChTime(1.5D), -0.5D, -0.5D),
					new AcWait(1D),
					new AcTankDrive(new ChTime(0.5D), 0.5D, 0.5D),
					new AcTankDrive(new ChNumSen(20, sensors.yaw), -0.7D, 0.7D),
					new AcWait(0.5D),
					new AcTankDrive(new ChTime(1.5D), -0.5D, -0.5D)
			);

			//Right Gear
			case(3): return createAuto(
					new AcTrajectorySlow(new ChTime(5), true, TrajectoryListAmbi.ambi_rightGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D),
					new AcWait(2D),
					new AcTankDrive(new ChTime(0.5D), 0.5D, 0.5D),
					new AcWait(1D),
					new AcTankDrive(new ChTime(1.5D), -0.5D, -0.5D)
			);
			
			//Left Gear Drop
			case(4): return createAuto(
					new AcTrajectorySlow(new ChTime(5), true, TrajectoryListAmbi.ambi_leftGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D),
					new AcTankDrive(new ChTime(0.3D), 0.5D, 0.5D),
					new AcSeq.Parallel(gearPlace),
					new AcTankDrive(new ChTime(1.5D), 0.5D, 0.5D)
					
			);

			//Middle Gear Drop
			case(5): return createAuto(
					new AcTrajectoryMid(new ChTime(3), true, TrajectoryListAmbi.ambi_middleGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D),
					new AcTankDrive(new ChTime(0.3D), 0.5D, 0.5D),
					new AcSeq.Parallel(gearPlace),
					new AcTankDrive(new ChTime(1.5D), 0.5D, 0.5D)
			);

			//Right Gear Drop
			case(6): return createAuto(
					new AcTrajectorySlow(new ChTime(5), true, TrajectoryListAmbi.ambi_rightGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D),
					new AcTankDrive(new ChTime(0.3D), 0.5D, 0.5D),
					new AcSeq.Parallel(gearPlace),
					new AcTankDrive(new ChTime(1.5D), 0.5D, 0.5D)
			);
			
			//I am amazing!
			case(7): return createAuto(
					new AcTrajectorySlow(new ChTime(7), true, TrajectoryListAmbi.ambi_leftGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(500), false, 0, 0),
					new AcSeq.Parallel(gearPlace)
			);

			//Blue kPa
			case(11): return createAuto(
					new AcSeq.Parallel(hopperOut),
					new AcSeq.Parallel(spitterDown),
					new AcTrajectoryFast(new ChTime(2), true, TrajectoryListBlue.blue_wallToHopper, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(2D), 0.8D, 0.8D),
					new AcSeq.Parallel(shooterIn),
					new AcSeq.Parallel(
							new AcMotor.Set(intake.motIntakeF, -0.6D, new ChTime(1)),
							new AcSeq.Parallel(shootSpeed)
					),
					new AcSeq.Parallel(deflect),
					new AcTrajectoryFast(new ChTime(3D), true, TrajectoryListBlue.blue_hopperToBoiler, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.05, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcSeq.Parallel(
							new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D),
							new AcInterrupt.KillSubsystem(spitter)
					),
					new AcWait(0.75D),
					new AcSeq.Parallel(hopperFeed),
					new AcSeq.Parallel(hopperVibrate)
			);

			//Red kPa
			case(21): return createAuto(
					new AcSeq.Parallel(hopperOut),
					new AcSeq.Parallel(spitterDown),
					new AcTrajectoryFast(new ChTime(2), true, TrajectoryListRed.red_wallToHopper, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(2D), 0.8D, 0.8D),
					new AcSeq.Parallel(shooterIn),
					new AcSeq.Parallel(
							new AcMotor.Set(intake.motIntakeF, -0.6D, new ChTime(1)),
							new AcSeq.Parallel(shootSpeed)
					),
					new AcSeq.Parallel(deflect),
					new AcTrajectoryFast(new ChTime(3D), true, TrajectoryListRed.red_hopperToBoiler, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.05, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcSeq.Parallel(
							new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D),
							new AcInterrupt.KillSubsystem(spitter)
					),
					new AcWait(0.75D),
					new AcSeq.Parallel(hopperFeed),
					new AcSeq.Parallel(hopperVibrate)
			);

			default: return null;
		}
	}

	public static CommandBase createAuto(Action... actions) {
		return new CommandSetup(null, new AcSeq.Parallel(actions)).c();
	}
}
