package redbacks.arachne.lib.trajectories;

import redbacks.robot.RobotMap;

public class Trajectory
{
	public double[][] waypoints;
	private int progressIndex = 0;
	public double totalDistance;
	
	public Trajectory(double[]... waypoints) {
		this.waypoints = waypoints;
	}
	
	public void reset() {
		progressIndex = 0;
	}
	
	public double getAngleFromDistance(double distance) {
		for(; progressIndex < waypoints.length && distance * RobotMap.encoderTicksPerMetre < waypoints[progressIndex][0]; progressIndex++);
		return waypoints[progressIndex][1];
	}
	
	public boolean isPathComplete() {
		return progressIndex >= waypoints.length - 1;
	}
}
