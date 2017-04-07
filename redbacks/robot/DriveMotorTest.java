package redbacks.robot;

import edu.wpi.first.wpilibj.SpeedController;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.motors.CtrlDrive;

public class DriveMotorTest extends CtrlDrive
{
	public DriveMotorTest(SpeedController motor) {
		super(motor);
	}
	
	public void set(double speed) {
		super.set(speed);
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		System.out.println("DMT v1, stack: " + stack[5].toString() + " " + speed);
	}
	
	public void set(double speed, CommandBase command) {
		super.set(speed, command);
		System.out.println("DMT v2 " + speed);
	}
}
