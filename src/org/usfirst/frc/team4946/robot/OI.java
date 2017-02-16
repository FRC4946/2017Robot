package org.usfirst.frc.team4946.robot;


import org.usfirst.frc.team4946.robot.commands.Agitate;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;
import org.usfirst.frc.team4946.robot.commands.gearpusher.PushGear;
import org.usfirst.frc.team4946.robot.commands.gearpusher.ToggleDoor;
import org.usfirst.frc.team4946.robot.commands.indexer.IndexerCommand;
import org.usfirst.frc.team4946.robot.commands.intake.IntakeBall;
import org.usfirst.frc.team4946.robot.commands.shooter.DecrementRPM;
import org.usfirst.frc.team4946.robot.commands.shooter.IncrementRPM;
import org.usfirst.frc.team4946.robot.commands.winch.SpinWinchAlways;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick operatorStick = new Joystick(0);
	private Joystick driveStick = new Joystick(1);

	Button intakeButton = new JoystickButton(operatorStick, 1);
	Button winchButton = new JoystickButton(operatorStick, 2);
	Button spinIndexer = new JoystickButton(operatorStick, 3);
//	Button gearDoorButton = new JoystickButton(operatorStick, 4);
//	Button gearPusherButton = new JoystickButton(operatorStick, 5);
	Button agitatorButton = new JoystickButton(operatorStick, 4);
	
	Button down = new JoystickButton(operatorStick, 5);
	Button up = new JoystickButton(operatorStick, 6);


	Button maintain0deg = new JoystickButton(driveStick, 1);
	Button drive48in = new JoystickButton(driveStick, 4);
	Button turn0Button = new JoystickButton(driveStick, 6);
	Button turn90Button = new JoystickButton(driveStick, 7);

	public OI() {
		intakeButton.whileHeld(new IntakeBall());
		agitatorButton.whileHeld(new Agitate());
		spinIndexer.whileHeld(new IndexerCommand());
		winchButton.whileHeld(new SpinWinchAlways());
//		gearDoorButton.whenPressed(new ToggleDoor());
//		gearPusherButton.whileHeld(new PushGear());
//		drive48in.whenPressed(new AutoDriveDistancePID(48, 0.6));
		turn0Button.whenPressed(new TurnPID(0.0));
		turn90Button.whenPressed(new TurnPID(90.0));
		
		up.whileHeld(new IncrementRPM());
		down.whileHeld(new DecrementRPM());
	}

	public Joystick getOperatorJoystick() {
		return operatorStick;
	}

	public Joystick getDriveJoystick() {
		return driveStick;
	}

}
