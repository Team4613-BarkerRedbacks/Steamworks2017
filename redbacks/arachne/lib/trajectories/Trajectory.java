package redbacks.arachne.lib.trajectories;

public class Trajectory
{
	public double[][] waypoints;
	private int progressIndex = 0;
	public double totalDistance;
	
	public Trajectory(double[]... waypoints) {
		this.waypoints = waypoints;
		totalDistance = waypoints[waypoints.length-1][0];
	}
	
	public void reset() {
		progressIndex = 0;
	}
	
	public double getAngleFromDistance(double distance) {
		while(progressIndex < waypoints.length - 1 && Math.abs(distance) > Math.abs(waypoints[progressIndex][0])) {
			progressIndex++;
		}
		return waypoints[progressIndex][1];
	}
	
	public boolean isPathComplete() {
		return progressIndex >= waypoints.length - 1;
	}
}
