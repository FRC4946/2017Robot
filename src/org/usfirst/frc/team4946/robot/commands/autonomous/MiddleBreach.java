package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleBreach extends CommandGroup {

	public static final boolean LEFT = true;
	public static final boolean RIGHT = false;

	
	public MiddleBreach(boolean shouldBreachLeft) {
		if (shouldBreachLeft) {
			addSequential(new TurnPID(90));
			addSequential(new AutoDriveDistancePID(80));
			addSequential(new TurnPID(-50));
			addSequential(new AutoDriveDistancePID(60));
		} else {
			addSequential(new TurnPID(-90));
			addSequential(new AutoDriveDistancePID(80));
			addSequential(new TurnPID(50));
			addSequential(new AutoDriveDistancePID(60));
		}
	}
}
