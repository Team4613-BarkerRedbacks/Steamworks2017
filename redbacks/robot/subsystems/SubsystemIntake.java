package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;
import redbacks.arachne.lib.solenoids.SolSingle;

import static redbacks.robot.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemIntake extends SubsystemBase
{
	public CtrlMotor intakeMotor = new CtrlMotor(idMotIntake);
	public SolSingle intakeSol = new SolSingle(idSolIntake);

	public SubsystemIntake() {
		super();
	}
}
