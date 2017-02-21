package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.commands.Agitate;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDrive;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveCurveDistance;
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
public class AutonomousWrapperHopper extends CommandGroup {

	public AutonomousWrapperHopper(boolean isLeft, boolean isRed) {
		addSequential(new ResetEncAndGyro());
		addSequential(new Wait(1));

		double BOILER_DIST = 120;
		double FEEDER_DIST = 195;

		addSequential(new SetShooterSpeed(3530));
		addParallel(new SpinShooterPID());

		if (isRed) {

			if (isLeft) {
				addSequential(new AutoDriveDistancePID(195));
				// addSequential(new TurnPID(10));
				// addSequential(new AutoDriveDistancePID(16));
			} else {
				addSequential(new AutoDriveDistance(-BOILER_DIST, -1.0));
				addSequential(new AutoDrive(0.5, -0.7, 0.4));
				// addSequential(new AutoDrive(1.0, 0.0, 0.2));
				addSequential(new Wait(2));
				addSequential(new AutoDrive(0.0, -0.8, 0.8));
				// addSequential(new AutoDriveDistance(60, 0.8));
				//
				// addSequential(new TurnPID(-125));
				addParallel(new Agitate(0.35), 5);

			}

		} else {

			if (isLeft) {
				addSequential(new AutoDriveDistance(BOILER_DIST - 10, 1.0));
				addSequential(new AutoDrive(0.5, 0.8, 0.5));
				addSequential(new AutoDrive(1.0, 0.0, 0.4));
				addSequential(new Wait(2));
				addSequential(new AutoDrive(1.0, 1.0, 0.5));
				addSequential(new AutoDriveDistance(15, 1.0));

				addSequential(new TurnPID(179));

			} else {
				addSequential(new AutoDriveDistancePID(195));
				// addSequential(new TurnPID(-10));
				// addSequential(new AutoDriveDistancePID(16));
			}
		}
	}
}

/*
 * 
 * al(new AutoDriveDistance(BOILER_DIST-10, 1.0)); addSequential(new
 * AutoDrive(0.5, 0.8, 0.5)); addSequential(new AutoDrive(1.0, 0.0, 0.3));
 * addSequential(new Wait(2)); addSequential(new AutoDrive(0.0, -0.8, 0.4));
 * addSequential(new AutoDriveDistance(-60, -0.8));
 * 
 * addSequential(new TurnPID(130));
 */

/*
 * 
 * 
 * addSequential(new AutoDriveDistance(-BOILER_DIST, -1.0)); addSequential(new
 * AutoDrive(-0.5, -0.8, 0.5)); addSequential(new AutoDrive(-1.0, 0.0, 0.5));
 * addSequential(new Wait(2)); addSequential(new TurnPID(35)); //
 * addSequential(new AutoDriveDistance(60, 0.8)); // // addSequential(new
 * TurnPID(-125)); addSequential(new Agitate(), 5);
 */