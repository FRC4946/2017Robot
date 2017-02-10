package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	VictorSP winchTalon=new VictorSP(8);
	VictorSP winchTalon1=new VictorSP(9);
	DigitalInput winchSwitch=new DigitalInput(RobotMap.DIO_LIMIT_SWITCH_WINCH);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand())
    }
    
    public void setSpeed(double speed){
    	winchTalon.set(speed);
    	winchTalon1.set(speed);
    }
    
    public boolean getWinchSwitch(){
    	return winchSwitch.get();
    }
    
    
    
    
}

