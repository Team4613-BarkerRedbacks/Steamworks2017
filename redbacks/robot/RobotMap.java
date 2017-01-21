package redbacks.robot;

import com.ctre.CANTalon;

import redbacks.arachne.lib.input.ButtonGettableWrapper;

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
	
	public static double
		drivePIDMotorkP = 0.0005D, drivePIDMotorkI = 0.000005D, drivePIDMotorkD = 0.00005D,
		drivePIDGyrokP = 0.005D, drivePIDGyrokI = 0.001, drivePIDGyrokD = 0; //Removed final temporarily for testing
	
	public static double drivePIDtestDistance = 0.0D, drivePIDtestAngle = 0.0D;
	public static double multiPIDDriveCombinerLeftOverflow = 0.0D, mutiPIDDriveCombinerRightOverflow = 0.0D;
	
	public static ButtonGettableWrapper shootButton = OI.d_B, feedButton = OI.d_A;
}
