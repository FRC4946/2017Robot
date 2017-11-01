package org.usfirst.frc.team4946.robot.commands.shooter;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CoverShooter extends Command {

	public CoverShooter() {
		requires(Robot.shooterCoverSubsystem);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.shooterCoverSubsystem.setShooterIsCovered(true);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
