package redbacks.arachne.lib.pid;

import edu.wpi.first.wpilibj.PIDOutput;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.motors.CtrlMotor;

public class PIDMotor implements PIDOutput
{
	public CtrlMotor motor;
	Action action;

	double multiplier = 1;

	public PIDMotor(CtrlMotor motor) {
		this.motor = motor;
	}

	/**
	 * Used for the WPILib PIDController class to be able to control the motor
	 * 
	 * @param outputValue The speed of the motor.
	 * @author Tom Schwarz
	 */
	public void pidWrite(double outputValue) {
		motor.set(outputValue * multiplier, action.command);
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public PIDMotor setMultiplier(double multiplier) {
		this.multiplier = multiplier;
		return this;
	}
}
