package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;

import static redbacks.robot.RobotMap.*;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

/**
 * @author Matthew Brian
 * 
 */
public class SubsystemClimber extends SubsystemBase {

    public CtrlMotor climberMotor = new CtrlMotor(idMotClimb1);
	protected CANTalon climberSlave = idMotClimb2;

    public SubsystemClimber() {
    	super();
		
    	climberSlave.setControlMode(TalonControlMode.Follower.value);
    	climberSlave.set(((CANTalon) climberMotor.controller).getDeviceID());
    	climberSlave.setInverted(true);
    }
}
