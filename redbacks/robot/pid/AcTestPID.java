package redbacks.robot.pid;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.robot.Robot;


public class AcTestPID extends Action {
	
	double Kp = 0.001;
	double Ki = 0;
	double Kd = 0;
	
	PIDMotor pidDriverLeft = new PIDMotor(Robot.driver.left).setMultiplier(-1);
	PIDMotor pidDriverRight= new PIDMotor(Robot.driver.right);
	
	PIDController pidControllerLeft = new PIDController(Kp, Ki, Kd, Robot.sensors.driveREncoderDis, pidDriverLeft);
	PIDController pidControllerRight = new PIDController(Kp, Ki, Kd, Robot.sensors.driveREncoderDis, pidDriverRight);
	
	public AcTestPID() {
		super(new ChFalse());
		pidDriverLeft.setAction(this);
		pidDriverRight.setAction(this);
	}
	
	public void onStart() {
		Robot.sensors.driveREncoderDis.setPIDSourceType(PIDSourceType.kDisplacement);
		Robot.isIndivDriveControl = true;
		
		pidControllerLeft.setContinuous(false);
		pidControllerRight.setContinuous(false);
		
		pidControllerLeft.setOutputRange(-1.0D, 1.0D);
		pidControllerRight.setOutputRange(-1.0D, 1.0D);
		
		pidControllerLeft.setPercentTolerance(1.0D); //For some reason WPILib has you send the percent as a whole number. i.e. 15% is 15.0
		pidControllerRight.setPercentTolerance(1.0D);
		
		pidControllerLeft.setToleranceBuffer(15);
		pidControllerRight.setToleranceBuffer(15);
		
		double distance = SmartDashboard.getNumber("Distance", 0);
		SmartDashboard.putNumber("Distance", distance);
		
		pidControllerLeft.setSetpoint(distance);
		pidControllerRight.setSetpoint(distance);
		
		pidControllerLeft.enable();
		pidControllerRight.enable();
	}
	
	public void onRun() {
		System.out.println(pidControllerLeft.get() + ", " +  pidControllerRight.get());
		System.out.println(Robot.sensors.driveREncoderDis.pidGet());
		double p = SmartDashboard.getNumber("P", 0), i = SmartDashboard.getNumber("I", 0), d = SmartDashboard.getNumber("D", 0);
		SmartDashboard.putNumber("P", p);
		SmartDashboard.putNumber("I", i);
		SmartDashboard.putNumber("D", d);
		pidControllerLeft.setPID(p, i, d);
		pidControllerRight.setPID(p, i, d);
		
	}
	
	public void onFinish() {
		pidControllerLeft.disable();
		pidControllerRight.disable();
		Robot.isIndivDriveControl = false;
	}
	
	public boolean isDone() {
		return false;//(pidControllerLeft.onTarget() && pidControllerRight.onTarget()) ? true : false;
	}
}
