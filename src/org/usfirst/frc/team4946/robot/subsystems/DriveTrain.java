package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.AvgPIDSource;
import org.usfirst.frc.team4946.robot.RobotConstants;
import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.SimplePIController;
import org.usfirst.frc.team4946.robot.commands.driveTrain.DriveRobot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	RobotDrive m_driveTrain = new RobotDrive(RobotMap.PWM_DRIVETRAIN_FLMOTOR,
			RobotMap.PWM_DRIVETRAIN_BLMOTOR, RobotMap.PWM_DRIVETRAIN_FRMOTOR,
			RobotMap.PWM_DRIVETRAIN_BRMOTOR);

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
		m_driveEncoderRight.setReverseDirection(true);
		m_driveEncoderLeft.setReverseDirection(true);
		m_driveEncoderRight.reset();
		m_driveEncoderLeft.reset();
		m_driveGyro.calibrate();

		m_drivePID = new SimplePIController(0.01, 0.001, new AvgPIDSource(
				m_driveEncoderLeft, m_driveEncoderLeft)); // TODO: CHANGE THIS TO LEFT AND RIGHT
		m_drivePID.setContinuous(false);
		m_gyroPID = new SimplePIController(0.1, 0.01, m_driveGyro);
		m_gyroPID.setContinuous(true);
		m_gyroPID.setInputRange(0, 359);
	}

	public void drive(double drive, double curve) {
		drive(drive, curve, 1.0);
	}

	public void drive(double drive, double curve, double throttle) {

		throttle = (throttle - 1.0) / -2.0;

		drive *= (0.5 + (0.5 * throttle));
		curve *= (0.5 + (0.5 * throttle));

		m_driveTrain.arcadeDrive(drive, curve);

		SmartDashboard.putNumber("gyro", m_driveGyro.getAngle());
		SmartDashboard.putNumber("Dist", getEncoderDistance());
	}

	public double getEncoderDistance() {

		double leftDistance = m_driveEncoderLeft.getDistance();
		double rightDistance = m_driveEncoderRight.getDistance();

		double totalDistance = (leftDistance + rightDistance) / 2.0;
		return getOneEncoderValue();

	}

	public double getGyroValue() {

		double robotAngle = m_driveGyro.getAngle();
		return robotAngle;
	}

	public double getOneEncoderValue() {
		return m_driveEncoderLeft.getDistance();
	}

	public void resetEncoders() {
		m_driveEncoderRight.reset();
		m_driveEncoderLeft.reset();
	}

	public void calibrateGyroscope() {
		m_driveGyro.calibrate();
	}

	public void enableEncoders() {
		// Allows the Encoders to start computing w/ PID; the Gyro, because it's
		// a SimplePIController,
		// Probably already has this property
		// m_PIDEncoderLeft.enable();
		// m_PIDEncoderRight.enable();
	}

	public void setGyroSetpoint(double newSetpoint) {
		// PID; Setpoint is in degrees
		m_gyroPID.setSetpoint(newSetpoint);
	}

	public double getGyroOutput() {
		// PID
		return m_gyroPID.getOutput();
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
}
