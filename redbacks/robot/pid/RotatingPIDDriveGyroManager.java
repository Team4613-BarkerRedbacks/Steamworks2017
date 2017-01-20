package redbacks.robot.pid;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import redbacks.arachne.lib.motors.CtrlMotor;
import redbacks.robot.CommandList;
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
		System.out.println("1: LMPR" + leftMotorPIDResult + " RMPR" + rightMotorPIDResult +  " GDPR" + gyroDifferencePIDResult + " LMFR" + leftMotorFinalResult + " RMFR" + rightMotorFinalResult + " REV" + Robot.sensors.driveREncoderDis.get() + " LEV" + Robot.sensors.driveLEncoderDis.get() + " GYV" + Robot.sensors.yaw.get());
		leftMotorFinalResult = leftMotorPIDResult + gyroDifferencePIDResult;
		rightMotorFinalResult = rightMotorPIDResult - gyroDifferencePIDResult;
		System.out.println("2: LMPR" + leftMotorPIDResult + " RMPR" + rightMotorPIDResult +  " GDPR" + gyroDifferencePIDResult + " LMFR" + leftMotorFinalResult + " RMFR" + rightMotorFinalResult + " REV" + Robot.sensors.driveREncoderDis.get() + " LEV" + Robot.sensors.driveLEncoderDis.get() + " GYV" + Robot.sensors.yaw.get());
		
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
		
		System.out.println("3: LMPR" + leftMotorPIDResult + " RMPR" + rightMotorPIDResult +  " GDPR" + gyroDifferencePIDResult + " LMFR" + leftMotorFinalResult + " RMFR" + rightMotorFinalResult + " REV" + Robot.sensors.driveREncoderDis.get() + " LEV" + Robot.sensors.driveLEncoderDis.get() + " GYV" + Robot.sensors.yaw.get()); 
		
		Robot.driver.left.set(leftMotorFinalResult*-1, CommandList.rotatingpiddrivetest.c());
		Robot.driver.right.set(rightMotorFinalResult, CommandList.rotatingpiddrivetest.c());
		
	}
}
