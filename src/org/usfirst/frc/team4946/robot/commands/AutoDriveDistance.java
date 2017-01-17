package org.usfirst.frc.team4946.robot.commands;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveDistance extends Command {

	double m_distanceToDrive;
	double distance;
	
    public AutoDriveDistance(double distanceToDrive) {
    	requires(Robot.driveSubsystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_distanceToDrive = distanceToDrive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.drive(0.5, 0.0, 0.0);
    	distance = Robot.driveSubsystem.getEncoderDistance();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (distance >= m_distanceToDrive);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
