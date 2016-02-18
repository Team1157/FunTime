package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Roller extends Subsystem {

	CANTalon motor = RobotMap.rollerMotor;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {

		motor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		motor.enableControl();
		motor.set(0.0);
		// Set the default command for a subsystem here.
		//setDefaultCommand(new RollerWithJoystick());
	}

	public boolean speed(double speed) {
		if (speed < 1 || speed > -1) {
			motor.set(speed);
		} else {
			return false;
		}
		return true;
	}

	public void forward() {
		motor.set(1.0);

	}

	public void backward() {
		motor.set(-1.0);
	}

	public void stop() {
		motor.set(0.0);
	}
	
	 double map(double oldMin,double oldMax,double newMin,double newMax,double oldValue) {
		double oldRange = (oldMax - oldMin);
		double newRange = (newMax - newMin);
		double newValue = (((oldValue - oldMin) * newRange) / oldRange) + newMin;
		return newValue;
	}
}
