package redbacks.robot;

import redbacks.arachne.core.references.AutoStart;
import redbacks.arachne.lib.actions.*;
import redbacks.arachne.lib.actions.actuators.*;
import redbacks.arachne.lib.checks.*;
import redbacks.arachne.lib.checks.analog.*;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.pid.AcPIDControl;
import redbacks.arachne.lib.pid.PIDMotor;
import redbacks.arachne.lib.pid.Tolerances;
import redbacks.arachne.lib.trajectories.AcTrajectoryFast;
import redbacks.arachne.lib.trajectories.AcTrajectoryMid;
import redbacks.arachne.lib.trajectories.AcTrajectorySlow;
import redbacks.robot.actions.*;

import static redbacks.robot.Robot.*;
import static redbacks.robot.RobotMap.*;

import edu.wpi.first.wpilibj.PIDSourceType;

import static redbacks.robot.CommandList.*;

public class Auto extends AutoStart
{
	public static CommandBase getAutonomous(int autoNumber) {
		switch(autoNumber) {
			case(1): return createAuto(
					new AcTrajectorySlow(new ChTime(7), true, TrajectoryList.ambi_leftGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D),
					new AcWait(2D),
					new AcTankDrive(new ChTime(0.5D), 0.5D, 0.5D),
					new AcWait(1D),
					new AcTankDrive(new ChTime(1.5D), -0.5D, -0.5D)
			);

			case(2): return createAuto(
					new AcTrajectoryMid(new ChTime(5), true, TrajectoryList.ambi_middleGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D),
					new AcWait(2D),
					new AcTankDrive(new ChTime(0.5D), 0.5D, 0.5D),
					new AcTankDrive(new ChNumSen(10, sensors.yaw), 0.5D, -0.5D),
					new AcWait(1D),
					new AcTankDrive(new ChTime(1.5D), -0.5D, -0.5D),
					new AcWait(2D),
					new AcTankDrive(new ChTime(0.5D), 0.5D, 0.5D),
					new AcTankDrive(new ChNumSen(20, sensors.yaw), -0.5D, 0.5D),
					new AcWait(1D),
					new AcTankDrive(new ChTime(1.5D), -0.5D, -0.5D)
			);

			case(3): return createAuto(
					new AcTrajectorySlow(new ChTime(7), true, TrajectoryList.ambi_rightGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D),
					new AcWait(2D),
					new AcTankDrive(new ChTime(0.5D), 0.5D, 0.5D),
					new AcWait(1D),
					new AcTankDrive(new ChTime(1.5D), -0.5D, -0.5D)
			);
			
			case(4): return createAuto(
					//Places gear
					new AcTrajectorySlow(new ChTime(7), true, TrajectoryList.blue_WallToLeftGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					//new AcTankDrive(new ChTime(0.6D), -0.5D, -0.5D),
					
					new AcWait(1D),
					
					//Gets balls from hopper
					new AcTrajectorySlow(new ChFalse(), true, TrajectoryList.blue_GearLeftToHopper, driver.drivetrain, -1, -1,
							sensors.yaw, 0.1, sensors.centreEncoderDis, false, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.6D), 0.5D, 0.5D),
					
					new AcWait(4D),
					
					//Moves to hopper
					new AcTrajectorySlow(new ChFalse(), true, TrajectoryList.blue_HopperToBoiler, driver.drivetrain, -1, -1,
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.6D), 0.5D, 0.5D)
//					
//					//Shoots balls
//					new AcMulti(
//							new AcPIDControl(0.01D, new ChFalse(), false, 
//									3.0E-6, 0, 5.0E-5, 0.000004D, -22000,
//									new Tolerances.Percentage(1.0), sensors.shooterEncoderRateL, false, 0, 0, PIDSourceType.kRate, -1D, -0.5D, new PIDMotor(shooter.motShootL).setMultiplier(-1)
//							),
//							new AcPIDControl(0.01D, new ChFalse(), false, 
//									3.0E-6, 0, 5.0E-5, 0.000004D, -22000,
//									new Tolerances.Percentage(1.0), sensors.shooterEncoderRateR, false, 0, 0, PIDSourceType.kRate, -1D, -0.5D, new PIDMotor(shooter.motShootR).setMultiplier(-1)
//							)
//					)
					
					
			);

			case(11): return createAuto(
					new AcTrajectorySlow(new ChFalse(), true, TrajectoryList2.blue_wallToBottomRightHopper3, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(2.5D), 0.5D, 0.5D)
			);
			
			case(12): return createAuto(
					new AcTrajectorySlow(new ChFalse(), true, TrajectoryList2.blue_wallToBottomGearS, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D)
			);
			
			case(13): return createAuto(
					new AcTrajectorySlow(new ChFalse(), true, TrajectoryList2.blue_wallToBottomGearArc, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D)
			);

			case(14): return createAuto(
					new AcTrajectoryMid(new ChFalse(), true, TrajectoryList2.blue_wallToBottomRightHopper3, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(2.5D), 0.5D, 0.5D),
					new AcTrajectoryMid(new ChFalse(), true, TrajectoryList2.blue_bottomRightHopperToBoiler, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.5D, -0.5D)
			);
			
			case(15): return createAuto(
					new AcTrajectoryFast(new ChTime(2), true, TrajectoryList2.test3, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(1.3D), 0.8D, 0.8D),
					new AcSeq.Parallel(deflect),
					new AcTrajectoryFast(new ChFalse(), true, TrajectoryList2.testa5, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.05, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(250), false, 0, 0),
					new AcTankDrive(new ChTime(0.7D), -0.8D, -0.8D)
			);

			default: return null;
		}
	}
}
