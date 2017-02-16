package org.usfirst.frc.team4946.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int RELAY_GEAR_LED = 0;
	public static final int RELAY_SHOOTER_LED = 1;

	public static final int CAN_DRIVETRAIN_FLMOTOR = 0;
	public static final int CAN_DRIVETRAIN_BLMOTOR = 1; 
	public static final int CAN_DRIVETRAIN_FRMOTOR = 2;
	public static final int CAN_DRIVETRAIN_BRMOTOR = 3;
	public static final int CAN_TALON_SHOOTER = 4;

    public static final int PWM_INDEX_MOTOR = 5; // Not Needed
	public static final int PWM_AGITATOR= 6;
	public static final int PWM_WINCH_A = 7;
	public static final int PWM_WINCH_B = 8;
	public static final int PWM_INTAKE_MOTOR = 9;

	public static final int DIO_DRIVETRAIN_LEFTENCA = 0;
	public static final int DIO_DRIVETRAIN_LEFTENCB = 1;
	public static final int DIO_DRIVETRAIN_RIGHTENCA = 2;
	public static final int DIO_DRIVETRAIN_RIGHTENCB = 3;
	public static final int DIO_SHOOTER_SENSOR = 9;
	public static final int DIO_LIMIT_SWITCH_WINCH = 8; // Not Needed

	public static final int PCM_DOOR_OPENER_1 = 2; // Not Needed
	public static final int PCM_DOOR_OPENER_2 = 3;  // Not Needed
	public static final int PCM_GEAR_PUSHER = 1;
    public static final int PCM_SHOOTER_HOOD = 0; 
	public static final int PCM_SOLENOID_BLOCKER = 6; // Not Needed

}
