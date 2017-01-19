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
	double distance, angle;

	public RotatingPIDDriveMotor pidDriverLeft = new RotatingPIDDriveMotor();
	public RotatingPIDDriveMotor pidDriverRight = new RotatingPIDDriveMotor();
	public RotatingPIDDriveGyroManager pidDriverGyro = new RotatingPIDDriveGyroManager(pidDriverLeft, pidDriverRight);

	PIDController pidControllerLeft = new PIDController(RobotMap.drivePIDMotorkP, RobotMap.drivePIDMotorkI, RobotMap.drivePIDMotorkD, Robot.sensors.driveLEncoderDis, pidDriverLeft);
	PIDController pidControllerRight = new PIDController(RobotMap.drivePIDMotorkP, RobotMap.drivePIDMotorkI, RobotMap.drivePIDMotorkD, Robot.sensors.driveREncoderDis, pidDriverRight);
	PIDController pidControllerGyro = new PIDController(RobotMap.drivePIDGyrokP, RobotMap.drivePIDGyrokI, RobotMap.drivePIDGyrokD, Robot.sensors.yaw, pidDriverGyro);

	public AcRotatingPIDDrive() {
		super(new ChFalse());
	}

	//	public AcRotatingPIDDrive(double distance, double angle) {
	//		super(new ChFalse());
	//		this.distance = distance;
	//		this.angle = angle;
	//	}

	public boolean isDone() {
		return(pidControllerLeft.onTarget() && pidControllerRight.onTarget() && pidControllerGyro.onTarget());
	}

	public void onStart() {
		//create PID stuff
		Robot.sensors.driveREncoderDis.setPIDSourceType(PIDSourceType.kDisplacement); //TODO change to dropped wheel encoder
		Robot.sensors.yaw.setPIDSourceType(PIDSourceType.kDisplacement);

		pidControllerLeft.setContinuous(false);
		pidControllerRight.setContinuous(false);
		pidControllerGyro.setContinuous(true);

		pidControllerLeft.setOutputRange(-1.0D, 1.0D);
		pidControllerRight.setOutputRange(-1.0D, 1.0D);
		pidControllerGyro.setOutputRange(-2.0D, 2.0D);

		pidControllerLeft.setPercentTolerance(1.0D); //For some reason WPILib has you send the percent as a whole number. i.e. 15% is 15.0
		pidControllerRight.setPercentTolerance(1.0D);
		pidControllerGyro.setPercentTolerance(1.0D);

		pidControllerLeft.setToleranceBuffer(5);
		pidControllerRight.setToleranceBuffer(5);
		pidControllerGyro.setToleranceBuffer(5);

		distance = SmartDashboard.getNumber("RotatingPIDDrive: Distance", 0); //TODO: find better solution or whatever than this testing setup
		angle = SmartDashboard.getNumber("RotatingPIDDrive: Angle", 0);

		pidControllerLeft.setPID(RobotMap.drivePIDMotorkP, RobotMap.drivePIDMotorkI, RobotMap.drivePIDMotorkD);
		pidControllerRight.setPID(RobotMap.drivePIDMotorkP, RobotMap.drivePIDMotorkI, RobotMap.drivePIDMotorkD);
		pidControllerGyro.setPID(RobotMap.drivePIDGyrokP, RobotMap.drivePIDGyrokI, RobotMap.drivePIDGyrokI);

		//TEMP, remove plz
		distance = RobotMap.drivePIDtestDistance;
		angle = RobotMap.drivePIDtestAngle;

		pidControllerLeft.setSetpoint(distance);
		pidControllerRight.setSetpoint(distance);
		pidControllerGyro.setSetpoint(angle);

		pidControllerLeft.enable();
		pidControllerRight.enable();
		pidControllerGyro.enable();
	}

	public void onRun() {
		System.out.println("Left Motor: " + Robot.driver.left.get() + "  Right Motor: " + Robot.driver.right.get() + "    REncoder: " + Robot.sensors.driveREncoderDis.get() + "  Gyro: " + Robot.sensors.yaw.get());
	}

	public void onFinish() {
		pidControllerLeft.disable();
		pidControllerRight.disable();
		pidControllerGyro.disable();
	}
}
