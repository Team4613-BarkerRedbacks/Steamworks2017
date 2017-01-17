package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;

import static redbacks.robot.RobotMap.*;

/**
 * @author Matthew Brian
 * 
 */
public class SubsystemClimber extends SubsystemBase {

    public CtrlMotor climber = new CtrlMotor(talon10);

    public SubsystemClimber() {
    	super();
    }
}
