package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;
import redbacks.arachne.lib.motors.CtrlMotorList;
import redbacks.arachne.lib.solenoids.SolSingle;

import static redbacks.robot.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemIntake extends SubsystemBase
{
	public CtrlMotor motIntakeF = new CtrlMotor(idMotIntakeF);
	public CtrlMotor motIntakeB = new CtrlMotor(idMotIntakeB);
	
	public CtrlMotorList intakeMotor = new CtrlMotorList(
			motIntakeF, motIntakeB
	);
	
	public SolSingle intakeSol = new SolSingle(idSolIntake);
	
	public SubsystemIntake() {
		super();
		
		motIntakeB.setInverted(true);
	}
}
