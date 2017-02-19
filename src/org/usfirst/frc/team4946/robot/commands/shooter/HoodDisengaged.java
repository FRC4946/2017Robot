package org.usfirst.frc.team4946.robot.commands.shooter;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class HoodDisengaged extends Command{

	public HoodDisengaged () {
		requires(Robot.shooterHoodSubsystem);
	}

	@Override
	protected void initialize() {		
	}

	@Override
	protected void execute() {
		Robot.shooterHoodSubsystem.shooterHood(false);
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
