package org.usfirst.frc.team4946.robot.commands.vision;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToGear extends Command {

	int m_onTargetCounter = 0;
	public boolean hasTarget = false;

	public TurnToGear() {
		requires(Robot.driveSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveSubsystem.setVisionTurnRightMode();
		Robot.LEDlightsSubsystem.setGearLedIsEnabled(true);
		Robot.visionSubsystem.m_hasFreshGearAngle = false;
		hasTarget = false;
//		Robot.visionSubsystem.setGearIsActive(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!hasTarget) {
			if (!Robot.visionSubsystem.m_hasFreshGearAngle) {
				Robot.driveSubsystem.drive(0.0, 0.0);
				return;
			}

			double angle = Robot.visionSubsystem.m_gearAngle;
			Robot.driveSubsystem.setGyroSetpoint((angle + Robot.driveSubsystem
					.getGyroValue()) % 360);
			hasTarget = true;
		} else {
			Robot.driveSubsystem.drive(0.0,
					Robot.driveSubsystem.getGyroOutput());
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (!hasTarget) {
			m_onTargetCounter = 0;
			return false;
		}

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
		Robot.driveSubsystem.drive(0.0, 0.0);
		Robot.driveSubsystem.setNormalMode();
		Robot.LEDlightsSubsystem.setGearLedIsEnabled(false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
