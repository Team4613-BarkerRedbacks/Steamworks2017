package redbacks.robot.pid;

import redbacks.robot.RobotMap;
import redbacks.robot.pid.AcMultiPID.PIDAxis;

/**
 * A class to combines the MultiPID results of the drive to distance and gyro to let you drive to a distance and angle.
 * 
 * @author SchwarzT18
 */
public class MultiPIDDriveCombiner extends MultiPIDCombiner
{
	double multiplier, angle, output;
	boolean isLeftMotor;
	
	public MultiPIDDriveCombiner(Boolean isLeftMotor, double multiplier) {
		this.isLeftMotor = isLeftMotor;
		this.multiplier = multiplier;
	}
	
	public MultiPIDDriveCombiner(Boolean isLeftMotor) {
		this(isLeftMotor, 1);
	}

	/**
	 * PIDAxis should be a list where the first value is the speed PID result and the second value is the Gyro PID.
	 * This dosen't check if you're sending it the correct values, and could crash.
	 */
	public double combine(PIDAxis[] axes) {
		output = axes[0].output + axes[1].output;
		
		//If output > 1 or - 1 get difference and put it somewhere for other motor PID loop
		if (output > 1.0D) {
			if(isLeftMotor) {
				RobotMap.multiPIDDriveCombinerLeftOverflow = (output - 1.0D);
			}
			else{
				RobotMap.mutiPIDDriveCombinerRightOverflow = (output - 1.0D);
			}
		}
		else if (output < -1.0D) {
			if(isLeftMotor) {
				RobotMap.multiPIDDriveCombinerLeftOverflow = (output + 1.0D);
			}
			else{
				RobotMap.mutiPIDDriveCombinerRightOverflow = (output + 1.0D);
			}
		}
		
		return isLeftMotor ? (output + RobotMap.mutiPIDDriveCombinerRightOverflow) : (output + RobotMap.multiPIDDriveCombinerLeftOverflow); 
	}
}
