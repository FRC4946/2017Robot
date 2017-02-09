package org.usfirst.frc.team4946.robot;

import org.usfirst.frc.team4946.robot.commands.autonomous.AutonomousWrapperGearFirst;
import org.usfirst.frc.team4946.robot.commands.autonomous.AutonomousWrapperShootFirst;
import org.usfirst.frc.team4946.robot.subsystems.BallIntake;
import org.usfirst.frc.team4946.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4946.robot.subsystems.GearDropper;
import org.usfirst.frc.team4946.robot.subsystems.Indexer;
import org.usfirst.frc.team4946.robot.subsystems.ShooterMotor;
import org.usfirst.frc.team4946.robot.subsystems.Winch;

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

	public static Winch winchSubsystem;
	public static ShooterMotor shooterSubsystem; // MERGE SUBSYSTEM
	public static GearDropper gearSubsystem;
	public static BallIntake ballSubsystem;
	public static DriveTrain driveSubsystem;
	public static OI oi;
	public static Indexer indexerSubsystem;

	Command auto;
	SendableChooser<Integer> m_autoMode;
	SendableChooser<Integer> m_gearOrShoot;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		winchSubsystem = new Winch();
		shooterSubsystem = new ShooterMotor();
		gearSubsystem = new GearDropper();
		ballSubsystem = new BallIntake();
		driveSubsystem = new DriveTrain();
		indexerSubsystem = new Indexer();

		oi = new OI();

		m_gearOrShoot = new SendableChooser<Integer>();
		m_gearOrShoot.addObject("Gear First", RobotConstants.Auto.IF_GEAR_FIRST);
		m_gearOrShoot.addObject("Shoot First", RobotConstants.Auto.IF_SHOOT_FIRST);
		SmartDashboard.putData("Auto Order", m_gearOrShoot);

		m_autoMode = new SendableChooser<Integer>();
		driveSubsystem.calibrateGyroscope();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		
	if(m_gearOrShoot.getSelected()==RobotConstants.Auto.IF_GEAR_FIRST){
			m_autoMode = new SendableChooser<Integer>();
			m_autoMode.addDefault("Left Position", RobotConstants.Auto.LEFT_POSITION);
			m_autoMode.addObject("Right Position", RobotConstants.Auto.RIGHT_POSITION);
			m_autoMode.addObject("Breach - No Shoot", RobotConstants.Auto.BREACH_NO_SHOOT);
			m_autoMode.addObject("Middle Position - Breach & Shoot", RobotConstants.Auto.MIDDLE_POSITION_BREACH_SHOOT);
			m_autoMode.addObject("Middle Position - Breach Left", RobotConstants.Auto.MIDDLE_POSITION_BREACH_LEFT);
			m_autoMode.addObject("Middle Position - Breach Left", RobotConstants.Auto.MIDDLE_POSITION_BREACH_RIGHT);
			m_autoMode.addObject("Middle Position - Do Nothing", RobotConstants.Auto.MIDDLE_POSITION_DO_NOTHING);
			SmartDashboard.putData("Autonomous Script - Gear First", m_autoMode);
		} else {
			m_autoMode = new SendableChooser<Integer>();
			m_autoMode.addObject("Middle Position - Just Shoot", RobotConstants.Auto.MIDDLE_POSITION_JUST_SHOOT);
			SmartDashboard.putData("Autonomous Script - Shoot First", m_autoMode);

		}

		
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
		int autoMode =0;// m_autoMode.getSelected();
		int order = m_gearOrShoot.getSelected();

		if (order == RobotConstants.Auto.IF_GEAR_FIRST) {
			auto = new AutonomousWrapperGearFirst(autoMode, isRed);
		} else if (order == RobotConstants.Auto.IF_SHOOT_FIRST) {
			auto = new AutonomousWrapperShootFirst(autoMode, isRed);
		}
		// auto = new AutonomousWrapperTurningFromBack(autoMode, isRed);
		auto.start();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("Encoder Distance: ", driveSubsystem.getEncoderDistance());
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

		SmartDashboard.putNumber("RPM", shooterSubsystem.getRPM());
		SmartDashboard.putNumber("Set", shooterSubsystem.getSetRPM());
		SmartDashboard.putNumber("Encoder Distance: ", driveSubsystem.getEncoderDistance());
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
