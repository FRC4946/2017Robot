package org.usfirst.frc.team4946.robot.commands;

import org.usfirst.frc.team4946.robot.RobotConstants;

import DriveTrainCommands.AutoDriveDistance;
import DriveTrainCommands.AutoDriveDistancePID;
import DriveTrainCommands.TurnPID;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 */
public class AutonomousWrapper extends CommandGroup {

    public AutonomousWrapper() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	int position = 0;
    	
    	switch (position){
    		case RobotConstants.LEFT_POSITION_SHOOT: 
    			addSequential(new AutoDriveDistancePID (67.662,1.0));
    			addSequential(new TurnPID (60.0, 0.5));
    			
    			break;
    		case RobotConstants.LEFT_POSITION_NO_SHOOT: 
    			addSequential(new AutoDriveDistancePID(67.662, 1.0));
    			addSequential(new TurnPID(60.0, 0.5));
    			
    			break;
    		case RobotConstants.RIGHT_POSITION_NO_SHOOT:
    			//Drives 
    			addSequential(new AutoDriveDistancePID(67.662, 1.0));
    			addSequential(new TurnPID(-60.0, 0.5));
    			addSequential(new AutoDriveDistancePID(58.936, 1.0));
    			break;
    		case RobotConstants.MIDDLE_POSITION_BREACH_LEFT_SHOOT:
    			addSequential(new AutoDriveDistancePID(70.6, 1.0));
    			addSequential(new AutoDriveDistancePID(-20, 1.0));
    			addSequential(new TurnPID (-90, 0.5));
    			addSequential(new AutoDriveDistance (35, 1.0));
    			addSequential(new TurnPID(90,0.5));
    			addSequential(new AutoDriveDistance(25, 1.0));
    			//addSequential(new facegoal) 
    			break;
    		case RobotConstants.MIDDLE_POSITION_BREACH_RIGHT_NO_SHOOT:
    			addSequential(new AutoDriveDistancePID(70.6, 1.0));
    			addSequential(new AutoDriveDistancePID(-20, 1.0));
    			addSequential(new TurnPID (90, 0.5));
    			addSequential(new AutoDriveDistance (35, 1.0));
    			addSequential(new TurnPID(-90,0.5));
    			addSequential(new AutoDriveDistance(25, 1.0));
    			break;
    		case RobotConstants.MIDDLE_POSITION_NO_BREACH_SHOOT:
    			addSequential(new AutoDriveDistancePID(70.6, 1.0));
    			addSequential(new AutoDriveDistancePID(-20, 1.0));
    			addSequential(new TurnPID (-90, 0.5));
    			addSequential(new AutoDriveDistance (35, 1.0));
    			break;
    	
    	}
    	
    }
}
