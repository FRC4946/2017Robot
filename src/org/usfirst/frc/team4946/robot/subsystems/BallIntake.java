package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.intake.AutoIntake;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallIntake extends Subsystem {
	VictorSP intakeMotor = new VictorSP(RobotMap.PWM_INTAKE_MOTOR);
	DigitalInput ballSensor = new DigitalInput(RobotMap.DIO_BALL_SENSOR);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		 setDefaultCommand(new AutoIntake());
	}

	public void setSpeed(double speed) {
		intakeMotor.set(speed);
	}

	public boolean hasBall() {
		return ballSensor.get();
	}
}
