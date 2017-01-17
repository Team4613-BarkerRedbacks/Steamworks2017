package redbacks.robot.pid;

import edu.wpi.first.wpilibj.PIDController;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.robot.Robot;


public class AcTestPID extends Action {
	
	double Kp = 0.1;
	double Ki = 0.1;
	double Kd = 0.1;
	
	PIDMotor pidDriverLeft = new PIDMotor(Robot.driver.left);
	PIDMotor pidDriverRight= new PIDMotor(Robot.driver.right);
	
	PIDController pidControllerLeft = new PIDController(Kp, Ki, Kd, Robot.sensors.driveLEncoder, pidDriverLeft);
	PIDController pidControllerRight = new PIDController(Kp, Ki, Kd, Robot.sensors.driveREncoder, pidDriverRight);
	
	public AcTestPID() {
		super(new ChFalse());
	}
	
	public void onStart() {
		pidControllerLeft.setContinuous(false);
		pidControllerRight.setContinuous(false);
		
		pidControllerLeft.setOutputRange(-1.0D, 1.0D);
		pidControllerRight.setOutputRange(-1.0D, 1.0D);
		
		pidControllerLeft.setPercentTolerance(1.0D); //For some reason WPILib has you send the percent as a whole number. i.e. 15% is 0.15
		pidControllerRight.setPercentTolerance(1.0D);
		
		pidControllerLeft.setToleranceBuffer(15);
		pidControllerRight.setToleranceBuffer(15);
		
		pidControllerLeft.setSetpoint(5000.0D);
		pidControllerRight.setSetpoint(5000.0D);
		
		pidControllerLeft.enable();
		pidControllerRight.enable();
	}
	
	public void onFinish() {
		pidControllerLeft.free();
		pidControllerRight.free();
	}
	
	public boolean isDone() {
		return (pidControllerLeft.onTarget() && pidControllerRight.onTarget()) ? true : false;
	}
	
	
	


}
