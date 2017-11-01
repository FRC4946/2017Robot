package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PadPusher extends Subsystem {

	Solenoid pusher = new Solenoid(RobotMap.PCM_PAD_PUSHER);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void extend() {
		pusher.set(true);
	}

	public void retract() {
		pusher.set(false);
	}
}
