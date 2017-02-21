package org.usfirst.frc.team4946.robot.commands.driveTrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {

	Timer timer;
	double drive, curve, time;

	public AutoDrive(double drive, double curve, double timeSecs) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveSubsystem);
		timer = new Timer();
		this.drive = drive;
		this.curve = curve;
		this.time = timeSecs;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveSubsystem.drive(drive, curve);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return timer.get() >= time;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveSubsystem.drive(0.0, 0.0);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
