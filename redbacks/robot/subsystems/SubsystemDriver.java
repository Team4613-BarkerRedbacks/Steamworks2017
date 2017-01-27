package redbacks.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlDrive;
import redbacks.arachne.lib.motors.CtrlDrivetrain;
import static redbacks.robot.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemDriver extends SubsystemBase
{
	//Set up motors and solenoids here. Make sure to use the RobotMap.
	public CtrlDrive leftMotor = new CtrlDrive(idMotLDrive1);
	public CtrlDrive rightMotor = new CtrlDrive(idMotRDrive1);

	protected CANTalon leftSlave = idMotLDrive2;
	protected CANTalon rightSlave = idMotRDrive2;

	public CtrlDrivetrain drivetrain = new CtrlDrivetrain(leftMotor, rightMotor);

	public SubsystemDriver() {
		super();
		//Left side
		leftSlave.setControlMode(TalonControlMode.Follower.value);
		leftSlave.set(((CANTalon) leftMotor.controller).getDeviceID());

		//Right side
		rightSlave.setControlMode(TalonControlMode.Follower.value);
		rightSlave.set(((CANTalon) rightMotor.controller).getDeviceID());
	}
}
