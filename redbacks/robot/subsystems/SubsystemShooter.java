package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;
import redbacks.arachne.lib.motors.CtrlMotorList;

import static redbacks.robot.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemShooter extends SubsystemBase
{
	CtrlMotor motShootL = new CtrlMotor(idMotShootL);
	CtrlMotor motShootR = new CtrlMotor(idMotShootR);
	
	public CtrlMotorList shooterMotor = new CtrlMotorList(
			motShootL, motShootR
	);
	
	public SubsystemShooter() {
		super();
		
		motShootR.setInverted(true);
	}
}
