package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotConstants;
import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.driveRobot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
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
	
	Encoder m_driveEncoderLeft = new Encoder(RobotMap.DIO_DRIVETRAIN_LEFTENCA, RobotMap.DIO_DRIVETRAIN_LEFTENCB);
	Encoder m_driveEncoderRight = new Encoder(RobotMap.DIO_DRIVETRAIN_RIGHTENCA, RobotMap.DIO_DRIVETRAIN_RIGHTENCB);
	
	
	Gyro m_driveGyro = new ADXRS450_Gyro();
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new driveRobot());
    }
    
    public DriveTrain(){
		m_driveEncoderRight.setDistancePerPulse(RobotConstants.wheelDia*Math.PI / RobotConstants.encoderPPR);
		m_driveEncoderLeft.setDistancePerPulse(RobotConstants.wheelDia*Math.PI / RobotConstants.encoderPPR);
		m_driveEncoderRight.setReverseDirection(true);
		m_driveEncoderLeft.setReverseDirection(true);
		m_driveEncoderRight.reset();
		m_driveEncoderLeft.reset();
		m_driveGyro.calibrate();
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
    
    public double getGyroValues(){
    	
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
    
    public void setGyroValue(){
    
    }
    
}

