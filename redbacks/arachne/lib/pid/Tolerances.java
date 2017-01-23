package redbacks.arachne.lib.pid;

import edu.wpi.first.wpilibj.PIDController;

/**
 * Takes the place of {@link PIDController.Tolerance}, as it isn't visible.
 * {@link AcPIDControl} and {@link AcMultiPID.PIDParams} use it to determine which type of PIDController.Tolerance they should set.
 *
 * @author Sean Zammit
 */
public class Tolerances
{
	/** The value of the tolerance. This is either a percentage or an absolute value. */
	public double value;

	protected Tolerances(double toleranceValue) {
		this.value = toleranceValue;
	}

	/**
	 * Takes the place of {@link PIDController.PercentageTolerance}.
	 *
	 * @author Sean Zammit
	 */
	public static class Percentage extends Tolerances
	{
		/**
		 * Constructor for a replacement for {@link PIDController.PercentageTolerance}.
		 * 
		 * @param toleranceValue The value of the tolerance. This should be between 0 and 100, representing a percentage.
		 */
		public Percentage(double toleranceValue) {
			super(toleranceValue);
		}
	}

	/**
	 * Takes the place of {@link PIDController.AbsoluteTolerance}.
	 *
	 * @author Sean Zammit
	 */
	public static class Absolute extends Tolerances
	{
		/**
		 * Constructor for a replacement for {@link PIDController.AbsoluteTolerance}.
		 * 
		 * @param toleranceValue The value of the tolerance.
		 */
		public Absolute(double toleranceValue) {
			super(toleranceValue);
		}
	}
}
