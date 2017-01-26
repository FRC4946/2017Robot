package org.usfirst.frc.team4946.robot.commands.driveTrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveStraightDistance extends Command {

	double m_distanceToDrive;
	double m_startingDistance;
	double m_distanceTraveled;
	double m_gyroAngle;
	double m_maxSpeed;
	final double k_p = 0.03;
	
    public AutoDriveStraightDistance(double distInches, double maxSpeed) {
    	requires(Robot.driveSubsystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_distanceToDrive = distInches;
    	m_maxSpeed = maxSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_startingDistance = Robot.driveSubsystem.getEncoderDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       	m_gyroAngle = Robot.driveSubsystem.getGyroValue();
    	Robot.driveSubsystem.drive(m_maxSpeed/2, m_gyroAngle*k_p, 0.0);
    	m_distanceTraveled = Robot.driveSubsystem.getEncoderDistance() - m_startingDistance;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (m_distanceTraveled >= m_distanceToDrive);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.drive(0.0, 0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.drive(0.0, 0.0, 0.0);
    }
}
