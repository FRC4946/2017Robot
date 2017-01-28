package org.usfirst.frc.team4946.robot;

public class RobotConstants {
	// For Constants that don't have to do with port numbers
	public static final int ENCODER_PPR = 128;
	public static final double WHEEL_DIA = 4;//3.97; // rip not even 4"
	public static final double GEARBOX_REDUCTION = 24.0 / 50.0;
	public static final double ENCODER_DISTANCE_PER_PULSE = WHEEL_DIA * Math.PI
			/ ENCODER_PPR * GEARBOX_REDUCTION;
	public static final double AIRSHIP_SIDELENGTH = ((Math.sin(30) * 101) / Math
			.sin(120)); // 58.31237719 inches
	public static final double AUTO_MAX_SPEED = 0.6;

	public static final class Auto {
		//Blue
		public static final int BLUE_LEFT_POSITION_SHOOT = 1;
		public static final int BLUE_LEFT_POSITION_NO_SHOOT = 2;
		public static final int BLUE_RIGHT_POSITION_NO_SHOOT = 3;
		public static final int BLUE_MIDDLE_POSITION_BREACH_LEFT_SHOOT = 4;
		public static final int BLUE_MIDDLE_POSITION_BREACH_RIGHT_NO_SHOOT = 5;
		public static final int BLUE_MIDDLE_POSITION_NO_BREACH_SHOOT = 6;
		//Red
		public static final int RED_RIGHT_POSITION_SHOOT = 7;
		public static final int RED_RIGHT_POSITION_NO_SHOOT = 8;
		public static final int RED_LEFT_POSITION_NO_SHOOT = 9;
		public static final int RED_MIDDLE_POSITION_BREACH_RIGHT_SHOOT = 10;
		public static final int RED_MIDDLE_POSITION_BREACH_LEFT_NO_SHOOT = 11;
		public static final int RED_MIDDLE_POSITION_NO_BREACH_SHOOT = 12;
	}
}
