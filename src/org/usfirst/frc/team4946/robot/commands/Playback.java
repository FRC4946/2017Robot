package org.usfirst.frc.team4946.robot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Playback extends Command {

	private static final String path = "/home/lvuser/recordedAuto";

	Scanner scanner;
	long startTime;

	boolean onTime = true;
	double nextDouble;
	
	boolean done = false;
	String file;

	public Playback(String file) {
		this.file = file;
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		// create a scanner to read the file created during BTMacroRecord
		// scanner is able to read out the doubles recorded into
		// recordedAuto.csv (as of 2015)
		try {
			scanner = new Scanner(new File(path + file + ".csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// let scanner know that the numbers are separated by a comma or a
		// newline, as it is a .csv file
		scanner.useDelimiter(",|\\n");
		
		
		
		
		// lets set start time to the current time you begin autonomous
		startTime = System.currentTimeMillis();
		done = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		System.out.println("Playback");
		
		// if recordedAuto.csv has a double to read next, then read it
		if ((scanner != null) && (scanner.hasNextDouble())) {
			double t_delta;

			// if we have waited the recorded amount of time assigned to
			// each
			// respective motor value,
			// then move on to the next double value
			// prevents the macro playback from getting ahead of itself and
			// writing different
			// motor values too quickly
			if (onTime) {
				// take next value
				nextDouble = scanner.nextDouble();
			}

			// time recorded for values minus how far into replaying it we
			// are--> if not zero, hold up
			t_delta = nextDouble - (System.currentTimeMillis() - startTime);

			// if we are on time, then set motor values
			if (t_delta <= 0) {

				Robot.driveSubsystem.drive(scanner.nextDouble(),
						scanner.nextDouble());
				// go to next double
				onTime = true;
			}
			// else don't change the values of the motors until we are
			// "onTime"
			else {
				onTime = false;
			}
		}
		// end play, there are no more values to find
		else {
			if (scanner != null) {
				scanner.close();
				scanner = null;
			}
			done = true;
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return done;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveSubsystem.drive(0.0, 0.0);

		if (scanner != null) {
			scanner.close();
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
