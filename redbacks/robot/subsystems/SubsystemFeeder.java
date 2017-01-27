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
	public CtrlMotor feederMotor = new CtrlMotor(idMotFeed1);
	protected CANTalon feederSlave = idMotFeed2;

	public SubsystemFeeder() {
		super();
		
		feederSlave.setControlMode(TalonControlMode.Follower.value);
		feederSlave.set(((CANTalon) feederMotor.controller).getDeviceID());
	}
}
