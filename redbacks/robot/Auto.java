package redbacks.robot;

import redbacks.arachne.core.references.AutoStart;
import redbacks.arachne.lib.actions.*;
import redbacks.arachne.lib.actions.actuators.*;
import redbacks.arachne.lib.checks.*;
import redbacks.arachne.lib.checks.analog.*;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.pid.Tolerances;
import redbacks.arachne.lib.trajectories.AcTrajectory;
import redbacks.robot.actions.*;

import static redbacks.robot.Robot.*;
import static redbacks.robot.RobotMap.drivePIDMotorkD;
import static redbacks.robot.RobotMap.drivePIDMotorkI;
import static redbacks.robot.RobotMap.drivePIDMotorkP;

public class Auto extends AutoStart
{
	public static CommandBase getAutonomous(int autoNumber) {
		switch(autoNumber) {
			case(1): return createAuto(
					new AcTrajectory(new ChFalse(), true, TrajectoryList.blue_wallToBottomGear, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP*3, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(150), false, 0, 0),
					new AcWait(1D),
					new AcTrajectory(new ChFalse(), true, TrajectoryList.blue_bottomGearToBottomRightHopper, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP*3, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(150), false, 0, 0)
			);
			
			case(2): return createAuto(
					sensors.new AcReset(),
					new AcTankDrive(new ChNumSen(45, sensors.yaw, true, false, false), 1.0D, 0.8D),
					new AcTankDrive(new ChTime(0.3D), 0.5D, 0.5D)
			);
			
			case(3): return createAuto(
					new AcTrajectory(new ChFalse(), true, TrajectoryList.blue_wallToBottomGear, driver.drivetrain, -1, -1,
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(150), false, 0, 0),
					new AcTrajectory(new ChFalse(), true, TrajectoryList.blue_bottomGearToBottomRightHopper, driver.drivetrain, -1, -1, 
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(150), false, 0, 0),
					new AcWait(1D),
					new AcTrajectory(new ChFalse(), true, TrajectoryList.blue_bottomRightHopperToBoiler, driver.drivetrain, -1, -1,
							sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(150), false, 0, 0)
			);
			default: return null;
		}
	}
}
