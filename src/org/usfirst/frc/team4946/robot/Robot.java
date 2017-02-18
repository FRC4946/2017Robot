package org.usfirst.frc.team4946.robot;

import org.usfirst.frc.team4946.robot.RobotConstants.Auto.AutoOptions;
import org.usfirst.frc.team4946.robot.RobotConstants.Auto.AutoScript;
import org.usfirst.frc.team4946.robot.commands.autonomous.AutonomousWrapperGearFirst;
import org.usfirst.frc.team4946.robot.commands.autonomous.AutonomousWrapperShootFirst;
import org.usfirst.frc.team4946.robot.subsystems.Agitator;
import org.usfirst.frc.team4946.robot.subsystems.BallIntake;
import org.usfirst.frc.team4946.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4946.robot.subsystems.GearDropper;
import org.usfirst.frc.team4946.robot.subsystems.Indexer;
import org.usfirst.frc.team4946.robot.subsystems.LEDlights;
import org.usfirst.frc.team4946.robot.subsystems.ShooterHood;
import org.usfirst.frc.team4946.robot.subsystems.ShooterMotor;
import org.usfirst.frc.team4946.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Agitator agitatorSubsystem;
	public static LEDlights LEDlightsSubsystem;
	public static Winch winchSubsystem;
	public static ShooterMotor shooterSubsystem;
	public static GearDropper gearSubsystem;
	public static BallIntake ballSubsystem;
	public static ShooterHood shooterHoodSubsystem;
	public static DriveTrain driveSubsystem;
	public static Indexer indexerSubsystem;
	public static OI oi;

	Command auto;
	SendableChooser<AutoOptions> m_autoOptions;
	SendableChooser<AutoScript> m_autoScript;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		shooterHoodSubsystem = new ShooterHood();
		winchSubsystem = new Winch();
		shooterSubsystem = new ShooterMotor();
		gearSubsystem = new GearDropper();
		ballSubsystem = new BallIntake();
		driveSubsystem = new DriveTrain();
		indexerSubsystem = new Indexer();
		agitatorSubsystem = new Agitator();
		LEDlightsSubsystem = new LEDlights();

		oi = new OI();

		driveSubsystem.calibrateGyroscope();

		m_autoOptions = new SendableChooser<AutoOptions>();
		m_autoOptions.addDefault("Left", AutoOptions.kLeftPos);
		m_autoOptions.addObject("Right", AutoOptions.kRightPos);
		m_autoOptions.addObject("Middle - Breach & Shoot **GEAR FIRST MODE ONLY**",
				AutoOptions.kMiddleBreachAndShoot);
		m_autoOptions.addObject("Middle - Breach Left",
				AutoOptions.kMiddleBreachLeft);
		m_autoOptions.addObject("Middle - Breach Right",
				AutoOptions.kMiddleBreachRight);
		m_autoOptions.addObject("Middle - No Breach",
				AutoOptions.kMiddleNoBreachNoShoot);
		SmartDashboard.putData("Autonomous Options", m_autoOptions);

		m_autoScript = new SendableChooser<AutoScript>();
		m_autoScript.addDefault("Gear First **More Options", AutoScript.GEAR_FIRST);
		m_autoScript.addObject("Shoot First **More Options", AutoScript.SHOOT_FIRST);
		m_autoScript.addObject("Just Breach", AutoScript.BREACH);
		m_autoScript.addObject("Hopper Left", AutoScript.HOPPER_LEFT);
		m_autoScript.addObject("Hopper Right", AutoScript.HOPPER_RIGHT);
		SmartDashboard.putData("Auto Script", m_autoScript);
		CameraServer.getInstance().startAutomaticCapture();

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		shooterSubsystem.setRPM(0);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		
		
		
		boolean isRed = DriverStation.getInstance().getAlliance() == Alliance.Red;
		AutoScript script = m_autoScript.getSelected();
		AutoOptions options = m_autoOptions.getSelected();

		switch (script) {
		case GEAR_FIRST:
			auto = new AutonomousWrapperGearFirst(0, isRed);
			break;
		case SHOOT_FIRST:
			auto = new AutonomousWrapperShootFirst(0, isRed);
			break;
		case HOPPER_LEFT:
			auto = new AutonomousWrapperShootFirst(0, isRed);
			break;
		case HOPPER_RIGHT:
			auto = new AutonomousWrapperShootFirst(0, isRed);
			break;
		case BREACH:
			auto = new AutonomousWrapperShootFirst(0, isRed);
			break;
		default:
			auto = null;
		}
		// auto = new AutonomousWrapperTurningFromBack(autoMode, isRed);

		if (auto != null)
			auto.start();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("Encoder Distance: ",
				driveSubsystem.getEncoderDistance());
		SmartDashboard.putNumber("Gyro: ", driveSubsystem.getGyroValue());
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

		driveSubsystem.resetEncoders();
		driveSubsystem.resetGyro();

		if (auto != null)
			auto.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("%", shooterSubsystem.percentSpeed);

		SmartDashboard.putNumber("RPM", shooterSubsystem.getRPM());
		SmartDashboard.putNumber("Set", shooterSubsystem.getSetRPM());
		SmartDashboard.putNumber("Encoder Distance: ",
				driveSubsystem.getEncoderDistance());
		SmartDashboard.putNumber("Gyro: ", driveSubsystem.getGyroValue());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
