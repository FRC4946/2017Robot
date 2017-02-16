
package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.RobotConstants;
import org.usfirst.frc.team4946.robot.RobotConstants.Auto.AutoOptions;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistance;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.commands.driveTrain.ResetEncAndGyro;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;
import org.usfirst.frc.team4946.robot.commands.gearpusher.PushGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousWrapperGearFirst extends CommandGroup {
	
	final double FEEDER_DIST_A = 81.18; //Distance traveled when going from the side with feeder
	final double FEEDER_DIST_B = 32.036;

	final double BOILER_DIST_A = 67.662; //Distance traveled when going from the side with boiler
	final double BOILER_DIST_B = 58.936;

	final double MIDDLE_DIST = 70.6; //PLS MEAUSRE

	public AutonomousWrapperGearFirst(AutoOptions mode, boolean isRed) {
		addSequential(new ResetEncAndGyro());
		addSequential(new Wait(1));
//the mark of someone who's taken Grade 12 COMSCI
		if (isRed) {
			switch (mode) {

			// The right on the red alliance is FEEDER
			case kRightPos:
				//The robot starts at the right position, breaches on the right and drops the gear
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_A));
				addSequential(new Wait(1));
				addSequential(new TurnPID(300.0));
				addSequential(new Wait(1));
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_B));
				addSequential(new PushGear());
				// addSequential(new AutoDriveDistancePID(96));
				break;

			// The left on the red alliance is BOILER
			case kLeftPos:
				// ^ Intentional fall through
				// The robot starts at the left positioon, breaches on the left and drops the gear
				addSequential(new AutoDriveDistancePID(BOILER_DIST_A));
				addSequential(new Wait(1));
				addSequential(new TurnPID(60.0));
				addSequential(new Wait(1));
				addSequential(new AutoDriveDistancePID(BOILER_DIST_B));
				addSequential(new PushGear());
				// addSequential(new AutoDriveDistancePID(30));
				break;

			// Just drive, man, just drive
			case kJustBreach:
				addSequential(new AutoDriveDistancePID(20 * 12));
				break;

			// All middle pos scripts start the same...
			case kMiddleNoBreachNoShoot:
			case kMiddleBreachAndShoot:
			case kMiddleBreachLeft:
			case kMiddleBreachRight:
			case kMiddleJustShoot:

				addSequential(new AutoDriveDistancePID(MIDDLE_DIST));
				addSequential(new PushGear());
				addSequential(new AutoDriveDistancePID(-20));

				// For all modes other than Do_Nothing...
				switch (mode) {

				// From the middle position, breach left and then shoot
				case kMiddleBreachAndShoot:
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistancePID(35));
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistancePID(35));
					addSequential(new TurnPID(110));
					// addSequential(new facegoal)
					break;

				// From the middle position, breach left
				case kMiddleBreachLeft:
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistancePID(35));
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistancePID(35));
					break;

				// From the middle position, breach right
				case kMiddleBreachRight:
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistancePID(35));
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistancePID(35));
					break;

				// From the middle position, shoot (Boiler is on left)
				case kMiddleJustShoot:
					addSequential(new TurnPID(100));
					addSequential(new AutoDriveDistancePID(35));
					// face goal
					// shoot
					break;
				}
			}
		} else {
			switch (mode) {
			case kLeftPos:
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_A));
				addSequential(new TurnPID(60.0));
				addSequential(new AutoDriveDistancePID(FEEDER_DIST_B));
				addSequential(new PushGear());
				// addSequential(new AutoDriveDistance(30));
				break;
			case kJustBreach:
				addSequential(new AutoDriveDistancePID(20 * 12));
				break;
				
			case kRightPos:
				// Drives
				addSequential(new AutoDriveDistancePID(BOILER_DIST_A));
				addSequential(new TurnPID(-60.0));
				addSequential(new AutoDriveDistancePID(BOILER_DIST_B));
				// drop gear
				addSequential(new AutoDriveDistancePID(30, -1.0));
				// turn
				// shoot
				break;
				
				
			case kMiddleNoBreachNoShoot:
			case kMiddleBreachAndShoot:
			case kMiddleBreachLeft:
			case kMiddleBreachRight:
				
				addSequential(new AutoDriveDistancePID(MIDDLE_DIST));
				addSequential(new PushGear());
				addSequential(new AutoDriveDistancePID(-20.0));
				
				switch (mode) {
				case kMiddleBreachAndShoot:
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistance(35, 1.0));
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistance(35, 1.0));
					addSequential(new TurnPID(-110));
					// addSequential(new facegoal)
					break;
			    case kMiddleBreachRight:
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistance(35, 1.0));
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistance(35, 1.0));
					break;
			    case kMiddleBreachLeft:
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistance(35, 1.0));
					addSequential(new TurnPID(90));
					addSequential(new AutoDriveDistance(35, 1.0));
			    case kMiddleNoBreachNoShoot:
					break;
			    	
				}
			}
		}			
	}
}

		