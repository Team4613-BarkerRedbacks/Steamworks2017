package redbacks.robot;

import com.ctre.CANTalon;

public class RobotMap
{
	public static final CANTalon 
		talon2 = new CANTalon(2),
		talon3 = new CANTalon(3),
		talon4 = new CANTalon(4),
		talon5 = new CANTalon(5),
		talon6 = new CANTalon(6),
		talon7 = new CANTalon(7),
		talon8 = new CANTalon(8),
		talon9 = new CANTalon(9),
		talon10 = new CANTalon(10),
		talon11 = new CANTalon(11),
		talon12 = new CANTalon(12);
	
	public static final double
		kP = 0.0005D, kI = 0, kD = 0.00005D;
}
