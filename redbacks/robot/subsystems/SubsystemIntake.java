package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;

import static redbacks.robot.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemIntake extends SubsystemBase
{
	public CtrlMotor intake = new CtrlMotor(talon11);

	public SubsystemIntake() {
		super();
	}
}
