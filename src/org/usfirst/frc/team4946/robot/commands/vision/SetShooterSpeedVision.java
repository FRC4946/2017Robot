package org.usfirst.frc.team4946.robot.commands.vision;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterSpeedVision extends Command {

	public SetShooterSpeedVision() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.shooterSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.LEDlightsSubsystem.setShooterLedIsEnabled(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.LEDlightsSubsystem.setShooterLedIsEnabled(true);

		if (!Robot.visionSubsystem.jetsonHasFreshValue()) {
			Robot.shooterSubsystem.setRPM(0);
			Robot.shooterSubsystem.feedPID();
			return;
		}

		int rpm = (int) (Robot.visionSubsystem.getShooterDistInches() * RobotConstants.rpm_M)
				+ RobotConstants.rpm_B;
		Robot.shooterSubsystem.setRPM(rpm);
		Robot.shooterSubsystem.feedPID();

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.LEDlightsSubsystem.setShooterLedIsEnabled(false);
		Robot.shooterSubsystem.setRPM(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
