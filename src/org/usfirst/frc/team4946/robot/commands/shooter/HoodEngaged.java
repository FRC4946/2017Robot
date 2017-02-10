package org.usfirst.frc.team4946.robot.commands.shooter;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class HoodEngaged extends Command{

	public HoodEngaged () {
		requires(Robot.shooterHoodSubsystem);
	}

	private void requires(Class<Robot> class1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		Robot.shooterHoodSubsystem.shooterHood(true);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooterHoodSubsystem.shooterHood(true);	
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
