package org.usfirst.frc.team4946.robot;

import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;
import org.usfirst.frc.team4946.robot.commands.gearpusher.PushGear;
import org.usfirst.frc.team4946.robot.commands.gearpusher.ToggleDoor;
import org.usfirst.frc.team4946.robot.commands.indexer.IndexerCommand;
import org.usfirst.frc.team4946.robot.commands.indexer.IntakeForward;
import org.usfirst.frc.team4946.robot.commands.winch.SpinWinchUntilSwitch;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// // joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	private Joystick operatorStick = new Joystick(0);
	private Joystick driveStick = new Joystick(1);

	Button spinButton = new JoystickButton(operatorStick, 1);
	Button winchButton = new JoystickButton(operatorStick, 2);
	Button spinIndexer = new JoystickButton(operatorStick, 3);
	Button gearDoorButton = new JoystickButton(operatorStick, 4);
	Button gearPusherButton = new JoystickButton(operatorStick, 5);
	// Button addRpmTest = new JoystickButton(operatorStick, 5);
	// Button subtractRpmTest = new JoystickButton(operatorStick, 6);
	// Button spinButton = new JoystickButton(operatorStick, 5);
	
	Button maintain0deg = new JoystickButton(driveStick, 1);
	Button drive48in = new JoystickButton(driveStick, 4);
	Button turn0Button = new JoystickButton(driveStick, 6);
	Button turn90Button = new JoystickButton(driveStick, 7);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public OI() {
		spinButton.whileHeld(new IntakeForward());
		spinIndexer.whileHeld(new IndexerCommand());
		winchButton.whileHeld(new SpinWinchUntilSwitch());
		gearDoorButton.whenPressed(new ToggleDoor());
		gearPusherButton.whileHeld(new PushGear());
		// spinButton.whileHeld(new IntakeForward());
		drive48in.whenPressed(new AutoDriveDistancePID(48, 0.6));
		turn0Button.whenPressed(new TurnPID(0.0));
		turn90Button.whenPressed(new TurnPID(90.0));
	}

	public Joystick getOperatorJoystick() {
		return operatorStick;
	}

	public Joystick getDriveJoystick() {
		return driveStick;
	}

}
