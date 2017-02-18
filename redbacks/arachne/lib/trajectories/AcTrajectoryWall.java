package redbacks.arachne.lib.trajectories;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.motors.CtrlDrivetrain;
import redbacks.arachne.lib.sensors.NumericSensor;
import redbacks.robot.Robot;

public class AcTrajectoryWall extends Action
{
	Trajectory trajectory;
	boolean shouldFinish;
	
	CtrlDrivetrain drivetrain;
	double[] driveMults = {1, 1};
	
	NumericSensor gyro;
	double gyroComp;

	NumericSensor encoder;
	boolean invertEncoder;
	
	double target;

	public AcTrajectoryWall(Check check, boolean shouldFinish, Trajectory trajectory, CtrlDrivetrain drivetrain, double leftMult, double rightMult,
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
	}
	
	public void onStart() {
		trajectory.reset();
		gyro.set(0);
		encoder.set(0);
		Robot.isIndivDriveControl = false;
		System.out.println(target);
	}
	
	public void onRun() {
		drivetrain.tankDrive((getSpeed() - (getGyro() - trajectory.getAngleFromDistance(Math.abs(encoder.get()))) * gyroComp) * driveMults[0],
							(getSpeed() + (getGyro() - trajectory.getAngleFromDistance(Math.abs(encoder.get()))) * gyroComp) * driveMults[1]);
	}
	
	public boolean isDone() {
		return shouldFinish && Math.abs(target) < Math.abs(encoder.get());
	}
	
	public double getSpeed() {
		return (encoder.get() < target ? 1 : -1) * (invertEncoder ? -1 : 1);
	}
	
	public double getGyro() {
		double angle = gyro.get() % 360;
		if(angle > 180) angle -= 360;
		else if(angle <= -180) angle += 360;
		return angle;
	}
}
