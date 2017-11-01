package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterCover extends Subsystem {

	Solenoid m_shooterHood = new Solenoid(RobotMap.PCM_SHOOTER_HOOD);

	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new OpenShooter());
	}

	public void setShooterIsCovered(boolean isCovered) {
		m_shooterHood.set(!isCovered);
	}

}
