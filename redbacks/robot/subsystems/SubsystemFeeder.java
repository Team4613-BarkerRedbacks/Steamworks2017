package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;

import static redbacks.robot.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemFeeder extends SubsystemBase
{
	public CtrlMotor feeder = new CtrlMotor(talon8);

	public SubsystemFeeder() {
		super();
	}
}
