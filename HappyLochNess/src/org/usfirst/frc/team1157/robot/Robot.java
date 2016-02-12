
package org.usfirst.frc.team1157.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team1157.robot.commands.DriveAuto;
import org.usfirst.frc.team1157.robot.commands.TurnAuto;
import org.usfirst.frc.team1157.robot.commands.distanceTurnAndShoot;
import org.usfirst.frc.team1157.robot.commands.overAndBack;
import org.usfirst.frc.team1157.robot.subsystems.Arm;
import org.usfirst.frc.team1157.robot.subsystems.ArmWithoutPID;
import org.usfirst.frc.team1157.robot.subsystems.CameraFeeds;
import org.usfirst.frc.team1157.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1157.robot.subsystems.DriveTrainTalon;
import org.usfirst.frc.team1157.robot.subsystems.Roller;

import com.ni.vision.NIVision;

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

	public static Roller roller = new Roller();
	public static OI oi;
	public static DriveTrain drivetrain = new DriveTrain();
	public static DriveTrainTalon drivetraintalon;// = new DriveTrainTalon();
	public static Arm arm; // = new Arm(2.0, 0.0, 0.0);
	public static ArmWithoutPID armwopid; // = new ArmWithoutPID();
	Command autonomousCommand;
	SendableChooser chooser;
	Gyro gyro;
	AnalogInput pot;
	AnalogInput distanceFinder;
	int count = 0;
	public static CameraFeeds cam = new CameraFeeds();

	/**
	 * This function is run when the robot is first started up and should be
	 * 
	 * 
	 * used for any initialization code.
	 */
	public void robotInit() {
		gyro = RobotMap.gyro;
		pot = RobotMap.pot;
		distanceFinder = RobotMap.distanceFinder;

		SmartDashboard.putBoolean("Set PID", false);
		SmartDashboard.putNumber("P", 2);
		SmartDashboard.putNumber("I", 0);
		SmartDashboard.putNumber("D", 0);
		SmartDashboard.putNumber("KP", 0.03);
		SmartDashboard.putNumber("Tol", 3);
		SmartDashboard.putNumber("Distance", 0);
		// arm.setInputRange(0.005, 4.855);
		// arm.setOutputRange(-1, 1);

		SmartDashboard.putNumber("Setpoint", 0);

		oi = new OI();
		chooser = new SendableChooser();
		chooser.addObject("back and forth", new overAndBack(gyro));
		chooser.addDefault("Brute Force", new DriveAuto(5, 0.5, gyro, 0));
		chooser.addObject("turn 90", new TurnAuto(90, gyro));
		chooser.addObject("find distance, turn and shoot", new distanceTurnAndShoot(gyro, distanceFinder));
		SmartDashboard.putData("Auto mode", chooser);
		
		cam.init();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}

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
	public void autonomousInit() {
		gyro.reset();
		autonomousCommand = (Command) chooser.getSelected();
		// autonomousCommand = new RollerMove(1);

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("Gyro", gyro.getAngle());
		SmartDashboard.putNumber("Pot", pot.getValue());
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		// arm.enable();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		SmartDashboard.putNumber("PotVolt", pot.getAverageVoltage());
		SmartDashboard.putNumber("Pot", pot.getValue());
		SmartDashboard.putNumber("Distance (inches):", (distanceFinder.getAverageVoltage() * 1000.0) / 9.8);
		double value = SmartDashboard.getNumber("Setpoint");
		// arm.setSetpoint(value);
		Scheduler.getInstance().run();

		if (SmartDashboard.getBoolean("Set PID")) {
			setPID();
			SmartDashboard.putBoolean("Set PID", false);

		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	private void setPID() {
		// arm.disable();
		// arm = new Arm(SmartDashboard.getNumber("P"),
		// SmartDashboard.getNumber("I"), SmartDashboard.getNumber("D"));
		// arm.setInputRange(0.005, 4.855);
		// arm.setOutputRange(-1, 1);
		// double value = SmartDashboard.getNumber("Setpoint");
		// arm.setSetpoint(value);
		// arm.enable();

	}
}