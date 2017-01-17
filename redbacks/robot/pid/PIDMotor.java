package redbacks.robot.pid;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import redbacks.arachne.lib.actions.actuators.AcMotor;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.motors.CtrlMotor;

public class PIDMotor implements PIDOutput {
	CtrlMotor motor;
	
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
		new AcMotor.Set(motor, outputValue,  new ChFalse());
	}
}
