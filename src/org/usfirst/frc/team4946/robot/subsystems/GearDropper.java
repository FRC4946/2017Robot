package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearDropper extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
//	Solenoid m_doorOpener1 = new Solenoid(RobotMap.PCM_DOOR_OPENER_1); // <-
																		// change
																		// later
//	Solenoid m_doorOpener2 = new Solenoid(RobotMap.PCM_DOOR_OPENER_2); // change
																		// later
	Solenoid m_gearPusher = new Solenoid(RobotMap.PCM_GEAR_PUSHER); // change
																	// later

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setGearIsExtended(boolean gearIsExtended) {
		m_gearPusher.set(gearIsExtended);
	}

	public void doorToggle() {
		// Toggles door on and off
//		boolean onOrNot = m_doorOpener1.get();
//		m_doorOpener1.set(!onOrNot);
//		m_doorOpener2.set(!onOrNot);

	}

	public void toggleGear() {
		m_gearPusher.set(!m_gearPusher.get());
	}

}
