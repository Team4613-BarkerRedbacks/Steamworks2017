package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;

import static redbacks.robot.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemSpitter extends SubsystemBase
{
	public CtrlMotor spitter = new CtrlMotor(talon12);

	public SubsystemSpitter() {
		super();
	}
}
