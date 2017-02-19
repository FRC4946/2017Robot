package org.usfirst.frc.team4946.robot.commands.shooter;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IncrementRPM extends Command {

    public IncrementRPM() {
        // Use requires() here to declare subsystem dependencies
requires(Robot.shooterSubsystem);    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooterSubsystem.setRPM(Robot.shooterSubsystem.getSetRPM()+50);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterSubsystem.feedPID();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
