package org.usfirst.frc.team4946.robot.commands;

import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Record extends Command {

	private static final String path = "/home/lvuser/recordedAuto";

	// this object writes values into the file we specify
	FileWriter writer;
	String file;

	long startTime;

	/**
	 * Create a {@link Record} object, to record the driver's movements to a
	 * file, allowing the actions to be reproduced in Autonomous
	 * 
	 * @param file the file to write to
	 */
	public Record(String file) {
		this.file = file;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Use requires() here to declare subsystem dependencies
		// put the filesystem location you are supposed to write to as a string
		// as the argument in this method, as of 2015 it is
		// /home/lvuser/recordedAuto.csv
		try {
			writer = new FileWriter(path + file + ".csv");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// record the time we started recording
		startTime = System.currentTimeMillis();
		
	}


	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (writer != null) {

			
			try {

				// start each "frame" with the elapsed time since we started
				// recording
				writer.append("" + (System.currentTimeMillis() - startTime));

				// in this chunk, use writer.append to add each type of data you
				// want to record to the frame
				// the 2015 robot used the following motors during auto

				// drive motors
				writer.append("," + Robot.oi.getDriveJoystick().getRawAxis(1));
				writer.append("," + Robot.oi.getDriveJoystick().getRawAxis(0)
						+ "\n");

				/*
				 * THE LAST ENTRY OF THINGS YOU RECORD NEEDS TO HAVE A DELIMITER
				 * CONCATENATED TO THE STRING AT THE END. OTHERWISE GIVES
				 * NOSUCHELEMENTEXCEPTION
				 */

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		try {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
