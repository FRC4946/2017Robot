package org.usfirst.frc.team4946.robot;

import java.io.PushbackInputStream;

import org.usfirst.frc.team4946.robot.commands.Agitate;
import org.usfirst.frc.team4946.robot.commands.Minus5Percent;
import org.usfirst.frc.team4946.robot.commands.Plus5Percent;
import org.usfirst.frc.team4946.robot.commands.LEDs.ActivateGearLED;
import org.usfirst.frc.team4946.robot.commands.LEDs.ActivateShooterLED;
import org.usfirst.frc.team4946.robot.commands.LEDs.DeactivateGearLED;
import org.usfirst.frc.team4946.robot.commands.LEDs.DeactivateShooterLED;
import org.usfirst.frc.team4946.robot.commands.driveTrain.TurnPID;
import org.usfirst.frc.team4946.robot.commands.gearpusher.PushGear;
import org.usfirst.frc.team4946.robot.commands.indexer.IndexerCommand;
import org.usfirst.frc.team4946.robot.commands.intake.IntakeBall;
import org.usfirst.frc.team4946.robot.commands.shooter.DecrementRPM;
import org.usfirst.frc.team4946.robot.commands.shooter.IncrementRPM;
import org.usfirst.frc.team4946.robot.commands.winch.SpinWinchAlways;
import org.usfirst.frc.team4946.robot.util.JoystickAxisButton;

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
	
	// Driver Buttons
	Button gearButton = new JoystickAxisButton(driveStick, 2);
	Button gearLEDButton = new JoystickButton(driveStick, 5);
	Button agitatorButton = new JoystickAxisButton(driveStick, 3);
	Button shootLEDButton = new JoystickButton(driveStick, 6);

	
	// Operator Buttons
	Button plus5PercentButton = new JoystickButton(operatorStick, 7);
	Button minus5PercentButton = new JoystickButton(operatorStick, 8);
	Button down = new JoystickButton(operatorStick, 5);
	Button up = new JoystickButton(operatorStick, 6);

	public OI() {
		intakeButton.whileHeld(new IntakeBall());
		agitatorButton.whileHeld(new Agitate());
		winchButton.whileHeld(new SpinWinchAlways());
		gearButton.whileHeld(new PushGear());
		gearLEDButton.whenPressed(new ActivateGearLED());
		gearLEDButton.whenReleased(new DeactivateGearLED());
		shootLEDButton.whenPressed(new ActivateShooterLED());
		shootLEDButton.whenReleased(new DeactivateShooterLED());


		up.whileHeld(new IncrementRPM());
		down.whileHeld(new DecrementRPM());
		plus5PercentButton.whenPressed(new Plus5Percent());
		minus5PercentButton.whenPressed(new Minus5Percent());
	}

	public Joystick getOperatorJoystick() {
		return operatorStick;
	}

	public Joystick getDriveJoystick() {
		return driveStick;
	}

}
