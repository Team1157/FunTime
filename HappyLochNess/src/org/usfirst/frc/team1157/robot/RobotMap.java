package org.usfirst.frc.team1157.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static CANTalon rollerMotor = new CANTalon(6);
	public static CANTalon armMotor; // = new CANTalon(6);
	
	public static Gyro gyro = new AnalogGyro(0);
	
	public static AnalogInput pot = new AnalogInput(1);
	
	public static AnalogInput distanceFinder = new AnalogInput(2);
	
	public static int frontRightMotor = 2;
	public static int backRightMotor = 3;
	public static int backLeftMotor = 4;
	public static int frontLeftMotor = 5;
	
	public static CameraServer camera;
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
