package redbacks.arachne.lib.trajectories;

import static redbacks.robot.Robot.*;
import static redbacks.robot.RobotMap.*;

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

public class AcTrajectory extends Action
{
	Trajectory trajectory;
	
	CtrlDrivetrain drivetrain;
	double[] driveMults = {1, 1};
	
	NumericSensor gyro;
	double gyroComp;

	NumericSensor encoder;
	boolean invertEncoder;

	PIDAxis linearOut;
	AcPIDControl acLinear;
	public AcTrajectory(Check check, boolean shouldFinish, Trajectory trajectory, CtrlDrivetrain drivetrain, double leftMult, double rightMult, NumericSensor gyro, double gyroComp,
			NumericSensor encoder, boolean invertEncoder, double p, double i, double d, Tolerances tolerance, boolean isContinuous, double minIn, double maxIn) {
		//Could consider that a distance encoder will always be the same continuous and min/max state. Do we need those variables?
		super(check);
		this.trajectory = trajectory;
		this.drivetrain = drivetrain;
		this.driveMults[0] = leftMult;
		this.driveMults[1] = rightMult;
		this.gyro = gyro;
		this.gyroComp = gyroComp;
		this.encoder = encoder;
		this.invertEncoder = invertEncoder;
		
		this.linearOut = new PIDAxis(1);
		this.acLinear = new AcPIDControl(new ChFalse(), shouldFinish, p, i, d, 0, trajectory.totalDistance * (invertEncoder ? -1 : 1), tolerance, encoder, isContinuous, minIn, maxIn, PIDSourceType.kDisplacement, -0.8, 0.8, linearOut);
	}
	
	/**
	 * Constructor for our drivetrain autos, so you can only pass it a trajectory and don't need to write out a full giant ten billion bit constructor
	 * @param trajectory A trajecctory for it to follow.
	 */
	public AcTrajectory(Trajectory trajectory) {
		this(new ChFalse(), true, trajectory, driver.drivetrain, -1, -1,
		sensors.yaw, 0.1, sensors.centreEncoderDis, true, drivePIDMotorkP, drivePIDMotorkI, drivePIDMotorkD, new Tolerances.Absolute(150), false, 0, 0);
	}
	
	public void onStart() {
		trajectory.reset();
		gyro.set(0);
		encoder.set(0);
		Robot.isIndivDriveControl = false;
		acLinear.initialise(command);
	}
	
	public void onRun() {
		acLinear.execute();
		drivetrain.tankDrive((linearOut.output - (getGyro() - trajectory.getAngleFromDistance(Math.abs(encoder.get()))) * gyroComp * Math.abs(linearOut.output)) * driveMults[0], (linearOut.output + (getGyro() - trajectory.getAngleFromDistance(Math.abs(encoder.get()))) * gyroComp * Math.abs(linearOut.output)) * driveMults[1]);
	}
	
	public void onFinish() {
		acLinear.end();
	}
	
	public boolean isDone() {
		return acLinear.isDone();
	}
	
	public double getGyro() {
		double angle = gyro.get() % 360;
		if(angle > 180) angle -= 360;
		else if(angle <= -180) angle += 360;
		return angle;
	}
}
