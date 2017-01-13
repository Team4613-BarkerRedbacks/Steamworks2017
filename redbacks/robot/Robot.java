package redbacks.robot;

import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.robot.subsystems.*;

import static redbacks.robot.CommandList.*;

public class Robot extends ArachneRobot
{
	/** The instance of the operator interface. This is used to map inputs to functions. */
	public static OI oi = new OI();

	public static SubsystemDriver driver = new SubsystemDriver();
	
	public static SubsystemSensors sensors = new SubsystemSensors();
	
	public void initDefaultCommands() {
		driver.setDefaultCommand(drive.c());
		sensors.setDefaultCommand(readSensors.c());
		
		oi.mapOperations();
	}

	public CommandBase getAutonomous(int autoID) {
		return Auto.getAutonomous(autoID);
	}
}
