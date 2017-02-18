package redbacks.arachne.lib.trajectories;

import edu.wpi.first.wpilibj.PIDSourceType;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.motors.CtrlDrivetrain;
import redbacks.arachne.lib.pid.AcPIDControl;
import redbacks.arachne.lib.pid.Tolerances;
import redbacks.arachne.lib.pid.AcMultiPID.PIDAxis;
import redbacks.arachne.lib.sensors.NumericSensor;
import redbacks.robot.Robot;

public class AcTrajectoryWall2 extends Action
{
	Trajectory trajectory;
	boolean shouldFinish;
	
	CtrlDrivetrain drivetrain;
	double[] driveMults = {1, 1};
	
	NumericSensor gyro;
	double gyroComp;

	NumericSensor encoder;
	boolean invertEncoder;

	PIDAxis linearOut;
	
	double target;
	
	public AcTrajectoryWall2(Check check, boolean shouldFinish, Trajectory trajectory, CtrlDrivetrain drivetrain, double leftMult, double rightMult,
			NumericSensor gyro, double gyroComp,
			NumericSensor encoder, boolean invertEncoder) {
		super(check);
		this.shouldFinish = shouldFinish;
		this.trajectory = trajectory;
		this.drivetrain = drivetrain;
		this.driveMults[0] = leftMult;
		this.driveMults[1] = rightMult;
		this.gyro = gyro;
		this.gyroComp = gyroComp;
		this.encoder = encoder;
		this.invertEncoder = invertEncoder;
		this.target = trajectory.totalDistance * (invertEncoder ? -1 : 1);
		
		this.linearOut = new PIDAxis(1);
		this.linearOut.pidWrite(1);
	}
	
	public void onStart() {
		trajectory.reset();
		gyro.set(0);
		encoder.set(0);
		Robot.isIndivDriveControl = false;
	}
	
	public void onRun() {
		drivetrain.tankDrive((linearOut.output - (getGyro() - trajectory.getAngleFromDistance(Math.abs(encoder.get()))) * gyroComp * Math.abs(linearOut.output)) * driveMults[0], (linearOut.output + (getGyro() - trajectory.getAngleFromDistance(Math.abs(encoder.get()))) * gyroComp * Math.abs(linearOut.output)) * driveMults[1]);
	}
	
	public boolean isDone() {
		return shouldFinish && Math.abs(target) < Math.abs(encoder.get());
	}
	
	public double getGyro() {
		double angle = gyro.get() % 360;
		if(angle > 180) angle -= 360;
		else if(angle <= -180) angle += 360;
		return angle;
	}
}
