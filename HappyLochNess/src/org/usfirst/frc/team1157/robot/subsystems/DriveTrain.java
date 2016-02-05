package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.commands.TankDriveWithJoystick;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	CANJaguar frontRight = new CANJaguar(2);
	CANJaguar frontLeft = new CANJaguar(5);
	CANJaguar backRight = new CANJaguar(3);
	CANJaguar backLeft = new CANJaguar(4);
	
	private RobotDrive drive;

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
				driveArcade(joy.getY(), -joy.getZ() * 0.75);
			} else {
				driveArcade(-joy.getY(), -joy.getZ() * 0.75);
			}
		} else if (joy.getName().equals("Logitech RumblePad 2 USB")) {
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