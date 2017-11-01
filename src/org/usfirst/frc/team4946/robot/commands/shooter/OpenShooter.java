package org.usfirst.frc.team4946.robot.commands.shooter;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class OpenShooter extends Command{

	public OpenShooter () {
		requires(Robot.shooterCoverSubsystem);
	}

	@Override
	protected void initialize() {		
	}

	@Override
	protected void execute() {
		Robot.shooterCoverSubsystem.setShooterIsCovered(false);
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
