package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotConstants;
import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.driveTrain.DriveRobot;
import org.usfirst.frc.team4946.robot.util.AvgPIDSource;
import org.usfirst.frc.team4946.robot.util.SimplePIController;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	RobotDrive m_driveTrain = new RobotDrive(new CANTalon(RobotMap.CAN_DRIVETRAIN_FLMOTOR),
			new CANTalon(RobotMap.CAN_DRIVETRAIN_BLMOTOR), new CANTalon(RobotMap.CAN_DRIVETRAIN_FRMOTOR),
					new CANTalon(RobotMap.CAN_DRIVETRAIN_BRMOTOR));

	Encoder m_driveEncoderLeft = new Encoder(RobotMap.DIO_DRIVETRAIN_LEFTENCA,
			RobotMap.DIO_DRIVETRAIN_LEFTENCB);
	Encoder m_driveEncoderRight = new Encoder(
			RobotMap.DIO_DRIVETRAIN_RIGHTENCA,
			RobotMap.DIO_DRIVETRAIN_RIGHTENCB);

	ADXRS450_Gyro m_driveGyro = new ADXRS450_Gyro();
	SimplePIController m_drivePID;
	SimplePIController m_gyroPID;
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveRobot());
	}

	public DriveTrain() {
		m_driveEncoderRight
				.setDistancePerPulse(RobotConstants.ENCODER_DISTANCE_PER_PULSE);
		m_driveEncoderLeft
				.setDistancePerPulse(RobotConstants.ENCODER_DISTANCE_PER_PULSE);
		m_driveEncoderRight.setReverseDirection(false);
		m_driveEncoderLeft.setReverseDirection(true);
		m_driveEncoderRight.reset();
		m_driveEncoderLeft.reset();
		m_driveGyro.calibrate();

		m_drivePID = new SimplePIController(RobotConstants.driveP, RobotConstants.driveI, new AvgPIDSource(
				m_driveEncoderLeft, m_driveEncoderRight)); 
		m_drivePID.setContinuous(false);
		m_drivePID.setDirection(false);
		m_drivePID.setTolerence(2);
		m_drivePID.setOutputRange(-RobotConstants.driveOutput, RobotConstants.driveOutput);
		
		m_gyroPID = new SimplePIController(RobotConstants.turnP, RobotConstants.turnI, m_driveGyro);
		m_gyroPID.setContinuous(true);
		m_gyroPID.setDirection(false);
		m_gyroPID.setInputRange(0, 360);
		m_gyroPID.setTolerence(3.0);
		m_gyroPID.setOutputRange(-RobotConstants.turnOutput, RobotConstants.turnOutput);
	}

	
	public void setSafety(boolean isSafe){
		m_driveTrain.setSafetyEnabled(isSafe);
	}
	
	
	public void drive(double drive, double curve) {
		drive(drive, curve, 1.0);
	}
	

	public void drive(double drive, double curve, double throttle) {

		drive *= (0.5 + (0.5 * throttle));
		curve *= (0.5 + (0.5 * throttle));

		m_driveTrain.arcadeDrive(drive, curve, true);

	}

	public double getEncoderDistance() {

		double leftDistance = m_driveEncoderLeft.getDistance();
		double rightDistance = m_driveEncoderRight.getDistance();

		double totalDistance = (leftDistance + rightDistance) / 2.0;
		return totalDistance;
	}

	public double getGyroValue() {

		double robotAngle = m_driveGyro.getAngle();
		return robotAngle;
	}

	public double getGyroPIDInput() {
		return m_gyroPID.getInputValue();
	}
	
	public double getOneEncoderValue() {
		return m_driveEncoderLeft.getDistance();
	}

	public void resetEncoders() {
		m_driveEncoderRight.reset();
		m_driveEncoderLeft.reset();
	}
	
	public void resetGyro() {
		m_driveGyro.reset();
	}

	public void calibrateGyroscope() {
		m_driveGyro.calibrate();
	}
	
	public void setDrivePIDMultiplier(double speed) {
		speed = Math.abs(speed);
		m_drivePID.setOutputRange(-speed, speed);
	}

	public void setGyroSetpoint(double newSetpoint) {
		// PID; Setpoint is in degrees
		m_gyroPID.setSetpoint(newSetpoint);
	}

	public double getGyroOutput() {
		// PID
		return m_gyroPID.getOutput();
	}
	
	public double getGyroSetpoint() {
		// PID
		return m_gyroPID.getSetpoint();
	}

	public void setDrivePIDSetpoint(double newSetpoint) {
		// PID; Setpoint is in inches
		m_drivePID.setSetpoint(newSetpoint);
	}

	public double getDrivePIDOutput() {
		return m_drivePID.getOutput();
	}

	public boolean getDrivePIDIsOnTarget() {
		return m_drivePID.onTarget();
	}
	
	public boolean getGyroPIDIsOnTarget() {
		return m_gyroPID.onTarget();
	}
}
