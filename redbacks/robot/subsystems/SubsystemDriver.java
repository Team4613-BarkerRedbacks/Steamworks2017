package redbacks.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.motors.CtrlDrive;
import redbacks.arachne.lib.motors.CtrlDrivetrain;
import static redbacks.robot.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemDriver extends SubsystemBase
{
	//Set up motors and solenoids here. Make sure to use the RobotMap.
	public CtrlDrive leftMotor = new CtrlDrive(idMotDriveLF);
	public CtrlDrive rightMotor = new CtrlDrive(idMotDriveRF);

	protected CANTalon leftSlave = idMotDriveLB;
	protected CANTalon rightSlave = idMotDriveRB;

	public CtrlDrivetrain drivetrain = new CtrlDrivetrain(leftMotor, rightMotor);

	public SubsystemDriver() {
		super();
		//Left side
		leftSlave.setControlMode(TalonControlMode.Follower.value);
		leftSlave.set(((CANTalon) leftMotor.controller).getDeviceID());

		//Right side
		rightSlave.setControlMode(TalonControlMode.Follower.value);
		rightSlave.set(((CANTalon) rightMotor.controller).getDeviceID());
	}

	public class AcInvertDrive extends Action
	{
		public AcInvertDrive() {
			super(new ChTrue());
		}

		public void onFinish() {
			SmartDashboard.putBoolean("Invert Drive", !SmartDashboard.getBoolean("Invert Drive", false));
		}
	}
}
