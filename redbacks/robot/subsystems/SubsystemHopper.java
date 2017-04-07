package redbacks.robot.subsystems;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.motors.CtrlMotor;
import redbacks.arachne.lib.motors.CtrlMotorList;
import redbacks.arachne.lib.solenoids.SolSingle;
import redbacks.robot.RobotMap;

import static redbacks.robot.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemHopper extends SubsystemBase
{
	CtrlMotor motHopperL = new CtrlMotor(idMotHopperL);
	CtrlMotor motHopperR = new CtrlMotor(idMotHopperR);
	
	public CtrlMotorList hopperMotor = new CtrlMotorList(
			motHopperL, motHopperR
	);

	public SolSingle hopperSol = new SolSingle(RobotMap.idSolHopper);
	public SolSingle eyebrowsSol = new SolSingle(RobotMap.idSolEyebrows);
	
	public SubsystemHopper() {
		super();

		motHopperL.setInverted(true);
	}
}
