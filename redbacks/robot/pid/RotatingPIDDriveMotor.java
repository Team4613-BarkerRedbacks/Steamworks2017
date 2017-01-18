package redbacks.robot.pid;

import edu.wpi.first.wpilibj.PIDOutput;
import redbacks.arachne.lib.motors.CtrlMotor;

/**
 * A dummy class which holds a motor speed and the motor that it should be applied to.
 * 
 * @author SchwarzT18
 */
public class RotatingPIDDriveMotor implements PIDOutput {
	
	
//	public CtrlMotor motor;
	public double motorValue = 0.0D;
	
//	public RotatingPIDDriveMotor(CtrlMotor motor) {
//		this.motor = motor;
//	}

	public void pidWrite(double output) {
		this.motorValue = output;
	}
	
	
	

}
