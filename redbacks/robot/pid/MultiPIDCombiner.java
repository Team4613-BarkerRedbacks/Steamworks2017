package redbacks.robot.pid;

import redbacks.robot.pid.AcMultiPID.PIDAxis;

/**
 * A generic class to combine multiple PID outputs into one output for motor control, e.g. to combine directional and angling PID to create a rotating PID drive base that will rotate to both angle and difference.
 * This class will sum together all inputs for generic simple functionality. For more advanced control extend and override the combine() function with your own combining code. Comining code should only take
 * 
 * @author SchwarzT18
 */
public class MultiPIDCombiner
{
	private double sum;

	public double combine(PIDAxis[] axes) {
		sum = 0;
		for(PIDAxis axisOutput : axes)
			sum += axisOutput.output;
		return sum;
	}
}
