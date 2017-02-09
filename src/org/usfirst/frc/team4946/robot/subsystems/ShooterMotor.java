package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.SpinShooter;
import org.usfirst.frc.team4946.robot.util.RateCounter;
import org.usfirst.frc.team4946.robot.util.SimplePIFController;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterMotor extends Subsystem {

	VictorSP shooterMotor = new VictorSP(RobotMap.CAN_TALON_SHOOTER);

	RateCounter rateCount = new RateCounter(RobotMap.DIO_SHOOTER_SENSOR);
	SimplePIFController pifController;
	int rpm = 0;

	double kP = 0;
	double kI = 0;
	double kF = 0.00010316;
	double kFOff = 0.18;

	public void addRPM() {
		rpm += 50;
		shooterMotor.set(rpm);
	}

	public void subtractRPM() {
		rpm -= 50;
		shooterMotor.set(rpm);
	}

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
		setDefaultCommand(new SpinShooter());
	}

	public void setSpeed(double speed) {
		shooterMotor.set(speed);
	}

	public void setRPM(int rpm) {
		this.rpm = rpm;
		pifController.setSetpoint(this.rpm);
		shooterMotor.set(pifController.getOutput());
	}

	public double getRPM() {
		return rateCount.getRPM();
	}

	public int getSetRPM() {
		return rpm;
	}

}
