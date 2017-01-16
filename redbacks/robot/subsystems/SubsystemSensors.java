package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.navx.NavX;
import redbacks.arachne.lib.sensors.SenCANEncoder;

import static redbacks.robot.RobotMap.*;

/**
 * The sensor subsystem. Only put sensors here.
 * 
 * @author Sean Zammit
 */
public class SubsystemSensors extends SubsystemBase
{
	//Drive
	public SenCANEncoder driveLEncoder = new SenCANEncoder(talon7);
	public SenCANEncoder driveREncoder = new SenCANEncoder(talon6);
	
	//NavX
	public NavX.Yaw yaw = new NavX.Yaw();
		
	public SubsystemSensors() {
		super();
		driveREncoder.setScaleFactor(-1);
		
		resetSensors();
	}
	
	public void resetSensors() {
		driveLEncoder.set(0);
		driveREncoder.set(0);
		yaw.set(0);
	}
}