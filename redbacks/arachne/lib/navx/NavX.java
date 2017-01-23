package redbacks.arachne.lib.navx;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import redbacks.arachne.lib.logic.GettableNumber;
import redbacks.arachne.lib.sensors.NumericSensor;

/**
 * NavX holds the classes that are used to create {@link NumericSensor NumericSensors} for the navX reading, {@link NavX.Sensor} and {@link NavX.Yaw}.
 * Depending on the orientation of the navX on the robot, it also allows you to set which axis is which.
 *
 * @author Sean Zammit
 */
public class NavX
{
	private static AHRS navx = new AHRS(SPI.Port.kMXP);

	/**
	 * Gets the current pitch angle of the navX.
	 * 
	 * @return the current pitch angle of the navX.
	 */
	public static double getPitch() {
		return navx.getRoll();
	}

	/**
	 * Gets the current rate of change in pitch angle of the navX.
	 * 
	 * @return the current rate of change in pitch angle of the navX.
	 */
	public static double getRatePitch() {
		return navx.getRawGyroY();
	}

	/**
	 * Gets the current roll angle of the navX.
	 * 
	 * @return the current roll angle of the navX.
	 */
	public static double getRoll() {
		return navx.getPitch();
	}

	/**
	 * Gets the current rate of change in roll angle of the navX.
	 * 
	 * @return the current rate of change in roll angle of the navX.
	 */
	public static double getRateRoll() {
		return navx.getRawGyroX();
	}

	/**
	 * Gets the current yaw angle of the navX.
	 * 
	 * @return the current yaw angle of the navX.
	 */
	public static double getYaw() {
		return navx.getYaw();
	}

	/**
	 * Gets the current rate of change in yaw angle of the navX.
	 * 
	 * @return the current rate of change in yaw angle of the navX.
	 */
	public static double getRateYaw() {
		return navx.getRawGyroZ();
	}

	/**
	 * Gets the current forward acceleration of the navX.
	 * 
	 * @return the current forward acceleration of the navX.
	 */
	public static double getAccelForward() {
		return navx.getWorldLinearAccelX();
	}

	/**
	 * Gets the current acceleration across the navX to the right.
	 * 
	 * @return the current acceleration across the navX to the right.
	 */
	public static double getAccelRight() {
		return -navx.getWorldLinearAccelY();
	}

	/**
	 * Gets the current upwards acceleration of the navX.
	 * 
	 * @return the current upwards acceleration of the navX.
	 */
	public static double getAccelUp() {
		return navx.getWorldLinearAccelZ();
	}

	/**
	 * Gets the current forward speed of the navX.
	 * 
	 * @return the current forward speed of the navX.
	 */
	public static double getSpeedForward() {
		return navx.getVelocityX();
	}

	/**
	 * Gets the current speed across the navX to the right.
	 * 
	 * @return the current speed across the navX to the right.
	 */
	public static double getSpeedRight() {
		return -navx.getVelocityY();
	}

	/**
	 * Gets the current upwards speed of the navX.
	 * 
	 * @return the current upwards speed of the navX.
	 */
	public static double getSpeedUp() {
		return navx.getVelocityZ();
	}

	/**
	 * A sensor representation of a navX reading, so it can be used as a {@link NumericSensor}.
	 * To create a sensor for the yaw angle, use {@link NavX.Yaw}.
	 *
	 * @author Sean Zammit
	 */
	public static class Sensor implements GettableNumber
	{
		/** The type of reading you want to create a sensor for. */
		public NavXReading type;

		/**
		 * Constructor for a sensor representation of a navX reading, so it can be used as a {@link NumericSensor}.
		 * 
		 * @param type The type of reading you want to create a sensor for. See {@link NavXReading}.
		 */
		public Sensor(NavXReading type) {
			this.type = type;
		}

		public double get() {
			switch(type) {
				case ACCEL_FORWARD:
					return getAccelForward();
				case ACCEL_RIGHT:
					return getAccelRight();
				case ACCEL_UP:
					return getAccelUp();

				case ANGLE_PITCH:
					return getPitch();
				case ANGLE_ROLL:
					return getRoll();
				case ANGLE_YAW:
					return getYaw();

				case RATE_PITCH:
					return getRatePitch();
				case RATE_ROLL:
					return getRateRoll();
				case RATE_YAW:
					return getRateYaw();

				case SPEED_FORWARD:
					return getSpeedForward();
				case SPEED_RIGHT:
					return getSpeedRight();
				case SPEED_UP:
					return getSpeedUp();

				default:
					return getYaw();
			}
		}
	}

	/**
	 * A sensor representation of a navX yaw reading, so it can be used as a {@link NumericSensor}.
	 * To create a sensor for any other angle, use {@link NavX.Sensor}.
	 *
	 * @author Sean Zammit
	 */
	public static class Yaw extends NumericSensor
	{
		protected double getSenVal() {
			float yaw = (float) (getYaw() + offset);
			if(yaw < -180) {
				yaw += 360;
			}
			if(yaw > 180) {
				yaw -= 360;
			}
			return yaw;
		}
		
		public void set(double value) {
			if(value == 0) {
				offset = 0;
				navx.reset();
			}
			else super.set(value);
		}
	}
}
