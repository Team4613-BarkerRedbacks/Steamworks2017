package redbacks.robot;

import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.robot.subsystems.*;

import static redbacks.robot.CommandList.*;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PIDController;

/**
 * @author Sean Zammit
 */
public class Robot extends ArachneRobot
{
	public static ArrayList<PIDController> activePIDs = new ArrayList<PIDController>();
	private boolean hasCameraStarted = false;
	
	/** The instance of the operator interface. This is used to map inputs to functions. */
	public static OI oi = new OI();

	public static SubsystemSensors sensors = new SubsystemSensors();
	
	public static SubsystemDriver driver = new SubsystemDriver();
	public static SubsystemShooter shooter = new SubsystemShooter();
	public static SubsystemHopper hopper = new SubsystemHopper();
	public static SubsystemIntake intake = new SubsystemIntake();
	public static SubsystemClimber climber = new SubsystemClimber();
	public static SubsystemSpitter spitter = new SubsystemSpitter();
		
	public void initDefaultCommands() {
		driver.setDefaultCommand(drive.c());
		
		sensors.setDefaultCommand(readSensors.c());
		sensors.resetSensors();
		
		oi.mapOperations();
	}

	public CommandBase getAutonomous(int autoID) {
		return Auto.getAutonomous(autoID);
	}
	
	public void initialiseRobot() {
		if(!hasCameraStarted) {
			CameraServer.getInstance().startAutomaticCapture();
			hasCameraStarted = true;
		}
		hopper.eyebrowsSol.set(true);
	}
	
	public void initialiseTeleop() {
		hopper.eyebrowsSol.set(true);
	}
	
	public void disabledInit() {
		for(PIDController pid : activePIDs) pid.disable();
		activePIDs.clear();
	}
}
