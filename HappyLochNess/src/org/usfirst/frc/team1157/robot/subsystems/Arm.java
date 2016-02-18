package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends PIDSubsystem {
    
	AnalogInput pot = RobotMap.pot;
	CANTalon motor = RobotMap.armMotor;
	
	public Arm(double p, double i, double d) {
		super("Arm", p, i, d);
		setAbsoluteTolerance(0.05); //Set the absolute error which is considered tolerable for use with OnTarget. The value is in the same range as the PIDInput values.
		getPIDController().setContinuous(false);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
    	return pot.getAverageVoltage();
    }
    
    protected void usePIDOutput(double output) {
    	motor.pidWrite(output);
    	SmartDashboard.putNumber("pidOut", output);
    }
}

