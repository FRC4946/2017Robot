package org.usfirst.frc.team4946.robot.commands.padpusher;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ExtendPusher extends InstantCommand {

    public ExtendPusher() {
    	requires(Robot.padPusherSubsystem);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.padPusherSubsystem.extend();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
