package org.usfirst.frc.team4946.robot;

import org.usfirst.frc.team4946.robot.RobotConstants.Auto.AutoOptions;
import org.usfirst.frc.team4946.robot.RobotConstants.Auto.AutoScript;
import org.usfirst.frc.team4946.robot.commands.autonomous.AutonomousWrapperGearFirst;
import org.usfirst.frc.team4946.robot.commands.autonomous.AutonomousWrapperHopper;
import org.usfirst.frc.team4946.robot.commands.autonomous.AutonomousWrapperShootFirst;
import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.subsystems.Agitator;
import org.usfirst.frc.team4946.robot.subsystems.BallIntake;
import org.usfirst.frc.team4946.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4946.robot.subsystems.Flippers;
import org.usfirst.frc.team4946.robot.subsystems.GearDropper;
import org.usfirst.frc.team4946.robot.subsystems.LEDlights;
import org.usfirst.frc.team4946.robot.subsystems.PadPusher;
import org.usfirst.frc.team4946.robot.subsystems.ShooterCover;
import org.usfirst.frc.team4946.robot.subsystems.ShooterMotor;
import org.usfirst.frc.team4946.robot.subsystems.Vision;
import org.usfirst.frc.team4946.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
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

	public static PadPusher padPusherSubsystem;
	public static Flippers flipperSubsystem;
	public static Agitator agitatorSubsystem;
	public static LEDlights LEDlightsSubsystem;
	public static Winch winchSubsystem;
	public static ShooterMotor shooterSubsystem;
	public static GearDropper gearSubsystem;
	public static BallIntake ballSubsystem;
	public static ShooterCover shooterCoverSubsystem;
	public static DriveTrain driveSubsystem;
	public static Vision visionSubsystem;
	public static OI oi;

	public static Command auto;
	SendableChooser<AutoOptions> m_autoOptions;
	SendableChooser<AutoScript> m_autoScript;

	Preferences prefs;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		prefs = Preferences.getInstance();
		RobotConstants.loadPrefs(prefs);
		RobotConstants.repopulatePrefs(prefs);		
		
		flipperSubsystem = new Flippers();
		shooterCoverSubsystem = new ShooterCover();
		winchSubsystem = new Winch();
		shooterSubsystem = new ShooterMotor();
		gearSubsystem = new GearDropper();
		ballSubsystem = new BallIntake();
		driveSubsystem = new DriveTrain();
		agitatorSubsystem = new Agitator();
		LEDlightsSubsystem = new LEDlights();
		visionSubsystem = new Vision();

		oi = new OI();

		driveSubsystem.calibrateGyroscope();

		m_autoOptions = new SendableChooser<AutoOptions>();
		m_autoOptions.addDefault("Middle & No Shoot",
				AutoOptions.MIDDLE_NO_SHOOT);
		m_autoOptions.addObject("Middle & Shoot", AutoOptions.MIDDLE_SHOOT);
		m_autoOptions.addObject("BoilerSide & Shoot",
				AutoOptions.BOILER_SIDE_SHOOT);
		m_autoOptions.addObject("BoilerSide & No Shoot",
				AutoOptions.BOILER_SIDE_NO_SHOOT);
		m_autoOptions.addObject("NotBoilerSide & No Shoot",
				AutoOptions.FEEDER_SIDE_NO_SHOOT);
		SmartDashboard.putData("Auto Options", m_autoOptions);

		m_autoScript = new SendableChooser<AutoScript>();
		m_autoScript.addDefault("Just Breach", AutoScript.BREACH);
		m_autoScript.addObject("Gear First **More Options",
				AutoScript.GEAR_FIRST);
		m_autoScript.addObject("Shoot First", AutoScript.SHOOT_FIRST);
		m_autoScript.addObject("Hopper", AutoScript.HOPPER);
		SmartDashboard.putData("Auto Script", m_autoScript);

		visionSubsystem.startSimpleVision();
		visionSubsystem.startZMQThread();

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		shooterSubsystem.setBrakeMode(false);

		shooterSubsystem.setSpeed(0);
		shooterSubsystem.setRPM(0);

		// gearSubsystem.setGearIsExtended(false);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		shooterSubsystem.setBrakeMode(false);
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
		gearSubsystem.setGearIsExtended(false);

		shooterCoverSubsystem.setShooterIsCovered(false);
		shooterSubsystem.setBrakeMode(true);

		driveSubsystem.setSafety(false);

		boolean isRed = DriverStation.getInstance().getAlliance() == Alliance.Red;
		AutoScript script = m_autoScript.getSelected();
		AutoOptions options = m_autoOptions.getSelected();

		switch (script) {
		case GEAR_FIRST:
			auto = new AutonomousWrapperGearFirst(options, isRed);
			break;
		case SHOOT_FIRST:
			auto = new AutonomousWrapperShootFirst(options, isRed);
			break;
		case HOPPER:
			auto = new AutonomousWrapperHopper(true, isRed);
			break;
		case BREACH:
			auto = new AutoDriveDistancePID(10 * 12);
			break;
		default:
			auto = null;
		}

		if (auto != null)
			auto.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		updateSmartDashboard();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

		driveSubsystem.setSafety(true);

		driveSubsystem.resetEncoders();
		driveSubsystem.resetGyro();

		if (auto != null)
			auto.cancel();

		RobotConstants.loadPrefs(prefs);
		RobotConstants.repopulatePrefs(prefs);
		shooterSubsystem.updatePID();
		shooterSubsystem.setBrakeMode(true);

		shooterCoverSubsystem.setShooterIsCovered(false);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	public void updateSmartDashboard() {
		SmartDashboard.putBoolean("Jetson Connected",
				Robot.visionSubsystem.jetsonIsConnected());
		SmartDashboard.putBoolean("Jetson Has Value",
				Robot.visionSubsystem.jetsonHasFreshValidValue());

		SmartDashboard.putNumber("%", shooterSubsystem.percentSpeed);

		double rpm = shooterSubsystem.getRPM();
		double rpm2 = rpm;

		SmartDashboard.putNumber("RPM2", rpm);
		SmartDashboard.putNumber("Set RPM", shooterSubsystem.getSetRPM());
		SmartDashboard.putNumber("RPM", rpm2);
		SmartDashboard.putNumber("Encoder Distance",
				driveSubsystem.getEncoderDistance());

		SmartDashboard.putNumber("Gyro", driveSubsystem.getGyroValue());
		SmartDashboard.putNumber("Gyro Setpoint",
				Robot.driveSubsystem.getGyroSetpoint());

		SmartDashboard
				.putNumber("Dist", visionSubsystem.getShooterDistInches());
		double angle = -visionSubsystem.getShooterAngle();
		SmartDashboard.putNumber("Angle", angle);
		SmartDashboard.putNumber("Angle2", angle);
		SmartDashboard.putNumber("VisionRPM", visionSubsystem.getShooterRPM());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
