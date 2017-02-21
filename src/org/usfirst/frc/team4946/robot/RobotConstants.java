package org.usfirst.frc.team4946.robot;

import edu.wpi.first.wpilibj.Preferences;

public class RobotConstants {
	// For Constants that don't have to do with port numbers
	public static final int ENCODER_PPR = 128;
	public static final double WHEEL_DIA = 4;// 3.97; // rip not even 4"
	public static final double GEARBOX_REDUCTION = 24.0 / 50.0;
	public static final double ENCODER_DISTANCE_PER_PULSE = WHEEL_DIA * Math.PI
			/ ENCODER_PPR * GEARBOX_REDUCTION;
	public static final double AUTO_MAX_SPEED = 0.6;

	public static final class Auto {

		public enum AutoScript {
			GEAR_FIRST,
			SHOOT_FIRST,
			HOPPER_LEFT,
			HOPPER_RIGHT,
			BREACH
		}

		public enum AutoOptions {
			LEFT_POS,
			RIGHT_POS,
			RIGHT_POS_THEN_SHOOT,
			MIDDLE_NO_BREACH,
			MIDDLE_BREACH_AND_SHOOT,
			MIDDLE_BREACH_LEFT,
			MIDDLE_BREACH_RIGHT,
			MIDDLE_JUST_SHOOT,
		}
	}

	public static double driveP;
	public static double driveI;
	public static double driveOutput;
	public static double turnP;
	public static double turnI;
	public static double turnOutput;
	public static double shootP;
	public static double shootI;
	public static double shootF;
	public static double shootFOff;

	public static void loadPrefs(Preferences prefs) {
		driveP = prefs.getDouble("driveP", 0.3);
		driveI = prefs.getDouble("driveI", 0.0);
		driveOutput = prefs.getDouble("driveOutput", 0.6);
		turnP = prefs.getDouble("turnP", 0.02);
		turnI = prefs.getDouble("turnI", 0.0);
		turnOutput = prefs.getDouble("turnOutput", 0.8);
		shootP = prefs.getDouble("shootP", 0.0015);
		shootI = prefs.getDouble("shootI", 0.0);
		shootF = prefs.getDouble("shootF", 0.00012899);
		shootFOff = prefs.getDouble("shootFOff", 0.052882);
	}

	public static void repopulatePrefs(Preferences prefs) {
		prefs.putDouble("driveP", driveP);
		prefs.putDouble("driveI", driveI);
		prefs.putDouble("driveOutput", driveOutput);
		prefs.putDouble("turnP", turnP);
		prefs.putDouble("turnI", turnI);
		prefs.putDouble("turnOutput", turnOutput);
		prefs.putDouble("shootP", shootP);
		prefs.putDouble("shootI", shootI);
		prefs.putDouble("shootF", shootF);
		prefs.putDouble("shootFOff", shootFOff);
	}

}
