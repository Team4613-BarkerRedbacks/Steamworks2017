package redbacks.robot.actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.robot.Robot;

public class AcReadSensors extends Action
{
	public AcReadSensors() {
		super(new ChFalse());
	}

	public void onRun() {
		SmartDashboard.putNumber("Left Encoder", Robot.sensors.driveLEncoder.get());
		SmartDashboard.putNumber("Right Encoder", Robot.sensors.driveREncoder.get());
		
		SmartDashboard.putNumber("Yaw", Robot.sensors.yaw.get());
	}
}
