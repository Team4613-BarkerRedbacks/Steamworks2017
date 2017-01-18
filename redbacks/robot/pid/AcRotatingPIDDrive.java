package redbacks.robot.pid;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.robot.Robot;
import redbacks.robot.RobotMap;

public class AcRotatingPIDDrive extends Action 
{
	
	double leftMotorFinalResult = 0, rightMotorFinalResult = 0;
	//TODO remove and replace with final tuned values
	double tempDrivekP = 0.0D, tempDrivekI = 0.0D, tempDrivekD = 0.0D, tempGyrokP = 0.0D, tempGyrokI = 0.0D, tempGyrokD = 0.0D;
	
	RotatingPIDDriveMotor pidDriverLeft = new RotatingPIDDriveMotor();
	RotatingPIDDriveMotor pidDriverRight = new RotatingPIDDriveMotor();
	RotatingPIDDriveGyroManager pidDriverGyro = new RotatingPIDDriveGyroManager();
	
	PIDController pidControllerLeft = new PIDController(RobotMap.drivePIDMotorkP, RobotMap.drivePIDMotorkI, RobotMap.drivePIDMotorkD, Robot.sensors.driveLEncoder, pidDriverLeft);
	PIDController pidControllerRight= new PIDController(RobotMap.drivePIDMotorkP, RobotMap.drivePIDMotorkI, RobotMap.drivePIDMotorkD, Robot.sensors.driveREncoder, pidDriverRight);
	PIDController pidControllerGyro = new PIDController(RobotMap.drivePIDGyrokP, RobotMap.drivePIDGyrokI, RobotMap.drivePIDGyrokD, Robot.sensors.yaw, pidDriverGyro);
	
	public AcRotatingPIDDrive() {
		super(new ChFalse());
	}
	
	public boolean isDone() {
		return (pidControllerLeft.onTarget() && pidControllerRight.onTarget() && pidControllerGyro.onTarget());
	}
	
	public void onStart() {
		//create PID stuff
		Robot.sensors.driveREncoder.setPIDSourceType(PIDSourceType.kDisplacement); //TODO change to dropped wheel encoder
		Robot.sensors.yaw.setPIDSourceType(PIDSourceType.kDisplacement);
		
		pidControllerLeft.setContinuous(false);
		pidControllerRight.setContinuous(false);
		pidControllerGyro.setContinuous(true);
		
		pidControllerLeft.setOutputRange(-1.0D, 1.0D);
		pidControllerRight.setOutputRange(-1.0D, 1.0D);
		pidControllerGyro.setOutputRange(-2.0D,  2.0D);
		
		pidControllerLeft.setPercentTolerance(1.0D); //For some reason WPILib has you send the percent as a whole number. i.e. 15% is 15.0
		pidControllerRight.setPercentTolerance(1.0D);
		pidControllerGyro.setPercentTolerance(1.0D);
		
		pidControllerLeft.setToleranceBuffer(5);
		pidControllerRight.setToleranceBuffer(5);
		pidControllerGyro.setToleranceBuffer(5);
		
		double distance = SmartDashboard.getNumber("RotatingPIDDrive: Distance", 0); //TODO: find better solution or whatever than this testing setup
		double angle= SmartDashboard.getNumber("RotatingPIDDrive: Angle", 0); 
		SmartDashboard.putNumber("RotatingPIDDrive: Distance", distance);
		SmartDashboard.putNumber("RotatingPIDDrive: Angle", angle);
		
		pidControllerLeft.setSetpoint(distance);
		pidControllerRight.setSetpoint(distance);
		pidControllerGyro.setSetpoint(angle);
		
		pidControllerLeft.enable();
		pidControllerRight.enable();
		pidControllerGyro.enable();
	}
	
	public void onRun() {
		System.out.println("Left Motor: " + Robot.driver.left.get() + "  Right Motor: " + Robot.driver.right.get() + "    REncoder: " + Robot.sensors.driveREncoder.get() + "  Gyro: " + Robot.sensors.yaw);
		SmartDashboard.getNumber("tempDrivekP", tempDrivekP);
		SmartDashboard.getNumber("tempDrivekI", tempDrivekI);
		SmartDashboard.getNumber("tempDrivekD", tempDrivekD);
		
		SmartDashboard.getNumber("tempGyrokP", tempGyrokP);
		SmartDashboard.getNumber("tempGyrokI", tempGyrokI);
		SmartDashboard.getNumber("tempGyrokD", tempGyrokD);
		
		pidControllerLeft.setPID(tempDrivekP, tempDrivekI, tempDrivekD);
		pidControllerRight.setPID(tempDrivekP, tempDrivekI, tempDrivekD);
		pidControllerGyro.setPID(tempGyrokP, tempGyrokI, tempGyrokD);
		
	}
	
	public void onFinish() {
		pidControllerLeft.disable();
		pidControllerRight.disable();
		pidControllerGyro.disable();
	}
	
	public void updateMotors(double leftMotorPIDResult, double rightMotorPIDResult, double gyroDifferencePIDResult) {
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
