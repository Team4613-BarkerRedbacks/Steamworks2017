package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;
import redbacks.arachne.lib.solenoids.SolSingle;

import static redbacks.robot.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemSpitter extends SubsystemBase
{
	public CtrlMotor spitterMotor = new CtrlMotor(idMotSpit);
	public SolSingle spitterSol = new SolSingle(idSolSpit);

	public SubsystemSpitter() {
		super();
	}
}
