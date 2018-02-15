package org.usfirst.frc.team4946.robot.commands.driveTrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnAbsolutePID extends Command {

	int m_onTargetCounter = 0;
	double m_setPoint = 0.0;

	public TurnAbsolutePID(double turnAngle) {
		requires(Robot.driveSubsystem);
		m_setPoint = turnAngle;
	}

	protected void initialize() {
		Robot.driveSubsystem.setGyroSetpoint(m_setPoint);
	}

	protected void execute() {
		Robot.driveSubsystem.drive(0.0, Robot.driveSubsystem.getGyroOutput());
	}

	protected boolean isFinished() {

		if (Robot.driveSubsystem.getGyroPIDIsOnTarget()) {
			m_onTargetCounter++;
			if (m_onTargetCounter > 20)
				return true;
		} else
			m_onTargetCounter = 0;

		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
