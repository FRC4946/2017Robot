package org.usfirst.frc.team4946.robot.commands.flipper;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.subsystems.Flippers;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExtendFlippers extends Command {

	int counter = 0;

	public ExtendFlippers() {
		requires(Robot.flipperSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		counter = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.flipperSubsystem.extendWings();
		counter++;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return counter > 10;
	}

	// Called once after isFinished returns true
	protected void end() {
//		Robot.flipperSubsystem.cancel();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
