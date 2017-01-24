package redbacks.arachne.lib.pid;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.Check;

/**
 * An object that holds all PID parameters required to create a {@link PIDController}.
 * This is used by {@link AcMultiPID} to allow multiple PIDControllers to be created.
 * As it passes all its values to an {@link AcPIDControl} instances, see it instead.
 *
 * @author Sean Zammit
 */
public class PIDParams
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
