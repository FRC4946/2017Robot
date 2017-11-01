package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flippers extends Subsystem {

	Solenoid leftFlipper = new Solenoid(
			RobotMap.PCM_LEFT_FLIPPER);
	Solenoid rightFlipper = new Solenoid(
			RobotMap.PCM_RIGHT_FLIPPER);

	public void initDefaultCommand() {
	}

	public void extendWings() {
		leftFlipper.set(true);
		rightFlipper.set(true);
	}

	public void cancel() {
		leftFlipper.set(false);
		rightFlipper.set(false);
	}
}
