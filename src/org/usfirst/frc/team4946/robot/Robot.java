
package org.usfirst.frc.team4946.robot;

import org.usfirst.frc.team4946.robot.commands.AutonomousWrapper;
import org.usfirst.frc.team4946.robot.commands.ExampleCommand;
import org.usfirst.frc.team4946.robot.subsystems.BallIntake;
import org.usfirst.frc.team4946.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4946.robot.subsystems.ExampleSubsystem;

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

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final BallIntake ballSubsystem = new BallIntake();
	public static final DriveTrain driveSubsystem = new DriveTrain();
	public static OI oi;

	Command auto;
	SendableChooser<Integer> m_autoMode;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();

		m_autoMode = new SendableChooser<Integer>();
		m_autoMode.addObject("Left Position - With Shoot", RobotConstants.LEFT_POSITION_SHOOT);
		m_autoMode.addObject("Left Position -  No Shoot", 2);
		m_autoMode.addObject("Middle Position Breach Left - with Shoot", 4);
		m_autoMode.addObject("Middle Position Breach Right - No Shoot", 5);
		m_autoMode.addObject("Middle Position No Breach - With Shoot", 6);
		m_autoMode.addObject("Right Position- No Shoot", 3);
		SmartDashboard.putData("Autonomous - Script", m_autoMode);

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
		int autoMode = m_autoMode.getSelected();

		auto = new AutonomousWrapper(autoMode);
		auto.start();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (auto != null)
			auto.cancel();
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
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
