package org.usfirst.frc.team4946.robot.commands.driveTrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnPID extends Command {

	double m_currentAngle;
	double m_curve;
	
    public TurnPID(double turnAngle, double curveValue) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	Robot.driveSubsystem.setGyroSetpoint(turnAngle);
    	m_curve = curveValue;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_currentAngle = Robot.driveSubsystem.getGyroOutput();
    	Robot.driveSubsystem.drive(0.0, m_curve);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return m_currentAngle == Robot.driveSubsystem.getGyroOutput();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
