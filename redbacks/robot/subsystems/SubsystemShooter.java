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
	public CtrlMotor shooterMotor = new CtrlMotor(idMotShoot1);
	public CANTalon shooterSlave = idMotShoot2;

	public SubsystemShooter() {
		super();
		
		shooterSlave.setControlMode(TalonControlMode.Follower.value);
		shooterSlave.set(((CANTalon) shooterMotor.controller).getDeviceID());
	}
}
