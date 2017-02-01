package org.usfirst.frc.team4946.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearDropper extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Solenoid m_doorOpener1 = new Solenoid(0); // <- change later
	Solenoid m_doorOpener2 = new Solenoid(1); // change later 
	Solenoid m_gearPusher = new Solenoid(2); //change later
	
	public void doorOpener(){

		boolean onOrNot = m_doorOpener1.get();
		m_doorOpener1.set(!onOrNot);
		m_doorOpener2.set(!onOrNot);
		
	}
	
	public void pushGear(){
		
		m_gearPusher.set(true);
		
	}
	
	public void stopPushingGear(){
		m_gearPusher.set(false);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

