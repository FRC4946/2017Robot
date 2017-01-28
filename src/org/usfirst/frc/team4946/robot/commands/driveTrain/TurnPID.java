package org.usfirst.frc.team4946.robot.commands.driveTrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnPID extends Command {

	double m_currentAngle;
	double m_curve;
	int m_onTargetCounter = 0;
	
    public TurnPID(double turnAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	Robot.driveSubsystem.setGyroSetpoint(turnAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.drive(0.0, Robot.driveSubsystem.getGyroOutput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		
    	return Robot.driveSubsystem.getGyroPIDIsOnTarget();
    	
    /**	SmartDashboard.putBoolean("on Target",
				Robot.driveSubsystem.getGyroPIDIsOnTarget());
		if (Robot.driveSubsystem.getGyroPIDIsOnTarget()) {
			m_onTargetCounter++;
			if (m_onTargetCounter > 20)
				return true;
		} else
			m_onTargetCounter = 0;

		return false;
		
		**/
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
