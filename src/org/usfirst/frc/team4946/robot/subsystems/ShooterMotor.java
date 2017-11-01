package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotConstants;
import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.shooter.SpinShooterPID;
import org.usfirst.frc.team4946.robot.commands.shooter.SpinShooterPercent;
import org.usfirst.frc.team4946.robot.util.RateCounter;
import org.usfirst.frc.team4946.robot.util.SimplePIFController;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterMotor extends Subsystem {

	CANTalon shooterMotorA = new CANTalon(RobotMap.CAN_TALON_SHOOTER_A);
	CANTalon shooterMotorB = new CANTalon(RobotMap.CAN_TALON_SHOOTER_B);

	RateCounter rateCount = new RateCounter(RobotMap.DIO_SHOOTER_SENSOR);
	SimplePIFController pifController;

	public void setBrakeMode(boolean isBreak) {
		shooterMotorA.enableBrakeMode(isBreak);
		shooterMotorB.enableBrakeMode(isBreak);
	}

	int rpm = 0;
	public double percentSpeed = 0;

	// double kP = 0; // TODO: TUNE
	// double kI = 0;
	// // double kF = 0.00010316;
	// double kF = 0.00012899;
	// // double kFOff = 0.18;
	// double kFOff = 0.052882;

	public ShooterMotor() {

		pifController = new SimplePIFController(RobotConstants.shootP,
				RobotConstants.shootI, RobotConstants.shootF,
				RobotConstants.shootFOff, rateCount);
		pifController.setInputRange(0, 8500);
		pifController.setOutputRange(0, 1);
		pifController.reverseError(false);
		pifController.setContinuous(false);
		rateCount.setMaxVal(8500);

	}

	public void updatePID() {
		updatePID(RobotConstants.shootP, RobotConstants.shootI,
				RobotConstants.shootF, RobotConstants.shootFOff);
	}

	public void updatePID(double p, double i, double f, double fOff) {
		pifController.setTunings(p, i, f, fOff);
	}

	// 1.1879E-4
	// 0.064873

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new SpinShooterPID());
		// setDefaultCommand(new SpinShooterPercent());
	}

	public void setSpeed(double speed) {
		percentSpeed = speed;
		shooterMotorA.set(-percentSpeed);
		shooterMotorB.set(-percentSpeed);
	}

	public void setRPM(int rpm) {
		if (rpm < 0)
			return;

		this.rpm = rpm;
		pifController.setSetpoint(this.rpm);
	}

	public void feedPID() {
		double out = pifController.getOutput();

		if (pifController.getSetpoint() > 1000 && out <= 0.21) {
			shooterMotorA.set(-0.2);
			shooterMotorB.set(-0.2);
			return;
		}

		if (Math.abs(out) > 0.05) {
			shooterMotorA.set(-out);
			shooterMotorB.set(-out);

		} else {
			shooterMotorA.set(0);
			shooterMotorB.set(0);

		}
	}

	public double getRPM() {
		return rateCount.getRPM();
	}

	public int getSetRPM() {
		return rpm;
	}

	public void plusFivePercent() {
		percentSpeed += 0.05;
		if (percentSpeed > 1.0)
			percentSpeed = 1.0;
	}

	public void minusFivePercent() {
		percentSpeed -= 0.05;
		if (percentSpeed < -1.0)
			percentSpeed = -1.0;
	}
}
