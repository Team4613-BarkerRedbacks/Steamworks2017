package redbacks.arachne.lib.pid;

public class Tolerances
{
	public double value;
	
	protected Tolerances(double toleranceValue) {
		this.value = toleranceValue;
	}
	
	public static class Percentage extends Tolerances {
		public Percentage(double toleranceValue) {
			super(toleranceValue);
		}
	}
	
	public static class Absolute extends Tolerances {
		public Absolute(double toleranceValue) {
			super(toleranceValue);
		}
	}
}
