package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon winchTalon=new CANTalon(RobotMap.CAN_TALON_WINCH);
	DigitalInput winchSwitch=new DigitalInput(RobotMap.DIO_LIMIT_SWITCH_WINCH);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand())
    }
    
    public void setSpeed(double speed){
    	winchTalon.set(speed);
    }
    
    public boolean getWinchSwitch(){
    	return winchSwitch.get();
    }
    
    
    
    
}

