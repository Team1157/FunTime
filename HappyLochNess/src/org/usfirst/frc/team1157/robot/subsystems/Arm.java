package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends PIDSubsystem {

    AnalogInput pot = RobotMap.pot;
    CANJaguar motor = RobotMap.armMotor;
    double inMin = 0.005; // TODO:find the correct numbers that go here
    double inMax = 4.855;

    public Arm(double p, double i, double d) {
	super("Arm", p, i, d);
	setAbsoluteTolerance(0.05); // Set the absolute error which is
				    // considered tolerable for use with
				    // OnTarget. The value is in the same range
				    // as the PIDInput values.
	getPIDController().setContinuous(false);
	setInputRange(inMin, inMax);
	setOutputRange(-1, 1);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new ArmWithJoystick());
    }
    
    public void armControl(Joystick joy) {
	setSetpoint(map(-1, 1, inMin, inMax, joy.getThrottle()));
    }

    protected double returnPIDInput() {
	return pot.getAverageVoltage();
    }

    protected void usePIDOutput(double output) {
	motor.pidWrite(output);
	SmartDashboard.putNumber("pidOut", output);
    }
    
    double map(double oldMin,double oldMax,double newMin,double newMax,double oldValue) {
	double oldRange = (oldMax - oldMin);  
	double newRange = (newMax - newMin);  
	double newValue = (((oldValue - oldMin) * newRange) / oldRange) + newMin; 
	 return newValue;
	}
}
