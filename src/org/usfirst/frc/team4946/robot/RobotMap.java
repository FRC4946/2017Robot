package org.usfirst.frc.team4946.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int PWM_INTAKE_MOTOR = 17;
	// For example to map the left and right4946444 motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static final int CAN_TALON_SHOOTER = 1;
	public static final int CAN_TALON_WINCH = 6;
	public static final int CAN_TALON_WINCH_1=5;

	public static final int DIO_SHOOTER_SENSOR = 7;
	public static final int DIO_LIMIT_SWITCH_WINCH = 8;

}
