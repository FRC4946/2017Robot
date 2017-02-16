package org.usfirst.frc.team4946.robot.subsystems;

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

	CANTalon shooterMotor = new CANTalon(RobotMap.CAN_TALON_SHOOTER);
	RateCounter rateCount = new RateCounter(RobotMap.DIO_SHOOTER_SENSOR);
	SimplePIFController pifController;

	int rpm = 0;
	public double percentSpeed = 0;


	double kP = 0;
	double kI = 0;
	double kF = 0.00010316;
	double kFOff = 0.18;

	public ShooterMotor() {

		pifController = new SimplePIFController(kP, kI, kF, kFOff, rateCount);
		pifController.setInputRange(0, 10000);

		// shooterMotor.changeControlMode(TalonControlMode.Speed); // Change
		// control mode
		// of talon,
		// default is
		// PercentVbus
		// (-1.0 to 1.0)
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new SpinShooterPercent());
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
	
	public void feedPID(){
		shooterMotor.set(pifController.getOutput());
	}

	public double getRPM() {
		// return rateCount.getRPM();
		return rateCount.pidGet();
	}

	public int getSetRPM() {
		return rpm;
	}

	public void plusFivePercent() {
		percentSpeed += 0.05;
		if(percentSpeed > 1.0)
			percentSpeed = 1.0;
	}

	public void minusFivePercent() {
		percentSpeed -= 0.05;
		if(percentSpeed < -1.0)
			percentSpeed = -1.0;
	}
}
