package redbacks.arachne.lib.pid;

import edu.wpi.first.wpilibj.PIDOutput;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.motors.CtrlDrive;
import redbacks.arachne.lib.motors.CtrlMotor;

/**
 * A PID controlled motor, with a controllable multiplier.
 *
 * @author Tom Schwarz, Sean Zammit
 */
public class PIDMotor implements PIDOutput
{
	/** The motor that will be set based on the {@link PIDController} results. */
	public CtrlMotor motor;
	Action action;

	double multiplier = 1;

	/**
	 * Constructor for a PID controlled motor, with a controllable multiplier.
	 * 
	 * @param motor The motor being set.
	 */
	public PIDMotor(CtrlMotor motor) {
		this.motor = motor;
	}

	public void pidWrite(double outputValue) {
		//FIXME Check this doesn't break stuff. EDIT: Look, it broke stuff!
		if(motor instanceof CtrlDrive) ((CtrlDrive) motor).set(outputValue * multiplier);
		else motor.controller.set(outputValue * multiplier);
	}

	/**
	 * Used to get the action, and hence command that is setting the PID output, to be sent to the motor when it is set.
	 * 
	 * @param action The action that is setting the PID output.
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	/**
	 * Used to set a multiplier on the PID output. This can be useful when controlling multiple motors, or when you need to invert the motor.
	 * 
	 * @param multiplier The multiplier to be used on the PID output before it is sent to the motor.
	 * @return This PIDMotor, so that you can call it on the constructor.
	 */
	public PIDMotor setMultiplier(double multiplier) {
		this.multiplier = multiplier;
		return this;
	}
}
