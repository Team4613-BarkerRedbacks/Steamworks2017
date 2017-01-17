package redbacks.arachne.lib.sensors;

import com.ctre.CANTalon;

public class SenMotorCurrent extends NumericSensor
{
	CANTalon talon;
	
	public SenMotorCurrent(CANTalon talon) {
		this.talon = talon;
	}
	
	public double getSenVal() {
		return talon.getOutputCurrent();
	}
}
