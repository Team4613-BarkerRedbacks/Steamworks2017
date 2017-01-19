package redbacks.robot.actions;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.navx.NavX;
import static redbacks.robot.Robot.*;

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
		sensors.driveLEncoder.setPIDSourceType(PIDSourceType.kRate);
		
		
		
	}
	
	public void onRun() { //Runs every loop
		SmartDashboard.putNumber("Left Encoder", sensors.driveLEncoder.get());
		SmartDashboard.putNumber("Right Encoder", sensors.driveREncoder.get());
		
		SmartDashboard.putString("Source type", sensors.driveLEncoder.getPIDSourceType().toString());
		SmartDashboard.putNumber("Shooter speed", sensors.driveLEncoder.pidGet());
		
		if (Timer.getFPGATimestamp()-driveEncoder_TimeLastRun > 0.05) {//Maximum updating speed of 20hz
			SmartDashboard.putNumber("Left Motor Speed", driveEncoder_CalculateSpeed(sensors.driveLEncoder.get(), driveLEncoder_ValueLastRun, Timer.getFPGATimestamp(), driveEncoder_TimeLastRun));
			SmartDashboard.putNumber("Right Motor Speed", driveEncoder_CalculateSpeed(sensors.driveREncoder.get(), driveREncoder_ValueLastRun, Timer.getFPGATimestamp(), driveEncoder_TimeLastRun));
			//SmartDashboard.putNumber("Left Motor Distance Travelled", driveEncoder_CalculateDistance(sensors.driveLEncoder.get()));
			//SmartDashboard.putNumber("Right Motor Distance Travelled", driveEncoder_CalculateDistance(sensors.driveREncoder.get()));
			//System.out.println("Left Motor Speed" + driveEncoder_CalculateSpeed(sensors.driveLEncoder.get(), driveLEncoder_ValueLastRun, Timer.getFPGATimestamp(), driveEncoder_TimeLastRun));
			//System.out.println("Right Motor Speed" + driveEncoder_CalculateSpeed(sensors.driveREncoder.get(), driveREncoder_ValueLastRun, Timer.getFPGATimestamp(), driveEncoder_TimeLastRun));
			//System.out.println("Left Encoder: " + (sensors.driveLEncoder.get() - driveLEncoder_ValueLastRun));
			//System.out.println("Right Encoder: " + (sensors.driveREncoder.get() - driveREncoder_ValueLastRun));
			SmartDashboard.putNumber("Veloxity X forward", NavX.getSpeedForward());
			SmartDashboard.putNumber("Veloxity Y rightleft", NavX.getSpeedRight());
			SmartDashboard.putNumber("Veloxity Z Upvertical", NavX.getSpeedUp());
			
			System.out.println("Motor speed: " + shooter.shooter.get());
			
			driveEncoder_UpdateMeasurements();
		}
		
		SmartDashboard.putNumber("Yaw", sensors.yaw.get());
		
	}

	/**
	 * Calculates the speed that (inputed) drive motor is travelling at in meters/second.
	 * 
	 * Assumes no slippage, adding error.
	 * Gets encoder value from sensors.driveLEncoder.get()
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
		this.driveLEncoder_ValueLastRun = sensors.driveLEncoder.get();
		this.driveREncoder_ValueLastRun = sensors.driveREncoder.get();
	}
	
}
