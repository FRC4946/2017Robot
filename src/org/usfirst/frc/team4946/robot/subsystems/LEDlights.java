package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDlights extends Subsystem {

	Relay gearLED = new Relay(RobotMap.RELAY_GEAR_LED);
	Relay shooterLED = new Relay(RobotMap.RELAY_SHOOTER_LED);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public LEDlights() {
		gearLED.setDirection(Direction.kForward);
		shooterLED.setDirection(Direction.kForward);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setGearLedIsEnabled(boolean gearLedOn) {

		if (gearLedOn == true) {
			gearLED.set(Value.kOn);
		} else {
			gearLED.set(Value.kOff);
		}

	}

	public void setShooterLedIsEnabled(boolean shooterLedOn) {

		if (shooterLedOn == true) {
			shooterLED.set(Value.kOn);
		} else {
			shooterLED.set(Value.kOff);
		}
	}
}
