package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.RobotConstants;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousWrapperShootFirst extends CommandGroup {

    public AutonomousWrapperShootFirst(int autoMode, boolean isRed) {
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
    	final double FEEDER_DIST_A = 81.18; //Distance traveled when going from the side with feeder
    	final double FEEDER_DIST_B = 32.036;

    	final double BOILER_DIST_A = 67.662; //Distance traveled when going from the side with boiler
    	final double BOILER_DIST_B = 58.936;

    	final double MIDDLE_DIST = 70.6; //PLS ME
    	
    	if (isRed) {
    		switch (autoMode) {
			    case RobotConstants.Auto.MIDDLE_POSITION_JUST_SHOOT:
			    	addSequential(new AutoDriveDistancePID(20));
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistancePID(50));
					addSequential(new TurnPID(-20));
					// shoot
					break;
			    case RobotConstants.Auto.LEFT_POSITION: 
			    	addSequential(new AutoDriveDistancePID(20));
			    	addSequential(new TurnPID (90));
			    	//travel some distance 
			    	// shoot
			    	addSequential(new TurnPID(-90));
			    	addSequential(new AutoDriveDistancePID(BOILER_DIST_A - 20));
			    	addSequential(new TurnPID(-60));
			    	addSequential(new AutoDriveDistancePID(BOILER_DIST_B));
			    	//drop gear
			    	addSequential(new AutoDriveDistancePID(-20));
			    	break;
			    case RobotConstants.Auto.RIGHT_POSITION:
			    	addSequential(new AutoDriveDistancePID(20));
			    	addSequential(new TurnPID(90));
			    	//addSequential(new AutoDriveDistancePID(somedistance)); 
			    	//facegoal
			    	//shoot
			    	addSequential(new TurnPID(-90));
			    	addSequential(new AutoDriveDistancePID(BOILER_DIST_A));
			    	addSequential(new TurnPID(-60));
			    	addSequential(new AutoDriveDistancePID(BOILER_DIST_B));
			    	
	    		}
    	} else {
    		switch (autoMode) {
			    case RobotConstants.Auto.MIDDLE_POSITION_JUST_SHOOT:
					addSequential(new TurnPID(-90));
					addSequential(new AutoDriveDistancePID(50));
					addSequential(new TurnPID(-20));
					// shoot
					break;
			    case RobotConstants.Auto.LEFT_POSITION: 
			    	addSequential(new AutoDriveDistancePID(20));
			    	addSequential(new TurnPID (-90));
			    	// shoot
			    	addSequential(new TurnPID(90));
			    	addSequential(new AutoDriveDistancePID(FEEDER_DIST_A - 20));
			    	addSequential(new TurnPID(60));
			    	addSequential(new AutoDriveDistancePID(FEEDER_DIST_B));
			    	//drop gear
			    	addSequential(new AutoDriveDistancePID(-20));
			    	break;
			    case RobotConstants.Auto.RIGHT_POSITION:
			    	addSequential(new AutoDriveDistancePID(20));
			    	addSequential(new TurnPID(-90));
			    	//addSequential(new AutoDriveDistancePID(somedistance)); 
			    	//facegoal
			    	//shoot
			    	addSequential(new TurnPID(90));
			    	addSequential(new AutoDriveDistancePID(FEEDER_DIST_A));
			    	addSequential(new TurnPID(60));
			    	addSequential(new AutoDriveDistancePID(FEEDER_DIST_B));
			    	
			    	
    		}
    	}
    }
}