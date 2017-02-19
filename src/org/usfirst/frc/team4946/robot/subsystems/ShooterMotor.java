package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotConstants;
import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.shooter.SpinShooterPID;
import org.usfirst.frc.team4946.robot.util.RateCounter;
import org.usfirst.frc.team4946.robot.util.SimplePIFController;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterMotor extends Subsystem {

	CANTalon shooterMotor = new CANTalon(RobotMap.CAN_TALON_SHOOTER);
	RateCounter rateCount = new RateCounter(RobotMap.DIO_SHOOTER_SENSOR);
	SimplePIFController pifController;

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
		rateCount.setMaxVal(8500);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new SpinShooterPID());
	}

	public void setSpeed(double speed) {
		shooterMotor.set(speed);
	}

	public void setRPM(int rpm) {
		if (rpm < 0)
			return;

		this.rpm = rpm;
		pifController.setSetpoint(this.rpm);
	}

	public void feedPID() {
		SmartDashboard.putNumber("asdfa", pifController.getOutput());
		double out = pifController.getOutput();
		if (Math.abs(out) > 0.05)
			shooterMotor.set(out);
		else
			shooterMotor.set(0);
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
