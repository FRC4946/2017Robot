package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.RobotConstants;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistance;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 */
public class AutonomousWrapper extends CommandGroup {

    public AutonomousWrapper(int mode, boolean isRed) {
    	
    	if(isRed) {
    		switch (mode){
    		case RobotConstants.Auto.RED_RIGHT_POSITION_SHOOT: 
    			addSequential(new AutoDriveDistancePID (81.18,1.0));
    			addSequential(new TurnPID (-60.0));
    			addSequential(new AutoDriveDistancePID (32.026, 1.0));
    			//drop gear
    			addSequential(new AutoDriveDistancePID(96, -1.0));
    			addSequential(new TurnPID(135.0));
    			//addSequential(new shoot)
    			break;
    		case RobotConstants.Auto.RED_RIGHT_POSITION_NO_SHOOT: 
    			addSequential(new AutoDriveDistancePID(81.18, 1.0));
    			addSequential(new TurnPID(-60.0));
    			addSequential(new AutoDriveDistancePID (32.026, 1.0));
    			//drop gear
    			addSequential(new AutoDriveDistance(30, -1.0));
    			break;
    		case RobotConstants.Auto.RED_LEFT_POSITION_NO_SHOOT:
    			//Drives 
    			addSequential(new AutoDriveDistancePID(67.662, 1.0));
    			addSequential(new TurnPID(60.0));
    			addSequential(new AutoDriveDistancePID(58.936, 1.0));
    			//drop gear
    			addSequential(new AutoDriveDistancePID(30, -1.0));
    			break;
    		case RobotConstants.Auto.RED_MIDDLE_POSITION_BREACH_RIGHT_SHOOT:
    			addSequential(new AutoDriveDistancePID(70.6, 1.0));
    			//drop gear
    			addSequential(new AutoDriveDistancePID(20, -1.0));
    			addSequential(new TurnPID (90));
    			addSequential(new AutoDriveDistance (35, 1.0));
    			addSequential(new TurnPID(-90));
    			addSequential(new AutoDriveDistance(35, 1.0));
    			addSequential(new TurnPID(110));
    			//addSequential(new facegoal) 
    			break;
    		case RobotConstants.Auto.RED_MIDDLE_POSITION_BREACH_LEFT_NO_SHOOT:
    			addSequential(new AutoDriveDistancePID(70.6, 1.0));
    			//Drop Gear
    			addSequential(new AutoDriveDistancePID(20, -1.0));
    			addSequential(new TurnPID (90));
    			addSequential(new AutoDriveDistance (35, 1.0));
    			addSequential(new TurnPID(-90));
    			addSequential(new AutoDriveDistance(35, 1.0));
    			break;
    		case RobotConstants.Auto.RED_MIDDLE_POSITION_NO_BREACH_SHOOT:
    			addSequential(new AutoDriveDistancePID(70.6, 1.0));
    			//drop gear 
    			addSequential(new AutoDriveDistancePID(20, -1.0));
    			addSequential(new TurnPID (-90));
    			addSequential(new AutoDriveDistancePID(50, 1.0));
    			addSequential(new TurnPID(-20));
    			//shoot
    			break;
    		}
    	} else {
    		switch (mode){
    		case RobotConstants.Auto.BLUE_LEFT_POSITION_SHOOT: 
    			addSequential(new AutoDriveDistancePID (81.18,1.0));
    			addSequential(new TurnPID (60.0));
    			addSequential(new AutoDriveDistancePID (32.026, 1.0));
    			//drop gear
    			addSequential(new AutoDriveDistancePID(96, -1.0));
    			addSequential(new TurnPID(135));
    			//addSequential(new shoot)
    			break;
    		case RobotConstants.Auto.BLUE_LEFT_POSITION_NO_SHOOT: 
    			addSequential(new AutoDriveDistancePID(81.18, 1.0));
    			addSequential(new TurnPID(60.0));
    			addSequential(new AutoDriveDistancePID (32.026, 1.0));
    			//drop gear
    			addSequential(new AutoDriveDistance(30, -1.0));
    			break;
    		case RobotConstants.Auto.BLUE_RIGHT_POSITION_NO_SHOOT:
    			//Drives 
    			addSequential(new AutoDriveDistancePID(67.662, 1.0));
    			addSequential(new TurnPID(-60.0));
    			addSequential(new AutoDriveDistancePID(58.936, 1.0));
    			//drop gear
    			addSequential(new AutoDriveDistancePID(30, -1.0));
    			break;
    		case RobotConstants.Auto.BLUE_MIDDLE_POSITION_BREACH_LEFT_SHOOT:
    			addSequential(new AutoDriveDistancePID(70.6, 1.0));
    			//drop gear
    			addSequential(new AutoDriveDistancePID(20, -1.0));
    			addSequential(new TurnPID (-90));
    			addSequential(new AutoDriveDistance (35, 1.0));
    			addSequential(new TurnPID(90));
    			addSequential(new AutoDriveDistance(35, 1.0));
    			addSequential(new TurnPID(-110));
    			//addSequential(new facegoal) 
    			break;
    		case RobotConstants.Auto.BLUE_MIDDLE_POSITION_BREACH_RIGHT_NO_SHOOT:
    			addSequential(new AutoDriveDistancePID(70.6, 1.0));
    			//Drop Gear
    			addSequential(new AutoDriveDistancePID(20, -1.0));
    			addSequential(new TurnPID (90));
    			addSequential(new AutoDriveDistance (35, 1.0));
    			addSequential(new TurnPID(-90));
    			addSequential(new AutoDriveDistance(35, 1.0));
    			break;
    		case RobotConstants.Auto.BLUE_MIDDLE_POSITION_NO_BREACH_SHOOT:
    			addSequential(new AutoDriveDistancePID(70.6, 1.0));
    			//drop gear 
    			addSequential(new AutoDriveDistancePID(20, -1.0));
    			addSequential(new TurnPID (-90));
    			addSequential(new AutoDriveDistancePID(50, 1.0));
    			addSequential(new TurnPID(-20));
    			//shoot
    			break;
    		}
    	}
    	
    }
}
