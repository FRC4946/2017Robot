package org.usfirst.frc.team4946.robot.commands.driveTrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveDistance extends Command {

	double m_distanceToDrive;
	double m_startingDistance;
	double m_distanceTraveled = 0;
	double m_maxSpeed;

	public AutoDriveDistance(double distInches, double maxSpeed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveSubsystem);

		m_distanceToDrive = distInches;
		m_maxSpeed = -maxSpeed;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		m_startingDistance = Robot.driveSubsystem.getEncoderDistance();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("Driving");

		Robot.driveSubsystem.drive(m_maxSpeed, 0.0);

		m_distanceTraveled = Robot.driveSubsystem.getEncoderDistance()
				- m_startingDistance;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// return false;
		if (m_distanceToDrive > 0)
			return (m_distanceTraveled >= m_distanceToDrive);
		else
			return (m_distanceTraveled <= m_distanceToDrive);
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("Donezo");
		Robot.driveSubsystem.drive(0.0, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
