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
	public SenCANEncoder driveLEncoder = new SenCANEncoder(talon7);
	public SenCANEncoder driveREncoder = new SenCANEncoder(talon6);

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
		driveREncoder.setScaleFactor(-1);

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
		driveLEncoder.set(0);
		driveREncoder.set(0);
		yaw.set(0);
	}
}