package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotConstants;
import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.SimplePIController;
import org.usfirst.frc.team4946.robot.commands.driveRobot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	RobotDrive m_driveTrain = new RobotDrive(RobotMap.PWM_DRIVETRAIN_BLMOTOR, 
			RobotMap.PWM_DRIVETRAIN_BRMOTOR, RobotMap.PWM_DRIVETRAIN_FLMOTOR, 
			RobotMap.PWM_DRIVETRAIN_FRMOTOR); 
	
	VictorSP m_driveTrainFL = new VictorSP(RobotMap.PWM_DRIVETRAIN_FLMOTOR);
	VictorSP m_driveTrainFR = new VictorSP(RobotMap.PWM_DRIVETRAIN_FRMOTOR);
	
	
	Encoder m_driveEncoderLeft = new Encoder(RobotMap.DIO_DRIVETRAIN_LEFTENCA, RobotMap.DIO_DRIVETRAIN_LEFTENCB);
	Encoder m_driveEncoderRight = new Encoder(RobotMap.DIO_DRIVETRAIN_RIGHTENCA, RobotMap.DIO_DRIVETRAIN_RIGHTENCB);
	
	
	ADXRS450_Gyro m_driveGyro = new ADXRS450_Gyro();
	PIDController m_PIDEncoderLeft;
	PIDController m_PIDEncoderRight;
	SimplePIController m_gyroPID;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new driveRobot());
    }
    
    public DriveTrain(){
		m_driveEncoderRight.setDistancePerPulse(RobotConstants.wheelDia*Math.PI / RobotConstants.encoderPPR*(50/24));
		m_driveEncoderLeft.setDistancePerPulse(RobotConstants.wheelDia*Math.PI / RobotConstants.encoderPPR*(50/24));
		m_driveEncoderRight.setReverseDirection(true);
		m_driveEncoderLeft.setReverseDirection(true);
		m_driveEncoderRight.reset();
		m_driveEncoderLeft.reset();
		m_driveGyro.calibrate();
		
		m_PIDEncoderLeft = new PIDController(0.01, 0.001, 0.0, m_driveEncoderLeft, m_driveTrainFL);
		m_PIDEncoderRight = new PIDController(0.01, 0.001, 0.0, m_driveEncoderRight, m_driveTrainFR);
		m_PIDEncoderLeft.setContinuous(false);
		m_PIDEncoderRight.setContinuous(false);
		m_gyroPID = new SimplePIController(0.1, 0.01, m_driveGyro);
		m_gyroPID.setContinuous(true);
		m_gyroPID.setInputRange(0, 359);

    }
    
    public void drive(double drive, double curve){
    	drive(drive, curve, 1.0);
    }

    
    public void drive(double drive, double curve, double throttle){
    	
		throttle = (throttle - 1.0) / -2.0;

		drive *= (0.5 + (0.5 * throttle));
		curve *= (0.5 + (0.5 * throttle));
    	
    	m_driveTrain.arcadeDrive(drive, curve);
    	
    	SmartDashboard.putNumber("gyro", m_driveGyro.getAngle());
		SmartDashboard.putNumber("Dist", this.getEncoderDistance());
    }
    
    public double getEncoderDistance(){
    	
    	double leftDistance = m_driveEncoderLeft.getDistance();
    	double rightDistance = m_driveEncoderRight.getDistance();
    	
    	double totalDistance = (leftDistance + rightDistance)/2.0;
    	return totalDistance;
    			
    }
    
    public double getGyroValue(){
    	
    	double robotAngle = m_driveGyro.getAngle();
    	return robotAngle;
    }
    
    public void resetEncoders(){
    	m_driveEncoderRight.reset();
    	m_driveEncoderLeft.reset();
    }
    
    public void calibrateGyroscope(){
    	m_driveGyro.calibrate();
    }
    
    public void enableEncoders(){
    	//Allows the Encoders to start computing w/ PID; the Gyro, because it's a SimplePIController,
    	//Probably already has this property
    	m_PIDEncoderLeft.enable();
    	m_PIDEncoderRight.enable();
    }
    
    public void setGyroSetpoint(double newSetpoint){
    	//PID; Setpoint is in degrees
    	m_gyroPID.setSetpoint(newSetpoint);
    }
    
    public double getGyroOutput(){
    	//PID
    	return m_gyroPID.getOutput();
    }
    
    public void setEncoderSetpoint(double newSetpoint){
    	//PID; Setpoint is in inches 
    	m_PIDEncoderLeft.setSetpoint(newSetpoint);
    	m_PIDEncoderRight.setSetpoint(newSetpoint);
    }
    
    public double getEncoderOutput(){
    	//PID
    	return ((m_PIDEncoderLeft.get()+ m_PIDEncoderRight.get())/2);
    }
}

