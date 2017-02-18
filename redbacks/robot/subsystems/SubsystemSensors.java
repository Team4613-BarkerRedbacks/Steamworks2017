package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.navx.*;
import redbacks.arachne.lib.sensors.*;

import static redbacks.robot.RobotMap.*;

import com.ctre.CANTalon.StatusFrameRate;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The sensor subsystem. Only put sensors here.
 * 
 * @author Sean Zammit
 */
public class SubsystemSensors extends SubsystemBase
{
	//Drive
	public SenCANEncoder.Displacement rightEncoderDis = new SenCANEncoder.Displacement(talon2);
	public SenCANEncoder.Rate rightEncoderRate = new SenCANEncoder.Rate(talon2);

	public SenCANEncoder.Displacement leftEncoderDis = new SenCANEncoder.Displacement(talon3);
	public SenCANEncoder.Rate leftEncoderRate = new SenCANEncoder.Rate(talon3);

	public SenCANEncoder.Displacement centreEncoderDis = new SenCANEncoder.Displacement(talon4);
	public SenCANEncoder.Rate centreEncoderRate = new SenCANEncoder.Rate(talon4);
	
	//Shooter
	public SenCANEncoder.Rate shooterEncoderRateL = new SenCANEncoder.Rate(idMotShootL);
	public SenCANEncoder.Rate shooterEncoderRateR = new SenCANEncoder.Rate(idMotShootR);
//	public SenCANDigitalInput shooterLight = new SenCANDigitalInput(talon6, true);
	
	//Spitter
	public SenDI gearLight = new SenDI(new DigitalInput(0));
	
	//Intake
	public SenCANEncoder intakeEncoderRate = new SenCANEncoder.Rate(talon14);
	
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
		rightEncoderDis.setScaleFactor(-1);
		rightEncoderRate.setScaleFactor(-1);
		
		centreEncoderDis.setScaleFactor(-1);
		centreEncoderRate.setScaleFactor(-1);
		
		shooterEncoderRateR.setScaleFactor(-1);
		
		resetSensors();

		idMotShootR.setStatusFrameRateMs(StatusFrameRate.QuadEncoder, 10);
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
		leftEncoderDis.set(0);
		rightEncoderDis.set(0);
		centreEncoderDis.set(0);
		yaw.set(0);
	}
}