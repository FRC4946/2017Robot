package org.usfirst.frc.team4946.robot.commands.shooter;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class Up5Percent extends InstantCommand {

	public Up5Percent() {
		super();

		requires(Robot.shooterSubsystem);
	}

	// Called once when the command executes
	protected void initialize() {
		Robot.shooterSubsystem.plusFivePercent();
	}

}
