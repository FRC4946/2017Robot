package org.usfirst.frc.team4946.robot.commands.driveTrain;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.commands.autonomous.AutonomousWrapperGearFirst;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnPID extends Command {

	int m_onTargetCounter = 0;
	double m_setPoint = 0.0;

	public TurnPID(double turnAngle) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveSubsystem);
		m_setPoint = -turnAngle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Robot.driveSubsystem.resetGyro();
		Robot.driveSubsystem.setGyroSetpoint((m_setPoint + Robot.driveSubsystem
				.getGyroValue()) % 360);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double out = Robot.driveSubsystem.getGyroOutput();

		Robot.driveSubsystem.drive(0.0, out);
		SmartDashboard.putNumber("Out", out);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//

		double angle = Robot.driveSubsystem.getGyroValue();
		while (angle < 0)
			angle += 360;
		angle %= 360;

		double set = Robot.driveSubsystem.getGyroSetpoint();
		while (set < 0)
			set += 360;
		set %= 360;

		SmartDashboard.putNumber("err", Math.abs(angle - set));

		if (Math.abs(angle - set) < 15) {
			m_onTargetCounter++;
			if (m_onTargetCounter > 20) {

				if (Robot.auto instanceof AutonomousWrapperGearFirst)
					((AutonomousWrapperGearFirst) Robot.auto).didTurn = true;
				
				System.out.println("\n\n\n\n\n aaaaaaaaa \n\n\n\n\n");
				
				return true;
			}
		} else
			m_onTargetCounter = 0;

		return false;
	}

	// Called once after is Finished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
