package org.usfirst.frc.team4946.robot.commands;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveDistancePID extends Command {

	double m_distanceToDrive;
	double m_startingDistance;
	double m_distanceTraveled;
	double m_gyroAngle;
	double m_maxSpeed;

	public AutoDriveDistancePID(double distInches, double maxSpeed) {
		requires(Robot.driveSubsystem);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		m_distanceToDrive = distInches;
		m_maxSpeed = maxSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
    	Robot.driveSubsystem.enableEncoders();
		m_startingDistance = Robot.driveSubsystem.getEncoderDistance();
		Robot.driveSubsystem.setGyroSetpoint(Robot.driveSubsystem.getGyroValue());
		Robot.driveSubsystem.setEncoderSetpoint(m_distanceToDrive);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveSubsystem.drive(m_maxSpeed, Robot.driveSubsystem.getGyroOutput());
		m_distanceTraveled = Robot.driveSubsystem.getEncoderOutput();
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
