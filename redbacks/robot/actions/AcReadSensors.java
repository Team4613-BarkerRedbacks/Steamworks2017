package redbacks.robot.actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.navx.NavX;
import static redbacks.robot.Robot.*;

public class AcReadSensors extends Action
{
	public AcReadSensors() {
		super(new ChFalse());
	}

	public void onRun() { //Runs every loop
		SmartDashboard.putNumber("Left Encoder", sensors.leftEncoderDis.get());
		SmartDashboard.putNumber("Right Encoder", sensors.rightEncoderDis.get());
		
		SmartDashboard.putNumber("Shooter speed", sensors.leftEncoderRate.get());
		
		SmartDashboard.putNumber("Velocity Forward", NavX.getSpeedForward());
		SmartDashboard.putNumber("Velocity Left", NavX.getSpeedRight());
		SmartDashboard.putNumber("Velocity Vertical", NavX.getSpeedUp());
		
		SmartDashboard.putNumber("Yaw", sensors.yaw.get());
	}	
}
