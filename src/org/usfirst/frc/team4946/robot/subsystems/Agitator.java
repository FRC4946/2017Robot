package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Agitator extends Subsystem {
	VictorSP agitator = new VictorSP(RobotMap.PWM_AGITATOR);
	Solenoid blocker = new Solenoid(RobotMap.PCM_SOLENOID_BLOCKER);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setSpeed(double speed) {
		agitator.set(speed);
	}
	public void setSpeed(boolean doorClosed) {
		blocker.set(false);
	}

	public void setDoor(boolean b) {
		// TODO Auto-generated method stub
		//?????
	}
}
