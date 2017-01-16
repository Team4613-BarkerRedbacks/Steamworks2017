package redbacks.arachne.lib.navx;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import redbacks.arachne.lib.logic.GettableNumber;
import redbacks.arachne.lib.sensors.NumericSensor;

public class NavX
{
	public static AHRS navx = new AHRS(SPI.Port.kMXP);

	//Rotation functions
	public static double getPitch() {
		return navx.getRoll();
	}

	public static double getRatePitch() {
		return navx.getRawGyroY();
	}

	public static double getRoll() {
		return navx.getPitch();
	}

	public static double getRateRoll() {
		return navx.getRawGyroX();
	}

	public static double getYaw() {
		return navx.getYaw();
	}

	public static double getRateYaw() {
		return navx.getRawGyroZ();
	}

	//Acceleration functions
	public static double getAccelForward() {
		return navx.getWorldLinearAccelX();
	}

	public static double getAccelRight() {
		//TEST Axes
		return navx.getWorldLinearAccelY();
	}

	public static double getAccelUp() {
		//TEST Axes
		return navx.getWorldLinearAccelZ();
	}

	//Speed functions
	public static double getSpeedForward() {
		return navx.getVelocityX();
	}

	public static double getSpeedRight() {
		//TEST Axes
		return navx.getVelocityY();
	}

	public static double getSpeedUp() {
		//TEST Axes
		return navx.getVelocityZ();
	}

	public static class Sensor implements GettableNumber
	{
		public NavXReading type;

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
