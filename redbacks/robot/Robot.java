package redbacks.robot;

import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.robot.subsystems.*;

import static redbacks.robot.CommandList.*;

import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends ArachneRobot
{
	/** The instance of the operator interface. This is used to map inputs to functions. */
	public static OI oi = new OI();

	public static SubsystemSensors sensors = new SubsystemSensors();
	
	public static SubsystemDriver driver = new SubsystemDriver();
	public static SubsystemShooter shooter = new SubsystemShooter();
	public static SubsystemFeeder feeder = new SubsystemFeeder();
	public static SubsystemIntake intake = new SubsystemIntake();
	public static SubsystemClimber climber = new SubsystemClimber();
	public static SubsystemSpitter spitter = new SubsystemSpitter();
		
	public void initDefaultCommands() {
		driver.setDefaultCommand(drive.c());
		feeder.setDefaultCommand(feedManual.c());
		
		sensors.setDefaultCommand(readSensors.c());
		sensors.resetSensors();
		
		oi.mapOperations();
	}

	public CommandBase getAutonomous(int autoID) {
		return Auto.getAutonomous(autoID);
	}
	
	public void initialiseRobot() {
		//CameraServer.getInstance().startAutomaticCapture();
	}
}
