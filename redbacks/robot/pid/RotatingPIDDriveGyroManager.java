package redbacks.robot.pid;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import redbacks.arachne.lib.motors.CtrlMotor;
import redbacks.robot.Robot;

public class RotatingPIDDriveGyroManager implements PIDOutput
{
	//	CtrlMotor leftRotatingPIDDriveMotor, rightRotatingPIDDriveMotor;
	//	
	//	public RotatingPIDDriveGyroManager(CtrlMotor leftRotatingPIDDriverMotor, CtrlMotor rightRotatingPIDDriverMotor) {
	//		this.leftRotatingPIDDriveMotor = leftRotatingPIDDriverMotor;
	//		this.rightRotatingPIDDriveMotor = rightRotatingPIDDriverMotor;
	//	}

	RotatingPIDDriveMotor pidDriverLeft, pidDriverRight;
	double leftMotorFinalResult = 0, rightMotorFinalResult = 0;

	public RotatingPIDDriveGyroManager(RotatingPIDDriveMotor pidDriverLeft, RotatingPIDDriveMotor pidDriverRight) {
		this.pidDriverLeft = pidDriverLeft;
		this.pidDriverRight = pidDriverRight;
	}

	public void pidWrite(double output) {
		this.updateMotors(pidDriverLeft.motorValue, pidDriverRight.motorValue, output);
	}

	public void updateMotors(double leftMotorPIDResult, double rightMotorPIDResult, double gyroDifferencePIDResult) {
		SmartDashboard.putNumber("leftMotorPIDResult", leftMotorPIDResult);
		SmartDashboard.putNumber("rightMotorPIDResult",  rightMotorPIDResult);
		SmartDashboard.putNumber("gyroDifferencePIDResult",  gyroDifferencePIDResult);
		leftMotorFinalResult = leftMotorPIDResult + gyroDifferencePIDResult;
		rightMotorFinalResult = rightMotorPIDResult - gyroDifferencePIDResult;
		
		if (leftMotorFinalResult > 1.0D) {
			rightMotorFinalResult -= leftMotorFinalResult - 1.0D;
			leftMotorFinalResult = 1.0D;
		}
		else if (leftMotorFinalResult < -1.0D) {
			rightMotorFinalResult -= rightMotorFinalResult + 1.0D;
		}
		
		if (rightMotorFinalResult > 1.0D) {
			leftMotorFinalResult -= rightMotorFinalResult - 1.0D;
			rightMotorFinalResult = 1.0D;
		}
		else if (rightMotorFinalResult < -1.0D) {
			leftMotorFinalResult -= leftMotorFinalResult + 1.0D;
		}
		
		Robot.driver.left.set(leftMotorFinalResult);
		Robot.driver.right.set(rightMotorFinalResult);
		
	}
}
