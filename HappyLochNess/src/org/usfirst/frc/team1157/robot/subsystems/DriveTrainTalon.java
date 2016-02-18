package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.TankDriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainTalon extends Subsystem {

    boolean testing = false;

    CANTalon right = new CANTalon(RobotMap.frontRightMotor);
    CANTalon left = new CANTalon(RobotMap.frontLeftMotor);
    CANTalon rightSlave = new CANTalon(RobotMap.backRightMotor);
    CANTalon leftSlave = new CANTalon(RobotMap.backLeftMotor);

    private RobotDrive drive;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public DriveTrainTalon() {
	super();

	rightSlave.changeControlMode(TalonControlMode.Follower);
	rightSlave.set(right.getDeviceID());

	leftSlave.changeControlMode(TalonControlMode.Follower);
	leftSlave.set(left.getDeviceID());

	right.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	right.reverseSensor(false);
	right.configNominalOutputVoltage(+0.0f, -0.0f);
	right.configPeakOutputVoltage(+12.0f, -12.0f);
	right.setProfile(0);
	right.setF(0);
	right.setP(0);
	right.setI(0);
	right.setD(0);

	left.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	left.reverseSensor(false);
	left.configNominalOutputVoltage(+0.0f, -0.0f);
	left.configPeakOutputVoltage(+12.0f, -12.0f);
	left.setProfile(0);
	left.setF(0);
	left.setP(0);
	left.setI(0);
	left.setD(0);

	drive = new RobotDrive(left, right);

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

	SmartDashboard.putNumber("motorOutputRight", (right.getOutputVoltage() / right.getBusVoltage()));
	SmartDashboard.putNumber("motorOutputLeft", (left.getOutputVoltage() / left.getBusVoltage()));

	if (testing) {

	    if (joy.getRawButton(7)) {
		double targetSpeed = joy.getAxis(AxisType.kY) * 1500;
		left.changeControlMode(TalonControlMode.Speed);
		right.changeControlMode(TalonControlMode.Speed);
		left.set(targetSpeed);
		right.set(targetSpeed);
	    } else {
		left.changeControlMode(TalonControlMode.PercentVbus);
		right.changeControlMode(TalonControlMode.PercentVbus);
		left.set(joy.getAxis(AxisType.kY));
		right.set(joy.getAxis(AxisType.kY));
	    }

	} else {

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
}