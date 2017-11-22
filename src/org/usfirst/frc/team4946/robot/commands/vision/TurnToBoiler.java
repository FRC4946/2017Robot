package org.usfirst.frc.team4946.robot.commands.vision;
/*
import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.RobotConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurnToBoiler extends Command {

	int m_onTargetCounter = 0;
	public boolean hasTarget = false;
	public boolean isOnTarget = false;
	Timer m_timer;

	int m_backupRPM = -1;

	public TurnToBoiler() {
		this(-1);
	}

	public TurnToBoiler(int rpm) {
		requires(Robot.driveSubsystem);
		requires(Robot.shooterSubsystem);
		m_timer = new Timer();
		m_backupRPM = rpm;
		isOnTarget = false;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveSubsystem.setVisionTurnRightMode();
		Robot.LEDlightsSubsystem.setShooterLedIsEnabled(true);
		Robot.LEDlightsSubsystem.setGearLedIsEnabled(false);
		hasTarget = false;
		m_onTargetCounter = 0;
		m_timer.stop();
		m_timer.reset();
		isOnTarget = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.LEDlightsSubsystem.setShooterLedIsEnabled(true);
		Robot.shooterSubsystem.feedPID();

		if (m_timer.get() > 1.5 && m_timer.get() < 2.2) {
			Robot.driveSubsystem.drive(0.0, 0.0);
			return;
		}
		if (m_timer.get() >= 2.2) {
			m_timer.reset();
			double angle = Robot.visionSubsystem.getShooterAngle();
			// if (angle < 0)
			// Robot.driveSubsystem.setVisionTurnLeftMode();
			// else
			// Robot.driveSubsystem.setVisionTurnRightMode();

			System.out.println("ANGLE: " + angle + "\t|"
					+ Robot.visionSubsystem.getShooterAngle());
			if (Math.abs(angle) < 1.5) {
				isOnTarget = true;
				return;
			} else
				isOnTarget = false;

			Robot.driveSubsystem.setGyroSetpoint(angle
					+ Robot.driveSubsystem.getGyroValue());
		}

		if (!hasTarget) {

			if (!Robot.visionSubsystem.jetsonHasFreshValidValue()) {
				Robot.driveSubsystem.drive(0.0, 0.0);
				Robot.shooterSubsystem.setRPM(m_backupRPM);
				Robot.shooterSubsystem.feedPID();
				return;
			}

			double angle = Robot.visionSubsystem.getShooterAngle();
			System.out.println("ANGLE: " + angle + "\t|"
					+ Robot.visionSubsystem.getShooterAngle());

			if (Math.abs(angle) < 1.5) {
				isOnTarget = true;
			} else {
				isOnTarget = false;

				// if (angle < 0)
				// Robot.driveSubsystem.setVisionTurnLeftMode();
				// else
				// Robot.driveSubsystem.setVisionTurnRightMode();

				Robot.driveSubsystem.setGyroSetpoint(angle
						+ Robot.driveSubsystem.getGyroValue());
			}
			
			
			hasTarget = true;
			m_timer.start();
		} else {
			int rpm = (int) (Robot.visionSubsystem.getShooterDistInches() * RobotConstants.rpm_M)
					+ RobotConstants.rpm_B;

			if (Robot.visionSubsystem.getShooterDistInches() > 130)
				rpm += 50;

			if (rpm < 3200 && m_backupRPM != -1)
				Robot.shooterSubsystem.setRPM(m_backupRPM);
			else
				Robot.shooterSubsystem.setRPM(rpm);
			Robot.shooterSubsystem.feedPID();

			if (!isOnTarget)
				Robot.driveSubsystem.drive(0.0,
						Robot.driveSubsystem.getGyroOutput());
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(isOnTarget)
			return true;
		
		
		if (!hasTarget) {
			m_onTargetCounter = 0;
			Robot.LEDlightsSubsystem.setGearLedIsEnabled(false);
			return false;
		}

		if (Robot.driveSubsystem.getGyroPIDIsOnTarget()) {
			Robot.LEDlightsSubsystem.setGearLedIsEnabled(true);
			m_onTargetCounter++;
			// if (m_onTargetCounter > 20)
			// return true;
		} else {
			m_onTargetCounter = 0;
			Robot.LEDlightsSubsystem.setGearLedIsEnabled(false);
		}

		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		if (!hasTarget)
			Robot.shooterSubsystem.setRPM(m_backupRPM);

		Robot.driveSubsystem.drive(0.0, 0.0);
		Robot.driveSubsystem.setNormalMode();
		Robot.LEDlightsSubsystem.setShooterLedIsEnabled(false);
		Robot.LEDlightsSubsystem.setGearLedIsEnabled(false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
} */
