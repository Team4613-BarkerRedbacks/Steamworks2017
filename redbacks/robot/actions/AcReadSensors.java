package redbacks.robot.actions;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.navx.NavX;
import redbacks.robot.CommandList;
import redbacks.robot.Robot;
import redbacks.robot.RobotMap;

public class AcReadSensors extends Action
{
	
	private double driveEncoder_TimeLastRun;
	private double driveLEncoder_ValueLastRun;
	private double driveREncoder_ValueLastRun;
	
	public AcReadSensors() {
		super(new ChFalse());
	}

	public void onStart() {
		driveEncoder_UpdateMeasurements(); //Run first time to initialize variables
		//CameraServer.getInstance().startAutomaticCapture();
		Robot.sensors.driveLEncoder.setPIDSourceType(PIDSourceType.kRate);
		SmartDashboard.putNumber("Shooter kP", 0);
		SmartDashboard.putNumber("Shooter kI", 0);
		SmartDashboard.putNumber("Shooter kD", 0);
		SmartDashboard.putNumber("Shooter target", 0);
		Robot.shooter.shooter.setInverted(true);
		
		SmartDashboard.putNumber("RotatingPIDDrive: DistanceTarget", Robot.sensors.driveREncoder.get());
		SmartDashboard.putNumber("RotatingPIDDrive: AngleTarget", Robot.sensors.yaw.get());
		
		SmartDashboard.putNumber("tempDrivekP", 0.0);
		SmartDashboard.putNumber("tempDrivekI", 0.0);
		SmartDashboard.putNumber("tempDrivekD", 0.0D);
		
		SmartDashboard.putNumber("tempGyrokP", 0.0);
		SmartDashboard.putNumber("tempGyrokI", 0.0);
		SmartDashboard.putNumber("tempGyrokD", 0.0);
		
	}
	
	public void onRun() { //Runs every loop
		SmartDashboard.putNumber("Left Encoder", Robot.sensors.driveLEncoder.get());
		SmartDashboard.putNumber("Right Encoder", Robot.sensors.driveREncoder.get());
		
		if (Timer.getFPGATimestamp()-driveEncoder_TimeLastRun > 0.05) {//Maximum updating speed of 20hz
			SmartDashboard.putNumber("Left Motor Speed", driveEncoder_CalculateSpeed(Robot.sensors.driveLEncoder.get(), driveLEncoder_ValueLastRun, Timer.getFPGATimestamp(), driveEncoder_TimeLastRun));
			SmartDashboard.putNumber("Right Motor Speed", driveEncoder_CalculateSpeed(Robot.sensors.driveREncoder.get(), driveREncoder_ValueLastRun, Timer.getFPGATimestamp(), driveEncoder_TimeLastRun));
			SmartDashboard.putNumber("Veloxity X forward", NavX.getSpeedForward());
			SmartDashboard.putNumber("Veloxity Y rightleft", NavX.getSpeedRight());
			SmartDashboard.putNumber("Veloxity Z Upvertical", NavX.getSpeedUp());
			
			System.out.println("Motor speed: " + Robot.shooter.shooter.get());
			
			driveEncoder_UpdateMeasurements();
		}
		
		SmartDashboard.putNumber("Yaw", Robot.sensors.yaw.get());
		
		RobotMap.drivePIDMotorkP = SmartDashboard.getNumber("tempDrivekP", 0.0);
		RobotMap.drivePIDMotorkI = SmartDashboard.getNumber("tempDrivekI", 0.0);
		RobotMap.drivePIDMotorkD = SmartDashboard.getNumber("tempDrivekD", 0.0);
		
		RobotMap.drivePIDGyrokP = SmartDashboard.getNumber("tempGyrokP", 0.0);
		RobotMap.drivePIDGyrokI = SmartDashboard.getNumber("tempGyrokI", 0.0);
		RobotMap.drivePIDGyrokD = SmartDashboard.getNumber("tempGyrokD", 0.0);
		
		RobotMap.drivePIDtestDistance = SmartDashboard.getNumber("RotatingPIDDrive: DistanceTarget", 0.0);
		RobotMap.drivePIDtestAngle = SmartDashboard.getNumber("RotatingPIDDrive: AngleTarget", 0.0);
		
	}

	/**
	 * Calculates the speed that (inputed) drive motor is travelling at in meters/second.
	 * 
	 * Assumes no slippage, adding error.
	 * Gets encoder value from Robot.sensors.driveLEncoder.get()
	 * 
	 * @param encoderCurrent double of current encoder value
	 * @param encoderPrevious double of previous encoder value (encoder value must have been set at same time as timePrevious set)
	 * @param timeCurrent double of the current time - i.e. Timer.getFPGATimestamp()
	 * @param timePrevious double of the previous time value (time value must have been set at same time as encoderPrevious set)
	 * 
	 * @return double of left motor speed in m/s
	 * 
	 * @author Tom Schwarz
	 */
	private double driveEncoder_CalculateSpeed(double encoderCurrent, double encoderPrevious, double timeCurrent, double timePrevious) {
		return (Math.PI* 0.0000982265625*(encoderCurrent-encoderPrevious))/(timeCurrent - timePrevious); //Simplified & optimized version of the equation "(circumference of wheel*wheelRotationsPassed)/timePassed"
	}
	
	
	//TODO add javadoc
	private double driveEncoder_CalculateDistance(double encoderCurrent) {
		return(Math.PI*0.0000982265625*encoderCurrent);
	}
	
	
	/**
	 * Sets the values of the below functions to their current measurement for future calculations, in driveEncoder_CalculateSpeed, on the next loop (of onRun() in AcReadSensors.java)
	 * 
	 * Sets driveEncoder_TimeLastRun to current time
	 * Sets driveLEncoder_ValueLastRun to total left encoder pulses
	 * Sets driveREncoder_ValueLastRun to total right encoder pulses
	 * 
	 * @author Tom Schwarz
	 */
	private void  driveEncoder_UpdateMeasurements() {
		this.driveEncoder_TimeLastRun = Timer.getFPGATimestamp();
		this.driveLEncoder_ValueLastRun = Robot.sensors.driveLEncoder.get();
		this.driveREncoder_ValueLastRun = Robot.sensors.driveREncoder.get();
	}
	
}
