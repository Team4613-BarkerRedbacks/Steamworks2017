package redbacks.robot;

import redbacks.arachne.core.references.AutoStart;
import redbacks.arachne.lib.actions.*;
import redbacks.arachne.lib.actions.actuators.*;
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
					sensors.new AcReset(),
					new AcTankDrive(new ChNumSen(3000, sensors.driveREncoderDis), 0.83D, 0.9D),
					new AcTankDrive(new ChNumSen(8, sensors.yaw, true, false, false), 0.9D, 0.8D),
					new AcTankDrive(new ChTime(0.2D), 0.4D, 0.4D)
			);
			
			case(2): return createAuto(
					sensors.new AcReset(),
					new AcTankDrive(new ChNumSen(45, sensors.yaw, true, false, false), 1.0D, 0.8D),
					new AcTankDrive(new ChTime(0.3D), 0.5D, 0.5D)
			);
			default: return null;
		}
	}
}
