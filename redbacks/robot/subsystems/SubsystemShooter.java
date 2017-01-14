package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;

import static redbacks.robot.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemShooter extends SubsystemBase
{
	public CtrlMotor shooter = new CtrlMotor(talon6);
	public CtrlMotor feeder = new CtrlMotor(talon8);

	public SubsystemShooter() {
		super();
	}
}
