package org.usfirst.frc.team4946.robot;

import org.usfirst.frc.team4946.robot.commands.Agitate;
import org.usfirst.frc.team4946.robot.commands.Playback;
import org.usfirst.frc.team4946.robot.commands.Record;
import org.usfirst.frc.team4946.robot.commands.LEDs.ActivateGearLED;
import org.usfirst.frc.team4946.robot.commands.LEDs.ActivateShooterLED;
import org.usfirst.frc.team4946.robot.commands.LEDs.DeactivateGearLED;
import org.usfirst.frc.team4946.robot.commands.LEDs.DeactivateShooterLED;
import org.usfirst.frc.team4946.robot.commands.gearpusher.ExtendGearPusher;
import org.usfirst.frc.team4946.robot.commands.gearpusher.RetractGearPusher;
import org.usfirst.frc.team4946.robot.commands.intake.IntakeBall;
import org.usfirst.frc.team4946.robot.commands.intake.ReverseIntake;
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

	JoystickAxisButton intakeForwardsButton = new JoystickAxisButton(operatorStick, 3);
	JoystickAxisButton intakeBackwardsButton = new JoystickAxisButton(operatorStick, 2);
	Button winchButton = new JoystickButton(operatorStick, 2);
	
	// Driver Buttons
	Button record = new JoystickButton(driveStick, 1);
	Button play = new JoystickButton(driveStick, 2);
	Button gearButton = new JoystickAxisButton(driveStick, 2);
	Button gearLEDButton = new JoystickButton(driveStick, 5);
	Button agitatorButton = new JoystickAxisButton(driveStick, 3);
	Button shootLEDButton = new JoystickButton(driveStick, 6);

	
	// Operator Buttons
	Button up10 = new JoystickButton(operatorStick, 7);
	Button down10 = new JoystickButton(operatorStick, 8);
	Button down = new JoystickButton(operatorStick, 5);
	Button up = new JoystickButton(operatorStick, 6);

	public OI() {
		intakeForwardsButton.whileHeld(new IntakeBall());
		intakeForwardsButton.setActivationPercent(0.1);
		intakeBackwardsButton.whileHeld(new ReverseIntake());
		intakeBackwardsButton.setActivationPercent(0.1);
		
		agitatorButton.whileHeld(new Agitate(0.5));
		winchButton.whileHeld(new SpinWinchAlways());
		gearButton.whenPressed(new ExtendGearPusher());
		gearButton.whenReleased(new RetractGearPusher());
		gearLEDButton.whenPressed(new ActivateGearLED());
		gearLEDButton.whenReleased(new DeactivateGearLED());
		shootLEDButton.whenPressed(new ActivateShooterLED());
		shootLEDButton.whenReleased(new DeactivateShooterLED());
		

		record.whileHeld(new Record("Shoot"));
		play.whileHeld(new Playback("Shoot"));

		up.whileHeld(new IncrementRPM(50));
		down.whileHeld(new DecrementRPM(50));
		up10.whenPressed(new IncrementRPM(10));
		down10.whenPressed(new DecrementRPM(10));
	}

	public Joystick getOperatorJoystick() {
		return operatorStick;
	}

	public Joystick getDriveJoystick() {
		return driveStick;
	}

}
