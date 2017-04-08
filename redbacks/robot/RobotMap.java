package redbacks.robot;

import com.ctre.CANTalon;

public class RobotMap
{
	public static final int
		encoderTicksPerMetre = 7200;//(int) ((1/(Math.PI*0.048))*1024);
	
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
		idSolHopper	= 2, //FIXME SHOULD BE 2
		idSolIntake	= 6,
		idSolEyebrows = 0;
	
	public static final double
	//P = 0.00008D
		drivePIDMotorkP = 0.00007D, drivePIDMotorkI = 0.0000001D, drivePIDMotorkD = 0.00001,
		drivePIDGyrokP = 0.03D, drivePIDGyrokI = 0.0001, drivePIDGyrokD = 0.002;
	
	public static double multiPIDDriveCombinerLeftOverflow = 0.0D, mutiPIDDriveCombinerRightOverflow = 0.0D;
}
