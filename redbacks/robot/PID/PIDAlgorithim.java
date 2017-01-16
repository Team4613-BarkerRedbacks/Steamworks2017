package redbacks.robot.PID;

/**
 * A generic class to calculate how things should move in accordance with the PID Algorithim 
 * 
 * @author SchwarzT18
 */
public class PIDAlgorithim {
	
	double Kp, Ki, Kd, targetPoint;
	private double prevIerror = 0;
	private double prevKerror = 0;
	private double PIDout, Kerror;
	
	/**
	 * Sets up the various needed constants for PID. Pass 0 to not use that part (e.g. for PI pass 0.0 to Kd) 
	 * 
	 * @param Kp constant for P
	 * @param Ki constant for I
	 * @param Kd constant for D
	 * @param targetPoint the goal for what the robot should get to. Could be an encoder value or angle.
	 */
	public PIDAlgorithim(double Kp, double Ki, double Kd, double targetPoint) {
		this.Kp = Kp;
		this.Ki = Ki;
		this.Kd = Kd;
		this.targetPoint = targetPoint;
	}
	
	/**
	 * Updates the target point in the PID class
	 * @param targetPoint the value to update targetPoint to.
	 */
	public void updateTargetPoint(double targetPoint) {
		this.targetPoint = targetPoint;
	}
	
	/**
	 * 
	 * Calculates the output from the PID algorithm
	 * @param currentPoint a double representing the current point of the moving item. Must be in the same units as targetPoint.
	 * @return the value the motor/whatever should use
	 */
	public double calculate(double currentPoint) {
		Kerror = targetPoint - currentPoint;
		PIDout = (Kp*Kerror) + (Ki * (this.prevIerror + Kerror)) + (Kd * (Kerror-prevKerror));
		this.prevIerror = (this.prevIerror + Kerror);
		this.prevKerror = Kerror;
		return PIDout;
	}
	
	/**
	 * Calculates the output from the PID algorithm, while updating targetPoint to a new position.
	 * @param currentPoint a double representing the current point of the moving item. Must be in the same units as targetPoint.
	 * @param targetPoint the goal for what the robot should get to. Could be an encoder value or angle.
	 * @return the value the motor/whatever should use.
	 */
	public double calculate(double currentPoint, double targetPoint) {
		this.targetPoint = targetPoint;
		return calculate(currentPoint);
	}
	
	/**
	 * Get the current value of the constant Kp
	 * @return the current value of the consant Kp
	 */
	public double getKp() {return this.Kp;}
	
	/**
	 * Get the current value of the constant Ki
	 * @return the current value of the consant Ki
	 */
	public double getKi() {return this.Ki;}
	
	/**
	 * Get the current value of the constant Kd
	 * @return the current value of the consant Kd
	 */
	public double getKd() {return this.Kd;}
	
	/**
	 * Update the value of Kp to a new value.
	 * @param Kp The value to update Kp to.
	 */
	public void updateKp(double Kp) {this.Kp = Kp;}
	
	/**
	 * Update the value of Ki to a new value.
	 * @param Ki The value to update Ki to.
	 */
	public void updateKi(double Ki) {this.Ki = Ki;}
	
	/**
	 * Update the value of Kd to a new value.
	 * @param Kd The value to update Kd to.
	 */
	public void updateKd(double Kd) {this.Kd = Kd;}
	
}
