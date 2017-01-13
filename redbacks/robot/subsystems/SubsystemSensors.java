package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.sensors.SenCANEncoder;

import static redbacks.robot.Robot.*;

import com.ctre.CANTalon;

/**
 * The sensor subsystem. Only put sensors here.
 * 
 * @author Sean Zammit
 */
public class SubsystemSensors extends SubsystemBase
{
	//Create sensors here. Make sure to use the RobotMap.
	//Drive
	//public SenCANEncoder driveLEncoder = new SenCANEncoder(driver.left);
	//public SenCANEncoder driveREncoder = new SenCANEncoder(driver.right);
	
	public SenCANEncoder testEncoder = new SenCANEncoder(new CANTalon(7));
		
	public SubsystemSensors() {
		super();
	}
}