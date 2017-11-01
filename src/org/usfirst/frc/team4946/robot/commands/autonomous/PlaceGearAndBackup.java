package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistance;
import org.usfirst.frc.team4946.robot.commands.gearpusher.ExtendPusher;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceGearAndBackup extends CommandGroup {

	public PlaceGearAndBackup(double time) {
		this(time, 0, 0);
	}

	public PlaceGearAndBackup(double time, double initDriveDist,
			double initDriveSpeed) {
		this(time, false, initDriveDist, initDriveSpeed);
	}

	public PlaceGearAndBackup(double time, boolean shouldCheck) {
		this(time, shouldCheck, 0, 0);
	}

	public PlaceGearAndBackup(double time, boolean shouldCheck,
			double initDriveDist, double initDriveSpeed) {

		// AutonomousWrapperGearFirst d = new AutonomousWrapperGearFirst(mode,
		// isRed)

//		if (Robot.auto instanceof AutonomousWrapperGearFirst && shouldCheck){
//			
//			System.out.println("\n\n\n checking did turn.....");
//			if (((AutonomousWrapperGearFirst) Robot.auto).didTurn == false)
//				return;
//			
//			System.out.println("\n\n\n turned!\n\n\n");
//
//		}

		if(initDriveDist != 0 || initDriveSpeed != 0)
			addSequential(new AutoDriveDistance(initDriveDist, initDriveSpeed), 2);
		
		addSequential(new ExtendPusher());
		addSequential(new Wait(time));
		addSequential(new AutoDriveDistance(-20, -0.9));
		// addSequential(new RetractPusher());
	}

	public PlaceGearAndBackup() {
		this(0.3);
	}
}
