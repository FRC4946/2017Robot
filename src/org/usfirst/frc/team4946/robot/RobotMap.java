package org.usfirst.frc.team4946.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int PWM_VICTORSP_AGITATOR= 7;
	public static final int PCM_SOLENOID_BLOCKER = 6;
	public static final int RELAY_GEAR_LED = 5;
	public static final int RELAY_SHOOTER_LED = 4;
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int master


	public static final int PWM_DRIVETRAIN_FLMOTOR = 0;
	public static final int PWM_DRIVETRAIN_BLMOTOR = 1; 
	public static final int PWM_DRIVETRAIN_FRMOTOR = 2;
	public static final int PWM_DRIVETRAIN_BRMOTOR = 3;
	public static final int PWM_INTAKE_MOTOR = 4;
  public static final int PWM_INDEX_MOTOR = 5;
  
	public static final int DIO_SHOOTER_SENSOR = 0;
	public static final int DIO_LIMIT_SWITCH_WINCH = 1;
	public static final int DIO_DRIVETRAIN_LEFTENCA = 2;
	public static final int DIO_DRIVETRAIN_LEFTENCB = 3;
	public static final int DIO_DRIVETRAIN_RIGHTENCA = 4;
	public static final int DIO_DRIVETRAIN_RIGHTENCB = 5;

	public static final int CAN_TALON_SHOOTER = 0;
	public static final int CAN_TALON_WINCH = 1;
	public static final int CAN_TALON_WINCH_1 = 2;

	public static final int PCM_DOOR_OPENER_1 = 1;
	public static final int PCM_DOOR_OPENER_2 = 2; 
	public static final int PCM_GEAR_PUSHER = 3;
  public static final int PCM_SHOOTER_HOOD = 4; 
}
