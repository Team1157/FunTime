package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.TankDriveWithJoystick;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	CANJaguar frontLeft = RobotMap.frontLeftMotor;
	CANJaguar frontRight = RobotMap.frontRightMotor;
	CANJaguar backLeft = RobotMap.backLeftMotor;
	CANJaguar backRight = RobotMap.backRightMotor;

	private RobotDrive drive;
	private Encoder leftEncoder, rightEncoder;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public DriveTrain() {
		super();
		drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);

	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoystick());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void driveTank(double left, double right, boolean sInput) {
		drive.tankDrive(left, right, sInput);
	}

	public void driveArcade(double speed, double rotate) {
		drive.arcadeDrive(speed, rotate, true);
	}

	public void driveJoy(Joystick joy) {
		if (joy.getName().equals("Logitech Extreme 3D")) {
			if (joy.getTrigger()) {
				driveArcade(joy.getY(), joy.getZ() * 0.75);
			} else {
				driveArcade(-joy.getY(), joy.getZ() * 0.75);
			}
		} else if (joy.getName().equals("Logitech rumblepad")) {
			if (joy.getRawButton(6)) {
				driveTank(joy.getThrottle(), joy.getY(), true);
			} else {
				driveTank(-joy.getY(), -joy.getThrottle(), true);
			}
		} else {
			SmartDashboard.putString("name", joy.getName());
		}
	}
}