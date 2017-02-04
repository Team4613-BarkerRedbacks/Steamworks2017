package redbacks.robot.actions;

import static edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.*;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.robot.RobotMap;

import static redbacks.robot.Robot.*;

public class AcReadSensors extends Action
{
	public AcReadSensors() {
		super(new ChFalse());
	}

	public void onRun() { //Runs every loop
		putNumber("Left Encoder", sensors.leftEncoderDis.get());
		putNumber("Right Encoder", sensors.rightEncoderDis.get());
		putNumber("Centre Encoder", sensors.centreEncoderDis.get());
		putNumber("Distance", sensors.centreEncoderDis.get() / RobotMap.encoderTicksPerMetre);

		putNumber("Shooter Speed", sensors.shooterEncoderRate.get());
		putNumber("Intake Speed", sensors.intakeEncoderRate.get());
		
		putBoolean("Gear Light", sensors.gearLight.get());
		putBoolean("Shooter Light", sensors.shooterLight.get());
		
		putNumber("Yaw", sensors.yaw.get());
	}	
}
