package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;

import static redbacks.robot.RobotMap.*;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

/**
 * @author Sean Zammit
 */
public class SubsystemShooter extends SubsystemBase
{
	public CtrlMotor shooter = new CtrlMotor(talon6);
	public CANTalon shootSlave = talon7;

	public SubsystemShooter() {
		super();
		
		shootSlave.setControlMode(TalonControlMode.Follower.value);
		shootSlave.set(((CANTalon) shooter.controller).getDeviceID());
	}
}
