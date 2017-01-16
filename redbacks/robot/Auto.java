package redbacks.robot;

import redbacks.arachne.core.references.AutoStart;
import redbacks.arachne.lib.actions.*;
import redbacks.arachne.lib.actions.actuators.AcMotor;
import redbacks.arachne.lib.checks.*;
import redbacks.arachne.lib.checks.analog.*;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.robot.actions.*;

import static redbacks.robot.Robot.*;

public class Auto extends AutoStart
{
	public static CommandBase getAutonomous(int autoNumber) {
		switch(autoNumber) {
			case(1): return createAuto(
					new AcSetNumSen(sensors.driveLEncoder, 0),
					new AcSetNumSen(sensors.driveREncoder, 0),
					new AcSetNumSen(sensors.yaw, 0),
					new AcTankDrive(new ChNumSen(2800, sensors.driveREncoder), 0.6D, 0.9D),
					new AcTankDrive(new ChNumSen(10, sensors.yaw, false, true, false), 0.9D, 0.6D),
					new AcTankDrive(new ChTrue(), 0.9D, 0.9D),
					new AcMulti(
							new AcMotor.Set(driver.left, -0.7D, new ChNumSen(2500, sensors.driveLEncoder)),
							new AcMotor.Set(driver.right, 0.7D, new ChNumSen(2500, sensors.driveREncoder))
					),
					new AcMulti(
							new AcMotor.Set(driver.left, -0.5D, new ChNumSen(2500, sensors.driveLEncoder)),
							new AcMotor.Set(driver.right, 0.5D, new ChNumSen(2500, sensors.driveREncoder))
					)
			);
			case(2): return createAuto(
					new AcTankDrive(new ChNumSen(3000, sensors.driveREncoder), 0.83D, 0.9D),
					new AcTankDrive(new ChNumSen(8, sensors.yaw, true, false, false), 0.9D, 0.8D),
					new AcTankDrive(new ChTime(0.2D), 0.4D, 0.4D)
			);
			default: return null;
		}
	}
}
