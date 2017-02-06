package redbacks.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import redbacks.arachne.core.OIBase;
import redbacks.arachne.lib.checks.analog.ChGettableNumber;
import redbacks.arachne.lib.checks.digital.*;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.input.*;
import redbacks.arachne.lib.logic.*;

import static redbacks.arachne.lib.input.ButtonGettableWrapper.*;
import static redbacks.robot.CommandList.*;

public class OI extends OIBase
{
	public static boolean isSingleControl = true;
	
	public void mapOperations() {
		whenPressed(d_Start, reset.c());
		//whenHeld(d_RT, intakeIn.c());
		whenHeld(d_Back, climbSlow.c());
		whenHeld(d_RStick, climbRamp.c());
		
		whenPressedReleased(d_RT, shoot.c(), rel_shoot.c());
		//whenPressedReleased(d_RT, shooterFeedHopper.c(), rel_shooterFeedHopper.c());
		
		whenPressed(d_RB, spitterUp.c());
		whenReleased(d_RB, spitterDown.c());
		
		whenHeld(d_B, hopperOn.c());
		whenHeld(d_LT, spit.c());
		whenHeld(d_LB, spitIn.c());
		
		if(isSingleControl) {
			whenHeld(new BtnMulti(LogicOperators.AND, 
							new BtnCheck(new ChGettableNumber(0.1, Robot.driver.leftMotor, true, false)), 
							new BtnCheck(new ChGettableNumber(-0.1, Robot.driver.rightMotor, false, false)),
							new BtnCheck(new ChGettableBoolean(Robot.spitter.spitterSol, true)),
							new BtnCheck(new ChGettableBooleanList(LogicOperators.NOR, 
									d_LB, 
									Robot.sensors.gearLight)
							)
					),
					spit.c()
			);
		}

		whenHeld(d_A, pidExample.c());
		whenHeld(d_X, multiAxisExample.c());
//		whenHeld(d_Y, trajTest.c());
//		whenHeld(d_Y, trajAutoTom.c());
		whenHeld(d_Y, trajStraightTest.c());
		
		//JARVIS
		whenPressed(new BtnCheck(new ChGettableBooleanList(LogicOperators.OR, Robot.sensors.gearLight, Robot.sensors.shooterLight)), spitterUp.c());
	}
	
	private static final Joystick stick_d = new Joystick(0);
	
	public static final JoystickAxis
		axis_d_LX = new JoystickAxis(stick_d, 0),
		axis_d_LY = new JoystickAxis(stick_d, 1),
		axis_d_LT = new JoystickAxis(stick_d, 2),
		axis_d_RT = new JoystickAxis(stick_d, 3),
		axis_d_RX = new JoystickAxis(stick_d, 4),
		axis_d_RY = new JoystickAxis(stick_d, 5);
	
	public static final ButtonGettableWrapper
		d_A = wrap(new JoystickButton(stick_d, 1)),
		d_B = wrap(new JoystickButton(stick_d, 2)),
		d_X = wrap(new JoystickButton(stick_d, 3)),
		d_Y = wrap(new JoystickButton(stick_d, 4)),
		d_LB = wrap(new JoystickButton(stick_d, 5)),
		d_RB = wrap(new JoystickButton(stick_d, 6)),
		d_Back = wrap(new JoystickButton(stick_d, 7)),
		d_Start = wrap(new JoystickButton(stick_d, 8)),
		d_LStick = wrap(new JoystickButton(stick_d, 9)),
		d_RStick = wrap(new JoystickButton(stick_d, 10)),

		d_POV_U = wrap(new BtnPOV(stick_d, 0)),
		d_POV_R = wrap(new BtnPOV(stick_d, 90)),
		d_POV_D = wrap(new BtnPOV(stick_d, 180)),
		d_POV_L = wrap(new BtnPOV(stick_d, 270)),

		d_LT = wrap(new BtnAxis(axis_d_LT, false, 0.5D)),
		d_RT = wrap(new BtnAxis(axis_d_RT, false, 0.5D));
	
	private static final Joystick stick_o = new Joystick(0);
	
	public static final JoystickAxis
		axis_o_LX = new JoystickAxis(stick_o, 0),
		axis_o_LY = new JoystickAxis(stick_o, 1),
		axis_o_LT = new JoystickAxis(stick_o, 2),
		axis_o_RT = new JoystickAxis(stick_o, 3),
		axis_o_RX = new JoystickAxis(stick_o, 4),
		axis_o_RY = new JoystickAxis(stick_o, 5);
	
	public static final ButtonGettableWrapper
		o_A = wrap(new JoystickButton(stick_o, 1)),
		o_B = wrap(new JoystickButton(stick_o, 2)),
		o_X = wrap(new JoystickButton(stick_o, 3)),
		o_Y = wrap(new JoystickButton(stick_o, 4)),
		o_LB = wrap(new JoystickButton(stick_o, 5)),
		o_RB = wrap(new JoystickButton(stick_o, 6)),
		o_Back = wrap(new JoystickButton(stick_o, 7)),
		o_Start = wrap(new JoystickButton(stick_o, 8)),
		o_LStick = wrap(new JoystickButton(stick_o, 9)),
		o_RStick = wrap(new JoystickButton(stick_o, 10)),

		o_POV_U = wrap(new BtnPOV(stick_o, 0)),
		o_POV_R = wrap(new BtnPOV(stick_o, 90)),
		o_POV_D = wrap(new BtnPOV(stick_o, 180)),
		o_POV_L = wrap(new BtnPOV(stick_o, 270)),

		o_LT = wrap(new BtnAxis(axis_o_LT, false, 0.5D)),
		o_RT = wrap(new BtnAxis(axis_o_RT, false, 0.5D));
	
	public void whenPressedReleased(Button button, CommandBase onPressed, CommandBase onReleased) {
		button.whenPressed(onPressed);
		button.whenReleased(onReleased);
	}
}
