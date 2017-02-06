package org.usfirst.frc.team4946.robot;

public class RobotConstants {
	// For Constants that don't have to do with port numbers
	public static final int ENCODER_PPR = 128;
	public static final double WHEEL_DIA = 4;// 3.97; // rip not even 4"
	public static final double GEARBOX_REDUCTION = 24.0 / 50.0;
	public static final double ENCODER_DISTANCE_PER_PULSE = WHEEL_DIA * Math.PI / ENCODER_PPR * GEARBOX_REDUCTION;
	public static final double AIRSHIP_SIDELENGTH = ((Math.sin(30) * 101) / Math.sin(120)); // 58.31237719
																							// inches
	public static final double AUTO_MAX_SPEED = 0.6;

	public static final class Auto {
		// Blue
		public static final int LEFT_POSITION = 1;
		public static final int RIGHT_POSITION = 2;
		public static final int SIDE_GEAR_AND_SHOOT = 3;
		public static final int BREACH_NO_SHOOT = 4;
		public static final int MIDDLE_POSITION_BREACH_SHOOT = 5;
		public static final int MIDDLE_POSITION_BREACH_LEFT = 6;
		public static final int MIDDLE_POSITION_BREACH_RIGHT = 7;
		public static final int MIDDLE_POSITION_JUST_SHOOT = 8;
		public static final int MIDDLE_POSITION_DO_NOTHING = 9;
		public static final int IF_GEAR_FIRST = 10;
		public static final int IF_SHOOT_FIRST = 11; 
		
	}
}
