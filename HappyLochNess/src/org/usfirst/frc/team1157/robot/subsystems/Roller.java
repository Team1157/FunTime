package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Roller extends Subsystem {

	static CANJaguar motor = RobotMap.rollerMotor;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		motor.setPercentMode();
		motor.enableControl();
		motor.set(0.0);
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public static void forward() {
		motor.set(1.0);

	}

	public static void backward() {
		motor.set(-1.0);
	}

	public static void stop() {
		motor.set(0.0);
	}
}
