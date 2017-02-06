package org.usfirst.frc.team4946.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int PWM_INTAKE_MOTOR = 4;
	public static final int PWM_DRIVETRAIN_FLMOTOR = 0;
	public static final int PWM_DRIVETRAIN_BLMOTOR = 1; 
	public static final int PWM_DRIVETRAIN_FRMOTOR = 2;
	public static final int PWM_DRIVETRAIN_BRMOTOR = 3;
	
	public static final int DIO_DRIVETRAIN_LEFTENCA = 6;
	public static final int DIO_DRIVETRAIN_LEFTENCB = 7;
	public static final int DIO_DRIVETRAIN_RIGHTENCA = 8;
	public static final int DIO_DRIVETRAIN_RIGHTENCB = 9;


	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static final int PWM_DOOR_OPENER_1 = 0;
	public static final int PWM_DOOR_OPENER_2 = 1; 
	public static final int PWM_GEAR_PUSHER = 2;
}
