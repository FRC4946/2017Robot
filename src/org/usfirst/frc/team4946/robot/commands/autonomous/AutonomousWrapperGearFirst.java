package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.RobotConstants.Auto.AutoOptions;
import org.usfirst.frc.team4946.robot.commands.Agitate;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistance;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.commands.driveTrain.ResetEncAndGyro;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;
import org.usfirst.frc.team4946.robot.commands.flipper.ExtendFlippers;
import org.usfirst.frc.team4946.robot.commands.shooter.IncrementRPM;
import org.usfirst.frc.team4946.robot.commands.shooter.SpinShooterPID;
import org.usfirst.frc.team4946.robot.commands.vision.TurnToBoiler;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousWrapperGearFirst extends CommandGroup {

	final double DIST_A = 91 + 6 - 4;
	final double DIST_B = 13 + 3;// 34 - 16;
	final double MIDDLE_DIST = 75;
	final double TURN_TO_PEG = 60;
	final double TURN_TO_BOILER_SIDE = 155;
	final double TURN_TO_BOILER_MIDDLE = 100;

	public boolean didTurn = false;

	public AutonomousWrapperGearFirst(AutoOptions mode, boolean isRed) {
		addSequential(new ExtendFlippers());
		addSequential(new ResetEncAndGyro());

		double turnToPeg = TURN_TO_PEG;
		double turnToBoilerSide = TURN_TO_BOILER_SIDE;
		double turnToBoilerMiddle = TURN_TO_BOILER_MIDDLE;

		switch (mode) {

		// The right on the red alliance is FEEDER
		case BOILER_SIDE_NO_SHOOT:
		case BOILER_SIDE_SHOOT:

			if (!isRed) {
				turnToPeg *= -1;
				turnToBoilerSide *= -1;
				// turnToBoilerSide = 120;
			}

			// The robot starts at the right position, breaches on the right
			// and drops the gear
			addSequential(new AutoDriveDistancePID(DIST_A), 3);
			addSequential(new TurnPID(turnToPeg), 2);
			// addSequential(new AutoDriveDistance(DIST_B, 0.9), 2);
			addSequential(new PlaceGearAndBackup(1, true, DIST_B, 0.9), 7);

			if (mode == AutoOptions.BOILER_SIDE_SHOOT) {
				addSequential(new TurnPID(turnToBoilerSide), 2);
				addSequential(new Wait(0.6));
				addSequential(new TurnToBoiler(3225), 2);
				addParallel(new SpinShooterPID());
				// addSequential(new Wait(0.5));
				addParallel(new Agitate(0.45), 15);
			}

			break;

		case FEEDER_SIDE_NO_SHOOT:

			if (isRed) {
				turnToPeg *= -1;
				turnToBoilerSide *= -1;
			}

			addSequential(new AutoDriveDistancePID(DIST_A), 3);

			didTurn = false;
			addSequential(new TurnPID(turnToPeg), 5);
			// addSequential(new AutoDriveDistance(DIST_B, 0.9), 2);
			addSequential(new PlaceGearAndBackup(1, true, DIST_B, 0.9), 7);

			addSequential(new TurnPID(-turnToPeg), 1);
			addSequential(new AutoDriveDistancePID(300), 5);

			break;

		// All middle pos scripts start the same...
		case MIDDLE_NO_SHOOT:
		case MIDDLE_SHOOT:

			if (isRed)
				turnToBoilerMiddle *= -1;

			addSequential(new AutoDriveDistancePID(MIDDLE_DIST), 2);
			addSequential(new PlaceGearAndBackup());

			if (mode == AutoOptions.MIDDLE_NO_SHOOT)
				break;

			addSequential(new TurnPID(turnToBoilerMiddle), 1);
			addSequential(new AutoDriveDistance(20, 0.8), 1);
			addSequential(new Wait(0.5));
			addSequential(new TurnToBoiler(3550), 2);
//			addSequential(new IncrementRPM(70));
			addParallel(new SpinShooterPID());
			addParallel(new Agitate(0.35), 15);
			break;

		// // For all modes other than Do_Nothing...
		// switch (mode) {
		//
		// // From the middle position, breach left (Red alliance) and then
		// // shoot
		// case MIDDLE_BREACH_AND_SHOOT:
		// addSequential(new MiddleBreach(MiddleBreach.LEFT));
		// addSequential(new TurnPID(110));
		// addSequential(new Agitate(0.35));
		// break;
		//
		// // From the middle position, breach left
		// case MIDDLE_BREACH_LEFT:
		// addSequential(new MiddleBreach(MiddleBreach.LEFT));
		// break;
		//
		// // From the middle position, breach right
		// case MIDDLE_BREACH_RIGHT:
		// addSequential(new MiddleBreach(MiddleBreach.RIGHT));
		// break;
		//
		// // From the middle position, shoot (Boiler is on left)
		// case MIDDLE_JUST_SHOOT:
		// addSequential(new TurnPID(-106));
		// addSequential(new AutoDriveDistancePID(36));
		// addSequential(new Agitate(0.35), 5);
		// // addSequential(new AutoDriveDistancePID(35));
		//
		// break;
		// default:
		// break;
		// }
		}
		// //
		// switch (mode) {
		// case LEFT_POS:
		// addSequential(new SetShooterSpeed(3000));
		// addSequential(new AutoDriveDistancePID(FEEDER_DIST_A - 5));
		// addSequential(new TurnPID(-60.0));
		// addSequential(new AutoDriveDistancePID(FEEDER_DIST_B - 10));
		// addSequential(new PlaceGearAndBackup());
		// // addSequential(new TurnPID(-160.0));
		// // addSequential(new AutoDriveDistance(80, 1.0));
		// // addSequential(new Agitate(), 2);
		// // addSequential(new SetShooterSpeed(0));
		//
		// break;
		//
		// case RIGHT_POS:
		// case RIGHT_POS_THEN_SHOOT:
		// // Drives
		// // addSequential(new AutoDriveDistancePID(BOILER_DIST_A));
		// addSequential(new AutoDriveDistancePID(BOILER_DIST_A + 20));
		//
		// addSequential(new TurnPID(60.0));
		// // addSequential(new AutoDriveDistancePID(BOILER_DIST_B - 15));
		// addSequential(new AutoDriveDistancePID(24));
		//
		// addSequential(new PlaceGearAndBackup());
		//
		// if (mode == AutoOptions.RIGHT_POS_THEN_SHOOT) {
		// addSequential(new SetShooterSpeed(4000));
		// addParallel(new SpinShooterPID());
		// // addSequential(new TurnPID(120.0));
		// // addSequential(new AutoDriveDistancePID(60));
		// // addSequential(new TurnPID(-80.0));
		// // addSequential(new AutoDriveDistancePID(100));
		// addSequential(new Playback("Shoot"));
		// addSequential(new Agitate(), 5);
		// }
		//
		// break;
		//
		// case MIDDLE_NO_BREACH:
		// case MIDDLE_BREACH_AND_SHOOT:
		// case MIDDLE_BREACH_LEFT:
		// case MIDDLE_BREACH_RIGHT:
		// case MIDDLE_JUST_SHOOT:
		//
		// addSequential(new AutoDriveDistancePID(MIDDLE_DIST));
		// addSequential(new PlaceGearAndBackup());
		//
		// switch (mode) {
		//
		// // From the middle position, breach right and then shoot
		// case MIDDLE_BREACH_AND_SHOOT:
		// addSequential(new MiddleBreach(MiddleBreach.RIGHT));
		// addSequential(new TurnPID(-110));
		// // addSequential(new facegoal)
		// break;
		//
		// case MIDDLE_BREACH_RIGHT:
		// addSequential(new MiddleBreach(MiddleBreach.RIGHT));
		// break;
		//
		// case MIDDLE_BREACH_LEFT:
		// addSequential(new MiddleBreach(MiddleBreach.LEFT));
		// break;
		//
		// case MIDDLE_JUST_SHOOT:
		// addSequential(new TurnPID(-100));
		// addSequential(new AutoDriveDistancePID(35));
		// // face goal
		// // shoot
		// break;
		// default:
		// break;
		// // }
		// }
		// }
	}
}
