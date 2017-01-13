package redbacks.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlDrive;
import redbacks.arachne.lib.motors.CtrlDrivetrain;

/**
 * @author Sean Zammit
 */
public class SubsystemDriver extends SubsystemBase
{
	//Set up motors and solenoids here. Make sure to use the RobotMap.
	public CtrlDrive left = new CtrlDrive(new CANTalon(3));
	public CtrlDrive right = new CtrlDrive(new CANTalon(2));
	
	protected CANTalon lSlave1 = new CANTalon(5);
	protected CANTalon rSlave1 = new CANTalon(4);

	public CtrlDrivetrain drivetrain = new CtrlDrivetrain(left, right);
	
	public SubsystemDriver() {
		super();
		//Left side
		lSlave1.setControlMode(TalonControlMode.Follower.value);		
		lSlave1.set(((CANTalon) left.controller).getDeviceID());
		
		//Right side
		rSlave1.setControlMode(TalonControlMode.Follower.value);		
		rSlave1.set(((CANTalon) right.controller).getDeviceID());
	}
}
