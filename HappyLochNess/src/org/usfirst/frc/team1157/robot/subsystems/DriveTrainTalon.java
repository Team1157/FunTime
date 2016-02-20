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

    CANTalon right;// = new CANTalon(RobotMap.frontRightMotor);
    CANTalon left;// = new CANTalon(RobotMap.frontLeftMotor);
    CANTalon rightSlave;// = new CANTalon(RobotMap.backRightMotor);
    CANTalon leftSlave;// = new CANTalon(RobotMap.backLeftMotor);

    private RobotDrive drive;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public DriveTrainTalon() {
	super();
	
	right = new CANTalon(RobotMap.frontRightMotor);
	left = new CANTalon(RobotMap.frontLeftMotor);
	rightSlave = new CANTalon(RobotMap.backRightMotor);
	leftSlave = new CANTalon(RobotMap.backLeftMotor);
	
	rightSlave.changeControlMode(TalonControlMode.Follower);
	rightSlave.set(right.getDeviceID());

	leftSlave.changeControlMode(TalonControlMode.Follower);
	leftSlave.set(left.getDeviceID());

	right.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	right.reverseSensor(false);
	right.configNominalOutputVoltage(+0.0f, -0.0f);
	right.configPeakOutputVoltage(+12.0f, -12.0f);
	right.setProfile(0);
	right.setF(0.3700);
	right.setP(0.01);
	right.setI(0);
	right.setD(1);

	left.reverseOutput(true);
	left.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	left.reverseSensor(false);
	left.configNominalOutputVoltage(+0.0f, -0.0f);
	left.configPeakOutputVoltage(+12.0f, -12.0f);
	left.setProfile(0);
	left.setF(0.3700);
	left.setP(0.05);
	left.setI(0);
	left.setD(1);
	
//	left.changeControlMode(TalonControlMode.PercentVbus);
//	right.changeControlMode(TalonControlMode.PercentVbus);
	if(!testing){
	    left.changeControlMode(TalonControlMode.PercentVbus);
	    right.changeControlMode(TalonControlMode.PercentVbus);
	    drive = new RobotDrive(left, right);
	   // drive.setMaxOutput(375); //405 is about the max RPM
	}

    }

    public void initDefaultCommand() {
	setDefaultCommand(new TankDriveWithJoystick());
    }

    public void driveTank(double left, double right, boolean sInput) {
	drive.tankDrive(left, right, sInput);
    }

    public void driveArcade(double speed, double rotate) {
	drive.arcadeDrive(speed, rotate, false);
    }

    public void driveJoy(Joystick joy) {
	double outputRight = (right.getOutputVoltage() / right.getBusVoltage());
	SmartDashboard.putNumber("motorOutputRight", outputRight);
	SmartDashboard.putNumber("motorOutputLeft", (left.getOutputVoltage() / left.getBusVoltage()));
	SmartDashboard.putNumber("Right Speed", right.getSpeed());
	SmartDashboard.putNumber("Left Speed", left.getSpeed());
	
	
	if (testing) {

	    if (joy.getRawButton(7)) {
		SmartDashboard.putNumber("Button 7: ", 1);
		double targetSpeed = joy.getAxis(AxisType.kY) * 405.0;
		SmartDashboard.putNumber("Target Speed", -targetSpeed);
		SmartDashboard.putNumber("Left Error: ", targetSpeed-left.getSpeed());
		SmartDashboard.putNumber("Right Error: ", -targetSpeed-right.getSpeed());
		left.changeControlMode(TalonControlMode.Speed);
		right.changeControlMode(TalonControlMode.Speed);
		left.set(targetSpeed);
		right.set(targetSpeed);
	    } else {
		SmartDashboard.putNumber("Button 7: ", 0);
		left.changeControlMode(TalonControlMode.PercentVbus);
		right.changeControlMode(TalonControlMode.PercentVbus);
		left.set(-joy.getY());
		right.set(joy.getY());
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