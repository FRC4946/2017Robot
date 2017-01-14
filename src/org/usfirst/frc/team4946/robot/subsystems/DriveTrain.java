package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

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
	
	Gyro m_driveScope = new AnalogGyro(RobotMap.ANALOG_DRIVETRAIN_GYRO);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}

