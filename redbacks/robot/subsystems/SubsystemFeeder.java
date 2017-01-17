package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;

import static redbacks.robot.RobotMap.*;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

/**
 * @author Sean Zammit
 */
public class SubsystemFeeder extends SubsystemBase
{
	public CtrlMotor feeder = new CtrlMotor(talon8);
	protected CANTalon feedSlave = talon9;

	public SubsystemFeeder() {
		super();
		
		feedSlave.setControlMode(TalonControlMode.Follower.value);
		feedSlave.set(((CANTalon) feeder.controller).getDeviceID());
	}
}
