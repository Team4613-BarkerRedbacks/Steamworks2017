package redbacks.arachne.lib.pid;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.Check;

/**
 * An action to output to a {@link PIDOutput}, based on the value of multiple {@link PIDController PIDControllers}.
 * This can be useful in combining a driven distance with a desired angle, for example.
 *
 * @author Sean Zammit
 */
public class AcMultiPID extends Action
{
	double min, max;
	boolean shouldFinish;
	MultiPIDCombiner combiner;
	
	PIDAxis[] axes;
	PIDParams[] params;
	PIDOutput output;
	
	AcPIDControl[] actions;
	
	/**
	 * Constructor for an action to output to a {@link PIDOutput}, based on the value of multiple {@link PIDController PIDControllers}.
	 * 
	 * @param check A check that will determine whether the action should stop.
	 * @param shouldFinish Whether the action should finish once all the PIDControllers are on target.
	 * @param output The single PIDOutput to be set after combining the PIDController values.
	 * @param multipliers The multipliers to be used for each of the PIDControllers created using the {@link PIDParams}. This array should be equally as long.
	 * @param params The parameters used to create {@link AcPIDControl} actions that will determine the PIDController output values to be combined.
	 */
	public AcMultiPID(Check check, boolean shouldFinish, PIDOutput output, double[] multipliers, PIDParams... params) {
		this(check, shouldFinish, output, multipliers, new MultiPIDCombiner(), params);
	}

	/**
	 * Constructor for an action to output to a {@link PIDOutput}, based on the value of multiple {@link PIDController PIDControllers}.
	 * 
	 * @param check A check that will determine whether the action should stop.
	 * @param shouldFinish Whether the action should finish once all the PIDControllers are on target.
	 * @param output The single PIDOutput to be set after combining the PIDController values.
	 * @param multipliers The multipliers to be used for each of the PIDControllers created using the {@link PIDParams}. This array should be equally as long.
	 * @param combiner The method of combining the outputs from the PIDControllers. By default, they are just summed.
	 * @param params The parameters used to create {@link AcPIDControl} actions that will determine the PIDController output values to be combined.
	 */
	public AcMultiPID(Check check, boolean shouldFinish, PIDOutput output, double[] multipliers, MultiPIDCombiner combiner, PIDParams... params) {
		super(check);
		this.output = output;
		if(output instanceof PIDMotor) ((PIDMotor) output).setAction(this);
		this.axes = new PIDAxis[multipliers.length];
		this.actions = new AcPIDControl[params.length];
		for(int i = 0; i < this.axes.length; i++)
			this.axes[i] = new PIDAxis(multipliers[i]);
		this.params = params;
		this.shouldFinish = shouldFinish;
		this.combiner = combiner;
	}

	public void onStart() {
		for(int idx = 0; idx < params.length; idx++) {
			PIDParams pars = params[idx];
			actions[idx] = new AcPIDControl(new ChFalse(), true, pars.p, pars.i, pars.d, pars.f, pars.target, pars.tolerance, pars.input, pars.isContinuous, pars.minIn, pars.maxIn, pars.type, pars.minOut, pars.maxOut, axes[idx]);
			actions[idx].initialise(command);
		}
	}

	public void onRun() {
		for(AcPIDControl action : actions) action.execute();
		output.pidWrite(combiner.combine(axes));
	}

	public void onFinish() {
		for(AcPIDControl action : actions) action.end();
		output.pidWrite(0);
	}

	public boolean isDone() {
		if(!shouldFinish) return false;
		boolean allTargeted = true;
		for(AcPIDControl action : actions) if(!action.isDone()) allTargeted = false;
		return allTargeted;
	}

	/**
	 * A {@link PIDOutput} that stores the output value, instead of doing anything with it.
	 * This is used by {@link AcMultiPID} to combine multiple {@link PIDController} outputs.
	 *
	 * @author Sean Zammit
	 */
	public static class PIDAxis implements PIDOutput
	{
		public double output = 0, multiplier;

		/**
		 * Constructor for a {@link PIDOutput} that stores the output value, instead of doing anything with it.
		 * 
		 * @param multiplier The multiplier to be used on the output.
		 */
		public PIDAxis(double multiplier) {
			this.multiplier = multiplier;
		}

		public void pidWrite(double output) {
			this.output = output * multiplier;
		}
	}

	/**
	 * An object that holds all PID parameters required to create a {@link PIDController}.
	 * This is used by {@link AcMultiPID} to allow multiple PIDControllers to be created.
	 * As it passes all its values to an {@link AcPIDControl} instances, see it instead.
	 *
	 * @author Sean Zammit
	 */
	public static class PIDParams
	{
		public Check check;

		public double p, i, d, f, target, minIn, maxIn, minOut, maxOut;
		public boolean isContinuous, shouldFinish;

		public Tolerances tolerance;
		public PIDSource input;
		public PIDSourceType type;

		/**
		 * Constructor for an object that holds the required parameters for a {@link PIDController}.
		 * See {@link AcPIDControl#AcPIDControl(double, double, double, double, Tolerances, PIDSource, PIDOutput...) AcPIDControl}.
		 */
		public PIDParams(double p, double i, double d, double target, Tolerances tolerance, PIDSource input) {
			this(new ChFalse(), true, p, i, d, 0, target, tolerance, input, false, 0, 0, PIDSourceType.kDisplacement, -1, 1);
		}

		/**
		 * Constructor for an object that holds the required parameters for a {@link PIDController}.
		 * See {@link AcPIDControl#AcPIDControl(double, double, double, double, Tolerances, PIDSource, boolean, double, double, PIDOutput...) AcPIDControl}.
		 */
		public PIDParams(double p, double i, double d, double target, Tolerances tolerance, PIDSource input, boolean isContinuous, double minIn, double maxIn) {
			this(new ChFalse(), true, p, i, d, 0, target, tolerance, input, isContinuous, minIn, maxIn, PIDSourceType.kDisplacement, -1, 1);
		}

		/**
		 * Constructor for an object that holds the required parameters for a {@link PIDController}.
		 * See {@link AcPIDControl#AcPIDControl(Check, boolean, double, double, double, double, Tolerances, PIDSource, boolean, double, double, PIDSourceType, double, double, PIDOutput...) AcPIDControl}.
		 */
		public PIDParams(Check check, boolean shouldFinish, double p, double i, double d, double target, Tolerances tolerance, PIDSource input, boolean isContinuous, double minIn, double maxIn, PIDSourceType type, double minOut, double maxOut) {
			this(check, shouldFinish, p, i, d, 0, target, tolerance, input, isContinuous, minIn, maxIn, type, minOut, maxOut);
		}

		/**
		 * Constructor for an object that holds the required parameters for a {@link PIDController}.
		 * See {@link AcPIDControl#AcPIDControl(Check, boolean, double, double, double, double, double, Tolerances, PIDSource, boolean, double, double, PIDSourceType, double, double, PIDOutput...) AcPIDControl}.
		 */
		public PIDParams(Check check, boolean shouldFinish, double p, double i, double d, double f, double target, Tolerances tolerance, PIDSource input, boolean isContinuous, double minIn, double maxIn, PIDSourceType type, double minOut, double maxOut) {
			this.check = check;
			this.shouldFinish = shouldFinish;
			this.p = p;
			this.i = i;
			this.d = d;
			this.f = f;
			this.target = target;
			this.tolerance = tolerance;
			this.input = input;
			this.isContinuous = isContinuous;
			this.minIn = minIn;
			this.maxIn = maxIn;
			this.type = type;
			this.minOut = minOut;
			this.maxOut = maxOut;
		}
	}
	
	/**
	 * TODO Complete documentation
	 * 
	 * A generic class to combine multiple PID outputs into one output for motor control, e.g. to combine directional and angling PID to create a rotating PID drive base that will rotate to both angle and difference.
	 * This class will sum together all inputs for generic simple functionality. For more advanced control extend and override the combine() function with your own combining code. Combining code should only take
	 * 
	 * @author SchwarzT18
	 */
	public static class MultiPIDCombiner
	{
		private double sum;

		public double combine(PIDAxis[] axes) {
			sum = 0;
			for(PIDAxis axisOutput : axes)
				sum += axisOutput.output;
			return sum;
		}
	}
}
