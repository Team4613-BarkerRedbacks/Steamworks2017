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

	int i = 0;
	
	public void onRun() { //Runs every loop
		putNumber("Left Encoder", sensors.leftEncoderDis.get());
		putNumber("Right Encoder", sensors.rightEncoderDis.get());
		putNumber("Centre Encoder", sensors.centreEncoderDis.get());
		putNumber("Distance", sensors.centreEncoderDis.get() / RobotMap.encoderTicksPerMetre);
		
		putNumber("Shooter Speed", sensors.shooterEncoderRate.get());
		
//		if(i == 0) 
//			System.out.println(sensors.shooterEncoderRate.get());
//		i++;
//		i %= 6;
		System.out.println(intake.intakeMotor.get());
		
		putNumber("Intake Speed", intake.intakeMotor.get());
		
		putBoolean("Gear Light", sensors.gearLight.get());
//		putBoolean("Shooter Light", sensors.shooterLight.get());
		
		putNumber("Yaw", sensors.yaw.get());
	}	
}
