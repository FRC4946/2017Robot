package org.usfirst.frc.team4946.robot.commands.driveTrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveCurveDistance extends Command {

	double m_distanceToDrive;
	double m_startingDistance;
	double m_distanceTraveled;
	double m_gyroAngle;
	double m_maxSpeed;
	final double k_p = 0.02;
	double m_curve;

	public AutoDriveCurveDistance(double distInches, double maxSpeed,
			double curve) {
		requires(Robot.driveSubsystem);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		m_distanceToDrive = distInches;
		m_maxSpeed = -maxSpeed;
		m_curve = curve;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		m_startingDistance = Robot.driveSubsystem.getEncoderDistance();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveSubsystem.drive(m_maxSpeed,
				m_curve);
		m_distanceTraveled = Robot.driveSubsystem.getEncoderDistance()
				- m_startingDistance;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if (m_distanceToDrive > 0)
			return (m_distanceTraveled >= m_distanceToDrive);
		else
			return (m_distanceTraveled <= m_distanceToDrive);

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
