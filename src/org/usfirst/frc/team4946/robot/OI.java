package org.usfirst.frc.team4946.robot;

import org.usfirst.frc.team4946.robot.commands.Agitate;
import org.usfirst.frc.team4946.robot.commands.LEDs.ActivateGearLED;
import org.usfirst.frc.team4946.robot.commands.LEDs.DeactivateGearLED;
import org.usfirst.frc.team4946.robot.commands.flipper.ExtendFlippers;
import org.usfirst.frc.team4946.robot.commands.gearpusher.ExtendPusher;
import org.usfirst.frc.team4946.robot.commands.gearpusher.RetractPusher;
import org.usfirst.frc.team4946.robot.commands.intake.IntakeBall;
import org.usfirst.frc.team4946.robot.commands.intake.ReverseIntake;
import org.usfirst.frc.team4946.robot.commands.shooter.CoverShooter;
import org.usfirst.frc.team4946.robot.commands.shooter.DecrementRPM;
import org.usfirst.frc.team4946.robot.commands.shooter.IncrementRPM;
import org.usfirst.frc.team4946.robot.commands.shooter.OpenShooter;
import org.usfirst.frc.team4946.robot.commands.shooter.SetShooterSpeed;
import org.usfirst.frc.team4946.robot.commands.shooter.ZeroSpeed;
//import org.usfirst.frc.team4946.robot.commands.vision.TurnToBoiler;
import org.usfirst.frc.team4946.robot.commands.winch.SpinWinchFast;
import org.usfirst.frc.team4946.robot.util.JoystickAxisButton;
import org.usfirst.frc.team4946.robot.util.MultiButton;

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

	// Driver Buttons
	// Button record = new JoystickButton(driveStick, 1);
	// Button play = new JoystickButton(driveStick, 2);
	Button gearButton = new JoystickAxisButton(driveStick, 3);
	Button gearLEDButton = new JoystickButton(driveStick, 6);
	Button agitatorButton = new JoystickAxisButton(driveStick, 2);
	Button shootLEDButton = new JoystickButton(driveStick, 5);
	Button reverseButton = new MultiButton(driveStick, 1, 3, 4);
//	Button turn90 = new MultiButton(driveStick, 1);
	Button openWings = new JoystickButton(driveStick, 2);

	// Operator Buttons
	JoystickAxisButton intakeForwardsButton = new JoystickAxisButton(
			operatorStick, 3);
	JoystickAxisButton intakeBackwardsButton = new JoystickAxisButton(
			operatorStick, 2);
	Button winchButton = new JoystickButton(operatorStick, 2);
	Button disableAutoIntake = new JoystickButton(operatorStick, 1);
	// Button setShooterSpeed = new MultiButton(operatorStick, 3, 4);
	Button shooterPresetA = new JoystickButton(operatorStick, 3);
	Button shooterPresetB = new JoystickButton(operatorStick, 4);
	Button shooterPresetZero = new JoystickButton(operatorStick, 7);

	// Button up10 = new JoystickButton(operatorStick, 7);
	// Button down10 = new JoystickButton(operatorStick, 8);
	Button down = new JoystickButton(operatorStick, 5);
	Button up = new JoystickButton(operatorStick, 6);

	public OI() {
		intakeForwardsButton.whileHeld(new IntakeBall());
		intakeForwardsButton.setActivationPercent(0.1);
		intakeBackwardsButton.whileHeld(new ReverseIntake());
		intakeBackwardsButton.setActivationPercent(0.1);
		up.whenPressed(new IncrementRPM(50));
		down.whenPressed(new DecrementRPM(50));
		disableAutoIntake.whileHeld(new CoverShooter());
		disableAutoIntake.whenReleased(new OpenShooter());

		
//		turn90.whileHeld(new TurnPID(90));
		// up.whenPressed(new Up5Percent());
		// down.whenPressed(new Down5Percent());
		shooterPresetZero.whenPressed(new ZeroSpeed());

		// setShooterSpeed.whileHeld(new SetShooterSpeedVision());
		shooterPresetA.whenPressed(new SetShooterSpeed(3400));
		shooterPresetB.whenPressed(new SetShooterSpeed(3500));
		openWings.whenPressed(new ExtendFlippers());

		agitatorButton.whileHeld(new Agitate(0.4));
		winchButton.whileHeld(new SpinWinchFast());
		gearButton.whenPressed(new RetractPusher());
		gearButton.whenReleased(new ExtendPusher());

		// gearLEDButton.whileHeld(new TurnToGear());
		gearLEDButton.whenPressed(new ActivateGearLED());
		gearLEDButton.whenReleased(new DeactivateGearLED());
		//shootLEDButton.whileHeld(new TurnToBoiler());
		reverseButton.whileHeld(new Agitate(-0.4));

	}

	public Joystick getOperatorJoystick() {
		return operatorStick;
	}

	public Joystick getDriveJoystick() {
		return driveStick;
	}
	
	public boolean getIntakeDisabledButton(){
		return disableAutoIntake.get();
	}

}
