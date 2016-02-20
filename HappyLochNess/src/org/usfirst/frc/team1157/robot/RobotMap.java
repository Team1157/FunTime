package org.usfirst.frc.team1157.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    public static CANJaguar rollerMotor = new CANJaguar(3);
    public static CANJaguar armMotor = new CANJaguar(4);

    public static Gyro gyro = new AnalogGyro(0);

    public static AnalogInput pot = new AnalogInput(1);

    public static AnalogInput distanceFinder = new AnalogInput(2);

    public static int frontRightMotor = 6;
    public static int backRightMotor = 7;
    public static int backLeftMotor = 8;
    public static int frontLeftMotor = 9;

    public static CameraServer camera;
}
