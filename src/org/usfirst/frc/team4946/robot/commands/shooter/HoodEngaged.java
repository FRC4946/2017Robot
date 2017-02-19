package org.usfirst.frc.team4946.robot.commands.shooter;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class HoodEngaged extends Command {

	public HoodEngaged() {
		requires(Robot.shooterHoodSubsystem);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.shooterHoodSubsystem.shooterHood(true);
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
