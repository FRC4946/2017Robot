package org.usfirst.frc.team4946.robot.commands;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Agitate extends Command {

	double m_speed;
	
    public Agitate(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_speed = speed;
    	requires(Robot.agitatorSubsystem);
    }
	
    public Agitate() {
        this(0.4);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.agitatorSubsystem.setSpeed(0.4);
    	Robot.agitatorSubsystem.setDoor(false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.agitatorSubsystem.setSpeed(0.0);
    	Robot.agitatorSubsystem.setDoor(true);
    }

    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}