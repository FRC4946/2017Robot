package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.RobotConstants.Auto.AutoOptions;
import org.usfirst.frc.team4946.robot.commands.Agitate;
import org.usfirst.frc.team4946.robot.commands.Playback;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistance;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.commands.driveTrain.ResetEncAndGyro;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;
import org.usfirst.frc.team4946.robot.commands.shooter.SetShooterSpeed;
import org.usfirst.frc.team4946.robot.commands.shooter.SpinShooterPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousWrapperGearFirst extends CommandGroup {

	final double FEEDER_DIST_A = 81.18; // Distance traveled when going from the
										// side with feeder
	final double FEEDER_DIST_B = 32.036 + 10;

	final double BOILER_DIST_A = 72; // Distance traveled when going from
										// the side with boiler
	final double BOILER_DIST_B = 58.936 + 10;

	final double MIDDLE_DIST = 70.6; // PLS MEAUSRE

	public AutonomousWrapperGearFirst(AutoOptions mode, boolean isRed) {
		addSequential(new ResetEncAndGyro());
		addSequential(new Wait(1));

		addSequential(new SetShooterSpeed(3805));
		addParallel(new SpinShooterPID());

		// the mark of someone who's taken Grade 12 COMSCI
		if (isRed) {
			switch (mode) {

			// The right on the red alliance is FEEDER
			case RIGHT_POS:
				// The robot starts at the right position, breaches on the right
				// and drops the gear
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_A));
				addSequential(new TurnPID(60.0));
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_B));
				addSequential(new PlaceGearAndBackup());
				break;

			// The left on the red alliance is BOILER
			case LEFT_POS:
				// The robot starts at the left position, breaches on the left
				// and drops the gear
				addSequential(new AutoDriveDistancePID(BOILER_DIST_A));
				addSequential(new TurnPID(-60.0));
				addSequential(new AutoDriveDistancePID(BOILER_DIST_B));
				addSequential(new PlaceGearAndBackup());
				break;

			// All middle pos scripts start the same...
			case MIDDLE_NO_BREACH:
			case MIDDLE_BREACH_AND_SHOOT:
			case MIDDLE_BREACH_LEFT:
			case MIDDLE_BREACH_RIGHT:
			case MIDDLE_JUST_SHOOT:

				addSequential(new AutoDriveDistancePID(MIDDLE_DIST));
				addSequential(new PlaceGearAndBackup());

				// For all modes other than Do_Nothing...
				switch (mode) {

				// From the middle position, breach left (Red alliance) and then
				// shoot
				case MIDDLE_BREACH_AND_SHOOT:
					addSequential(new MiddleBreach(MiddleBreach.LEFT));
					addSequential(new TurnPID(110));
					addSequential(new Agitate(0.35));
					break;

				// From the middle position, breach left
				case MIDDLE_BREACH_LEFT:
					addSequential(new MiddleBreach(MiddleBreach.LEFT));
					break;

				// From the middle position, breach right
				case MIDDLE_BREACH_RIGHT:
					addSequential(new MiddleBreach(MiddleBreach.RIGHT));
					break;

				// From the middle position, shoot (Boiler is on left)
				case MIDDLE_JUST_SHOOT:
					addSequential(new TurnPID(-106));
					addSequential(new AutoDriveDistancePID(36));
					addSequential(new Agitate(0.35), 5);
					// addSequential(new AutoDriveDistancePID(35));

					break;
				default:
					break;
				}
			}
		} else {
			switch (mode) {
			case LEFT_POS:
				addSequential(new SetShooterSpeed(3000));
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_A - 5));
				addSequential(new TurnPID(-60.0));
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_B - 10));
				addSequential(new PlaceGearAndBackup());
				addSequential(new TurnPID(-160.0));
				addSequential(new AutoDriveDistance(80, 1.0));
				addSequential(new Agitate(), 2);
				addSequential(new SetShooterSpeed(0));

				break;

			case RIGHT_POS:
			case RIGHT_POS_THEN_SHOOT:
				// Drives
				// addSequential(new AutoDriveDistancePID(BOILER_DIST_A));
				addSequential(new AutoDriveDistancePID(BOILER_DIST_A + 20));

				addSequential(new TurnPID(60.0));
				// addSequential(new AutoDriveDistancePID(BOILER_DIST_B - 15));
				addSequential(new AutoDriveDistancePID(24));

				addSequential(new PlaceGearAndBackup());

				if (mode == AutoOptions.RIGHT_POS_THEN_SHOOT) {
					addSequential(new SetShooterSpeed(4000));
					addParallel(new SpinShooterPID());
//					addSequential(new TurnPID(120.0));
//					addSequential(new AutoDriveDistancePID(60));
//					addSequential(new TurnPID(-80.0));
//					addSequential(new AutoDriveDistancePID(100));
					addSequential(new Playback("Shoot"));
					addSequential(new Agitate(), 5);
				}

				break;

			case MIDDLE_NO_BREACH:
			case MIDDLE_BREACH_AND_SHOOT:
			case MIDDLE_BREACH_LEFT:
			case MIDDLE_BREACH_RIGHT:
			case MIDDLE_JUST_SHOOT:

				addSequential(new AutoDriveDistancePID(MIDDLE_DIST));
				addSequential(new PlaceGearAndBackup());

				switch (mode) {

				// From the middle position, breach right and then shoot
				case MIDDLE_BREACH_AND_SHOOT:
					addSequential(new MiddleBreach(MiddleBreach.RIGHT));
					addSequential(new TurnPID(-110));
					// addSequential(new facegoal)
					break;

				case MIDDLE_BREACH_RIGHT:
					addSequential(new MiddleBreach(MiddleBreach.RIGHT));
					break;

				case MIDDLE_BREACH_LEFT:
					addSequential(new MiddleBreach(MiddleBreach.LEFT));
					break;

				case MIDDLE_JUST_SHOOT:
					addSequential(new TurnPID(-100));
					addSequential(new AutoDriveDistancePID(35));
					// face goal
					// shoot
					break;
				default:
					break;
				}
			}
		}
	}
}
