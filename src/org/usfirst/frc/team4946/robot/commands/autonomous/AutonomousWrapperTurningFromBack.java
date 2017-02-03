package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.RobotConstants;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistance;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.commands.driveTrain.ResetEncAndGyro;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousWrapperTurningFromBack extends CommandGroup {

	static final double FEEDER_DIST_A = 80.062;
	static final double FEEDER_DIST_B = 46.536;

	static final double BOILER_DIST_A = 93.58;
	static final double BOILER_DIST_B = 19.626;

	static final double MIDDLE_DIST = 70.6;

	public AutonomousWrapperTurningFromBack(int mode, boolean isRed) {
		addSequential(new ResetEncAndGyro());
		addSequential(new Wait(1));
//the mark of someone who's taken Grade 12 COMSCI
		if (isRed) {
			switch (mode) {

			// The right on the red alliance is FEEDER
			case RobotConstants.Auto.RIGHT_POSITION:
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_A));
				addSequential(new Wait(1));
				addSequential(new TurnPID(300.0));
				addSequential(new Wait(1));
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_B));
				// drop gear
				// addSequential(new AutoDriveDistancePID(96));
				break;

			// The left on the red alliance is BOILER
			case RobotConstants.Auto.LEFT_POSITION:
			case RobotConstants.Auto.SIDE_GEAR_AND_SHOOT:
				// ^ Intentional fall through

				addSequential(new AutoDriveDistancePID(BOILER_DIST_A));
				addSequential(new Wait(1));
				addSequential(new TurnPID(60.0));
				addSequential(new Wait(1));
				addSequential(new AutoDriveDistancePID(BOILER_DIST_B));
				// drop gear
				// addSequential(new AutoDriveDistancePID(30));

				if (mode == RobotConstants.Auto.SIDE_GEAR_AND_SHOOT) {

					// drop gear
					// addSequential(new AutoDriveDistancePID(96));
				}
				break;

			// Just drive, man, just drive
			case RobotConstants.Auto.BREACH_NO_SHOOT:
				addSequential(new AutoDriveDistancePID(20 * 12));
				break;

			// All middle pos scripts start the same...
			case RobotConstants.Auto.MIDDLE_POSITION_DO_NOTHING:
			case RobotConstants.Auto.MIDDLE_POSITION_BREACH_SHOOT:
			case RobotConstants.Auto.MIDDLE_POSITION_BREACH_LEFT:
			case RobotConstants.Auto.MIDDLE_POSITION_BREACH_RIGHT:
			case RobotConstants.Auto.MIDDLE_POSITION_JUST_SHOOT:

				addSequential(new AutoDriveDistancePID(MIDDLE_DIST));
				// drop gear
				addSequential(new AutoDriveDistancePID(-20));

				// For all modes other than Do_Nothing...
				switch (mode) {

				// From the middle position, breach left and then shoot
				case RobotConstants.Auto.MIDDLE_POSITION_BREACH_SHOOT:
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistancePID(35));
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistancePID(35));
					addSequential(new TurnPID(110));
					// addSequential(new facegoal)
					break;

				// From the middle position, breach left
				case RobotConstants.Auto.MIDDLE_POSITION_BREACH_LEFT:
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistancePID(35));
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistancePID(35));
					break;

				// From the middle position, breach right
				case RobotConstants.Auto.MIDDLE_POSITION_BREACH_RIGHT:
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistancePID(35));
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistancePID(35));
					break;

				// From the middle position, shoot (Boiler is on left)
				case RobotConstants.Auto.MIDDLE_POSITION_JUST_SHOOT:
					addSequential(new TurnPID(100));
					addSequential(new AutoDriveDistancePID(35));
					// face goal
					// shoot
					break;
				}
				break;
			}
		} else {
			
			switch (mode) {
			case RobotConstants.Auto.RIGHT_POSITION:
			case RobotConstants.Auto.SIDE_GEAR_AND_SHOOT:
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_A));
				addSequential(new TurnPID(300.0));
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_A));
				// addSequential(new shoot)
				if (mode == RobotConstants.Auto.SIDE_GEAR_AND_SHOOT) {
					// drop gear
					// addSequential(new AutoDriveDistancePID(96));
				}
				break;
				
			case RobotConstants.Auto.BREACH_NO_SHOOT:
				addSequential(new AutoDriveDistancePID(20 * 12));
				break;
				
			case RobotConstants.Auto.LEFT_POSITION:
				addSequential(new AutoDriveDistancePID(BOILER_DIST_A));
				addSequential(new TurnPID(60.0));
				addSequential(new AutoDriveDistancePID(BOILER_DIST_B));
				// drop gear
				// addSequential(new AutoDriveDistance(30));
				break;
				
			case RobotConstants.Auto.MIDDLE_POSITION_BREACH_SHOOT:
			case RobotConstants.Auto.MIDDLE_POSITION_BREACH_RIGHT:
			case RobotConstants.Auto.MIDDLE_POSITION_BREACH_LEFT:
			case RobotConstants.Auto.MIDDLE_POSITION_JUST_SHOOT:
			case RobotConstants.Auto.MIDDLE_POSITION_DO_NOTHING:
				
				addSequential(new AutoDriveDistancePID(MIDDLE_DIST));
				// drop gear
				addSequential(new AutoDriveDistancePID(-20.0));
				
				switch (mode) {
				case RobotConstants.Auto.MIDDLE_POSITION_BREACH_SHOOT:
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistance(35, 1.0));
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistance(35, 1.0));
					addSequential(new TurnPID(-110));
					// addSequential(new facegoal)
					break;
			    case RobotConstants.Auto.MIDDLE_POSITION_BREACH_RIGHT:
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistance(35, 1.0));
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistance(35, 1.0));
					break;
			    case RobotConstants.Auto.MIDDLE_POSITION_BREACH_LEFT:
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistance(35, 1.0));
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistance(35, 1.0));
			    case RobotConstants.Auto.MIDDLE_POSITION_JUST_SHOOT:
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistancePID(50, 1.0));
					addSequential(new TurnPID(-20));
					// shoot
					break;
			    case RobotConstants.Auto.MIDDLE_POSITION_DO_NOTHING:
					break;	
				}
			}
		}
	}
}
