package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.commands.Agitate;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDrive;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveCurveDistance;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistance;
import org.usfirst.frc.team4946.robot.commands.driveTrain.ResetEncAndGyro;
import org.usfirst.frc.team4946.robot.commands.flipper.ExtendFlippers;
import org.usfirst.frc.team4946.robot.commands.shooter.CoverShooter;
import org.usfirst.frc.team4946.robot.commands.shooter.OpenShooter;
import org.usfirst.frc.team4946.robot.commands.shooter.SpinShooterPID;
import org.usfirst.frc.team4946.robot.commands.vision.TurnToBoiler;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousWrapperHopper extends CommandGroup {

	public AutonomousWrapperHopper(boolean isLeft, boolean isRed) {
		addSequential(new ExtendFlippers());
		addSequential(new ResetEncAndGyro());
		// addSequential(new Wait(1));

		double BOILER_DIST = 120;
		double FEEDER_DIST = 195;

		// addSequential(new SetShooterSpeed(3510));
		// addParallel(new SpinShooterPID());

		if (isRed) {

			// if (isLeft) {
			// addSequential(new AutoDriveDistancePID(195));
			// // addSequential(new TurnPID(10));
			// // addSequential(new AutoDriveDistancePID(16));
			// } else {
			// addSequential(new AutoDriveDistance(-BOILER_DIST, -1.0));
			// addSequential(new AutoDrive(0.5, -0.7, 0.4));

			// addSequential(new AutoDriveDistance(-155, -1.0));

			addSequential(new AutoDriveDistance(-110, -0.8));
			addSequential(new AutoDriveCurveDistance(-25, -0.8, -0.45));

			addSequential(new Wait(2));
			addSequential(new AutoDrive(0.0, -0.9, 0.65));
			addSequential(new TurnToBoiler(3530), 2);
			addParallel(new SpinShooterPID());

			addParallel(new Agitate(0.45), 15);

		} else {
			addSequential(new CoverShooter());
			addSequential(new AutoDriveDistance(-100, -0.8));
			addSequential(new AutoDriveCurveDistance(-25, -0.85, 0.7));

			addSequential(new Wait(2));
			addSequential(new AutoDrive(0.8, 0.0, 0.3));
			addSequential(new Wait(1));
			addSequential(new OpenShooter());
			addSequential(new TurnToBoiler(3580), 2);
			// addSequential(new SetShooterSpeed(3530));
			addParallel(new SpinShooterPID());
			addSequential(new Wait(0.5));
			addParallel(new Agitate(0.45), 15);
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