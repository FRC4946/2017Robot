package org.usfirst.frc.team4946.robot.commands.intake;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoIntake extends Command {

	Timer timer = new Timer();
	boolean hasBall = false;
	boolean run = false;

	public AutoIntake() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.ballSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		hasBall = false;
		run = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.oi.getIntakeDisabledButton()){
			Robot.ballSubsystem.setSpeed(0);
			timer.stop();
			timer.reset();
			hasBall = false;
			run = false;
			return;
		}
			
		
		
		if (Robot.ballSubsystem.hasBall()) {
			hasBall = true;
			Robot.ballSubsystem.setSpeed(0.8);
		} else {
			// Just released ball
			if (hasBall) {
				timer.reset();
				timer.start();
				hasBall = false;
				run = true;
			}

			if (timer.get() < 1.2 && run)
				Robot.ballSubsystem.setSpeed(0.8);
			else {
				run = false;
				timer.stop();
				Robot.ballSubsystem.setSpeed(0.0);
			}

		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		timer.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
