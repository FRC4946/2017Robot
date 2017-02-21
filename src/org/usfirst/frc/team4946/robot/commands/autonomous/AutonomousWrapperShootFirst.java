package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.RobotConstants.Auto.AutoOptions;
import org.usfirst.frc.team4946.robot.commands.Agitate;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.commands.driveTrain.ResetEncAndGyro;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnAbsolutePID;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;
import org.usfirst.frc.team4946.robot.commands.shooter.SetShooterSpeed;
import org.usfirst.frc.team4946.robot.commands.shooter.SpinShooterPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * O
 */
public class AutonomousWrapperShootFirst extends CommandGroup {
	final double FEEDER_DIST_A = 81.18; // Distance traveled when going from the
										// side with feeder
	final double FEEDER_DIST_B = 32.036;

	final double BOILER_DIST_A = 67.662; // Distance traveled when going from
											// the side with boiler
	final double BOILER_DIST_B = 58.936;

	final double MIDDLE_DIST = 70.6; // PLS ME

	final double HOPPER_DIST_A = 195.0;
	final double HOPPER_DIST_B = 16.0; // CHANGE LATER

	public AutonomousWrapperShootFirst(AutoOptions mode, boolean isRed) {

		// Shoot from the left
		addSequential(new ResetEncAndGyro());
		addSequential(new SetShooterSpeed(3600));
		addParallel(new SpinShooterPID());
		addSequential(new Wait(2));
		addSequential(new Agitate(), 2);
		addSequential(new TurnPID(-110), 2);
		addSequential(new AutoDriveDistancePID(70));
		addSequential(new TurnPID(-60));
		addSequential(new AutoDriveDistancePID(30));
		addSequential(new PlaceGearAndBackup());

		//
		// if (isRed) {
		//
		// switch (mode) {
		// case MIDDLE_NO_BREACH:
		// case MIDDLE_JUST_SHOOT:
		// case MIDDLE_BREACH_RIGHT:
		// case MIDDLE_BREACH_LEFT:
		// case MIDDLE_BREACH_AND_SHOOT:
		// // SHOOT
		// addSequential(new TurnPID(90));
		// addSequential(new AutoDriveDistancePID(MIDDLE_DIST));
		// addSequential(new PlaceGearAndBackup());
		// if (mode == AutoOptions.MIDDLE_BREACH_LEFT
		// || mode == AutoOptions.MIDDLE_BREACH_RIGHT)
		// addSequential(new MiddleBreach(
		// mode == AutoOptions.MIDDLE_BREACH_LEFT));
		//
		// break;
		// case LEFT_POS:
		// // The robot starts at the left position, shoots, breaches on
		// // the right and then drops the gear
		//
		// // shoot
		// addSequential(new TurnPID(-90));
		// addSequential(new AutoDriveDistancePID(BOILER_DIST_A - 20));
		// addSequential(new TurnPID(-60));
		// addSequential(new AutoDriveDistancePID(BOILER_DIST_B));
		// addSequential(new PlaceGearAndBackup());
		//
		// break;
		// case RIGHT_POS:
		// // The robot starts at the right position, shoots, breaches on
		// // the right and then drops the gear
		//
		// // facegoal
		// // shoot
		// addSequential(new TurnPID(-90));
		// addSequential(new AutoDriveDistancePID(FEEDER_DIST_A));
		// addSequential(new TurnPID(-60));
		// addSequential(new AutoDriveDistancePID(FEEDER_DIST_B));
		// addSequential(new PlaceGearAndBackup());
		// }
		// } else {
		// switch (mode) {
		// case MIDDLE_NO_BREACH:
		// case MIDDLE_JUST_SHOOT:
		// case MIDDLE_BREACH_RIGHT:
		// case MIDDLE_BREACH_LEFT:
		// case MIDDLE_BREACH_AND_SHOOT:
		//
		// // SHOOT
		// addSequential(new TurnPID(90));
		// addSequential(new AutoDriveDistancePID(MIDDLE_DIST));
		// addSequential(new PlaceGearAndBackup());
		// if (mode == AutoOptions.MIDDLE_BREACH_LEFT
		// || mode == AutoOptions.MIDDLE_BREACH_RIGHT)
		// addSequential(new MiddleBreach(
		// mode == AutoOptions.MIDDLE_BREACH_LEFT));
		//
		// break;
		// case LEFT_POS:
		// // The robot starts at the left position, shoots, breaches on
		// // the left and then drops the gear
		// // shoot
		// addSequential(new TurnPID(90));
		// addSequential(new AutoDriveDistancePID(FEEDER_DIST_A));
		// addSequential(new TurnPID(60));
		// addSequential(new AutoDriveDistancePID(FEEDER_DIST_B));
		// addSequential(new PlaceGearAndBackup());
		//
		// break;
		// case RIGHT_POS:
		// // The robot starts at the right position, shoots, breaches on
		// // the left and then drops the gear
		// // shoot
		// addSequential(new TurnPID(90));
		// addSequential(new AutoDriveDistancePID(BOILER_DIST_A));
		// addSequential(new TurnPID(60));
		// addSequential(new AutoDriveDistancePID(BOILER_DIST_B));
		// addSequential(new PlaceGearAndBackup());
		// default:
		// break;
		// }
		// }
	}
}
