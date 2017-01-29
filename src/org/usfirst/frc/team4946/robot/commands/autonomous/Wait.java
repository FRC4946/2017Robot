package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {

	Timer m_waitTimer = new Timer();
	double m_timeToWait; //seconds
	
    public Wait(double waitTimeSeconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballSubsystem);
    	requires(Robot.driveSubsystem);
    	m_timeToWait = waitTimeSeconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_waitTimer.reset();
    	m_waitTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (m_waitTimer.get() >= m_timeToWait);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
