package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;
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

    CANJaguar frontRight;
    CANJaguar frontLeft;
    CANJaguar backRight;
    CANJaguar backLeft;

    private RobotDrive drive;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public DriveTrain() {
	super();
	frontRight = new CANJaguar(RobotMap.frontRightMotor);
	frontLeft = new CANJaguar(RobotMap.frontLeftMotor);
	backRight = new CANJaguar(RobotMap.backRightMotor);
	backLeft = new CANJaguar(RobotMap.backLeftMotor);

	drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);

    }

    public void initDefaultCommand() {
	setDefaultCommand(new TankDriveWithJoystick());
    }

    public void driveTank(double left, double right, boolean sInput) {
	drive.tankDrive(left, right, sInput);
    }

    public void driveArcade(double speed, double rotate) {
	drive.arcadeDrive(speed, rotate, true);
    }

    public void driveJoy(Joystick joy) {
	if (joy.getName().equals("Logitech Extreme 3D")) {
	    if (joy.getZ() > 0.5 || joy.getZ() < -0.5) {
		driveArcade(0, -joy.getZ());
	    } else {
		driveArcade(-joy.getY(), -joy.getZ());
	    }
	} else if (joy.getName().equals("Logitech RumblePad 2 USB")) {
	    driveArcade(-joy.getThrottle(), -joy.getX());
	} else {
	    SmartDashboard.putString("name", joy.getName());
	}
    }
}