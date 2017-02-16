package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.RobotConstants;
import org.usfirst.frc.team4946.robot.RobotConstants.Auto;
import org.usfirst.frc.team4946.robot.RobotConstants.Auto.AutoScript;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.commands.driveTrain.ResetEncAndGyro;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;

import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 */
public class AutonomousWrapperHopper extends CommandGroup {
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
    	public AutonomousWrapperHopper(AutoScript mode, boolean isRed) {
    		addSequential(new ResetEncAndGyro());
    		addSequential(new Wait(1));
    		
    		if (isRed) {
    			
    			switch (mode){
			    case HOPPER_LEFT:
			    	addSequential(new AutoDriveDistancePID(195));
			    	addSequential(new TurnPID(-90));
			    	addSequential(new AutoDriveDistancePID(16));
			    	break;
			    case HOPPER_RIGHT:
			    	addSequential(new AutoDriveDistancePID(123));
			    	addSequential(new TurnPID(90));
			    	addSequential(new AutoDriveDistancePID(16));
			    	break;
    			}
    			
    		} else {
    			
    			switch (mode){
			    case HOPPER_LEFT:
			    	addSequential(new AutoDriveDistancePID(123));
			    	addSequential(new TurnPID(-90));
			    	addSequential(new AutoDriveDistancePID(16));
			    	break;
			    case HOPPER_RIGHT:
			    	addSequential(new AutoDriveDistancePID(195));
			    	addSequential(new TurnPID(90));
			    	addSequential(new AutoDriveDistancePID(16));
			    	break; 
    			}
    		}
    	}
    }
