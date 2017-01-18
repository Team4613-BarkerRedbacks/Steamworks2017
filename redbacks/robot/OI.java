package redbacks.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import redbacks.arachne.core.OIBase;
import redbacks.arachne.lib.input.BtnAxis;
import redbacks.arachne.lib.input.BtnPOV;
import redbacks.arachne.lib.input.ButtonGettableWrapper;
import redbacks.arachne.lib.input.JoystickAxis;
import static redbacks.arachne.lib.input.ButtonGettableWrapper.*;
import static redbacks.robot.CommandList.*;

public class OI extends OIBase
{
	public void mapOperations() {
		whenPressed(d_B, shoot.c());
		whenHeld(d_LB, spit.c());
		whenHeld(d_RB, spitIn.c());
		whenPressed(d_Start, reset.c());
		
		whenPressed(d_Back, pidtest2.c());
	}
	
	//Set up joysticks and buttons here.
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
}
