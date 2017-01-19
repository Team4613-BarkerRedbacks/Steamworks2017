package redbacks.robot.pid;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.Check;

public class AcMultiPID extends Action
{
	double min, max;
	boolean shouldFinish;
	
	PIDAxis[] axes;
	PIDParams[] params;
	PIDOutput output;
	
	AcPIDControl[] actions;

	public AcMultiPID(Check check, boolean shouldFinish, PIDOutput output, double[] multipliers, PIDParams... params) {
		super(check);
		this.output = output;
		if(output instanceof PIDMotor) ((PIDMotor) output).setAction(this);
		this.axes = new PIDAxis[multipliers.length];
		this.actions = new AcPIDControl[params.length];
		for(int i = 0; i < this.axes.length; i++)
			this.axes[i] = new PIDAxis(multipliers[i]);
		this.params = params;
		this.shouldFinish = shouldFinish;
	}

	public void onStart() {
		for(int idx = 0; idx < params.length; idx++) {
			PIDParams pars = params[idx];
			actions[idx] = new AcPIDControl(new ChFalse(), true, pars.p, pars.i, pars.d, pars.f, pars.target, pars.tolerance, pars.input, pars.isContinuous, pars.minIn, pars.maxIn, pars.type, pars.minOut, pars.maxOut, axes[idx]);
			actions[idx].initialise(command);
		}
	}

	public void onRun() {
		double sum = 0;
		for(AcPIDControl action : actions) action.execute();
		for(PIDAxis axis : axes) sum += axis.output;
		output.pidWrite(sum);
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

	public static class PIDAxis implements PIDOutput
	{
		public double output = 0, multiplier;

		public PIDAxis(double multiplier) {
			this.multiplier = multiplier;
		}

		public void pidWrite(double output) {
			this.output = output * multiplier;
		}
	}

	public static class PIDParams
	{
		public Check check;

		public double p, i, d, f, target, minIn, maxIn, minOut, maxOut;
		public boolean isContinuous, shouldFinish;

		public Tolerances tolerance;
		public PIDSource input;
		public PIDSourceType type;

		public PIDParams(double p, double i, double d, double target, Tolerances tolerance, PIDSource input) {
			this(new ChFalse(), true, p, i, d, 0, target, tolerance, input, false, 0, 0, PIDSourceType.kDisplacement, -1, 1);
		}

		public PIDParams(double p, double i, double d, double target, Tolerances tolerance, PIDSource input, boolean isContinuous, double minIn, double maxIn) {
			this(new ChFalse(), true, p, i, d, 0, target, tolerance, input, isContinuous, minIn, maxIn, PIDSourceType.kDisplacement, -1, 1);
		}

		public PIDParams(Check check, boolean shouldFinish, double p, double i, double d, double target, Tolerances tolerance, PIDSource input, boolean isContinuous, double minIn, double maxIn, PIDSourceType type, double minOut, double maxOut) {
			this(check, shouldFinish, p, i, d, 0, target, tolerance, input, isContinuous, minIn, maxIn, type, minOut, maxOut);
		}

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
}
