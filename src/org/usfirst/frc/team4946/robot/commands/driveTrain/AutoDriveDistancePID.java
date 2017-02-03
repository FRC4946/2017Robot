package org.usfirst.frc.team4946.robot.commands.driveTrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveDistancePID extends Command {

	double m_distanceToDrive;
	double m_startingDistance;
	double m_distanceTraveled;
	double m_gyroAngle;
	double m_maxSpeed;

	double m_onTargetCounter = 0;

	public AutoDriveDistancePID(double distInches) {
		requires(Robot.driveSubsystem);

		m_distanceToDrive = distInches;
	}

	public AutoDriveDistancePID(double distInches, double maxSpeed) {
		this(distInches);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		m_startingDistance = Robot.driveSubsystem.getEncoderDistance();
		Robot.driveSubsystem.setGyroSetpoint(Robot.driveSubsystem
				.getGyroValue());
		Robot.driveSubsystem.setDrivePIDSetpoint(m_distanceToDrive
				+ m_startingDistance);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double magnitude = Robot.driveSubsystem.getDrivePIDOutput();
		double curve = Robot.driveSubsystem.getGyroOutput();

		Robot.driveSubsystem.drive(magnitude, curve);

		m_distanceTraveled = Robot.driveSubsystem.getEncoderDistance();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		SmartDashboard.putBoolean("on Target",
				Robot.driveSubsystem.getDrivePIDIsOnTarget());
		if (Robot.driveSubsystem.getDrivePIDIsOnTarget()) {
			m_onTargetCounter++;
			if (m_onTargetCounter > 20)
				return true;
		} else
			m_onTargetCounter = 0;

		return false;
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
