package redbacks.robot;

import com.ctre.CANTalon;

public class RobotMap
{
	//FIXME Determine correct ticks per metre.
	public static final int
		encoderTicksPerMetre = 2000;
	
	public static final CANTalon
		talon2	= new CANTalon(2),
		talon3	= new CANTalon(3),
		talon4	= new CANTalon(4),
		talon5	= new CANTalon(5),
		talon6	= new CANTalon(6),
		talon7	= new CANTalon(7),
		talon8	= new CANTalon(8),
		talon9	= new CANTalon(9),
		talon10	= new CANTalon(10),
		talon11	= new CANTalon(11),
		talon12	= new CANTalon(12),
		talon13	= new CANTalon(13),
		talon14	= new CANTalon(14),
		talon15	= new CANTalon(15),
		talon16	= new CANTalon(16);
	
	public static final CANTalon
		idMotDriveRF	= talon2,
		idMotDriveLF	= talon3,
		idMotDriveRB	= talon4,
		idMotDriveLB	= talon5,
		idMotDriveRT	= talon6,
		idMotDriveLT	= talon7,
		idMotHopperR	= talon8,
		idMotHopperL	= talon9,
		idMotSpit		= talon10,
		idMotShootL		= talon11,
		idMotShootR		= talon12,
		idMotIntakeF	= talon13,
		idMotIntakeB	= talon14,
		idMotClimbL		= talon15,
		idMotClimbR		= talon16;
	
	public static final int
		idSolSpit	= 4,
		idSolIntake	= 5;
	
	public static final double
		drivePIDMotorkP = 0.0005D, drivePIDMotorkI = 0.000005D, drivePIDMotorkD = 0.00005D,
		drivePIDGyrokP = 0.025D, drivePIDGyrokI = 0.0001, drivePIDGyrokD = 0.001;
	
	public static double multiPIDDriveCombinerLeftOverflow = 0.0D, mutiPIDDriveCombinerRightOverflow = 0.0D;
}
