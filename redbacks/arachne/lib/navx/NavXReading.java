package redbacks.arachne.lib.navx;

/**
 * The list of sensor readings from the navX. This is used in {@link NavX.Sensor} to determine the type of sensor to create.
 * These consist of angles, rate of change of angles, speeds and accelerations on all three axes.
 *
 * @author Sean Zammit
 */
public enum NavXReading
{
	ANGLE_PITCH,
	ANGLE_ROLL,
	ANGLE_YAW,

	RATE_PITCH,
	RATE_ROLL,
	RATE_YAW,
	
	ACCEL_FORWARD,
	ACCEL_RIGHT,
	ACCEL_UP,
	
	SPEED_FORWARD,
	SPEED_RIGHT,
	SPEED_UP
}
