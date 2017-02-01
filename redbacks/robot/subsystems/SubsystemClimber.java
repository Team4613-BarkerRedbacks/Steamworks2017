package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;
import redbacks.arachne.lib.motors.CtrlMotorList;

import static redbacks.robot.RobotMap.*;

/**
 * @author Matthew Brian, Sean Zammit
 */
public class SubsystemClimber extends SubsystemBase
{
	CtrlMotor motClimbL = new CtrlMotor(idMotClimbL);
	CtrlMotor motClimbR = new CtrlMotor(idMotClimbR);
	
	public CtrlMotorList climberMotor = new CtrlMotorList(
			motClimbL, motClimbR
	);
	
	public SubsystemClimber() {
		super();
		
		motClimbR.setInverted(true);
	}
}
