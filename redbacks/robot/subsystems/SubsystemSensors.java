package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.navx.*;
import redbacks.arachne.lib.sensors.SenCANEncoder;

import static redbacks.robot.RobotMap.*;

/**
 * The sensor subsystem. Only put sensors here.
 * 
 * @author Sean Zammit
 */
public class SubsystemSensors extends SubsystemBase
{
	//Drive
	public SenCANEncoder.Displacement driveREncoderDis = new SenCANEncoder.Displacement(talon6);
	public SenCANEncoder.Rate driveREncoderRate = new SenCANEncoder.Rate(talon6);

	public SenCANEncoder.Displacement driveLEncoderDis = new SenCANEncoder.Displacement(talon7);
	public SenCANEncoder.Rate driveLEncoderRate = new SenCANEncoder.Rate(talon7);
	
	//NavX
	public NavX.Sensor pitch = new NavX.Sensor(NavXReading.ANGLE_PITCH);
	public NavX.Sensor roll = new NavX.Sensor(NavXReading.ANGLE_ROLL);
	public NavX.Yaw yaw = new NavX.Yaw();

	public NavX.Sensor ratePitch = new NavX.Sensor(NavXReading.RATE_PITCH);
	public NavX.Sensor rateRoll = new NavX.Sensor(NavXReading.RATE_ROLL);
	public NavX.Sensor rateYaw = new NavX.Sensor(NavXReading.RATE_YAW);

	public NavX.Sensor accelForward = new NavX.Sensor(NavXReading.ACCEL_FORWARD);
	public NavX.Sensor accelRight = new NavX.Sensor(NavXReading.ACCEL_RIGHT);
	public NavX.Sensor accelUp = new NavX.Sensor(NavXReading.ACCEL_UP);

	public NavX.Sensor speedForward = new NavX.Sensor(NavXReading.SPEED_FORWARD);
	public NavX.Sensor speedRight = new NavX.Sensor(NavXReading.SPEED_RIGHT);
	public NavX.Sensor speedUp = new NavX.Sensor(NavXReading.SPEED_UP);

	public SubsystemSensors() {
		super();
		driveREncoderDis.setScaleFactor(-1);
		driveREncoderRate.setScaleFactor(-1);

		resetSensors();
	}

	public class AcReset extends Action
	{
		public AcReset() {
			super(new ChTrue());
		}
		
		public void onFinish() {
			resetSensors();
		}
	}

	public void resetSensors() {
		driveLEncoderDis.set(0);
		driveREncoderDis.set(0);
		yaw.set(0);
	}
}