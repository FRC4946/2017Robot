package org.usfirst.frc.team4946.robot.commands.driveTrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveRobot extends Command {

    public DriveRobot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Joystick stick = Robot.oi.getDriveJoystick();
    	double drive = stick.getRawAxis(1);
    	double curve = stick.getRawAxis(0) * 0.8;
    	//double throttle = stick.getRawAxis(3);
    	
    	double throttle = 1;
    	
    	Robot.driveSubsystem.drive(drive, curve, throttle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.drive(0.0, 0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.drive(0.0, 0.0, 0.0);
    }
}
