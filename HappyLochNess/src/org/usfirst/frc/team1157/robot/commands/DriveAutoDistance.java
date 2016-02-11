package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveAutoDistance extends Command {
	
	double power, angle, distance;
	double Kp = 0.1;
	Gyro gyro;
	AnalogInput distanceFinder;

    public DriveAutoDistance(double Idistance, double Ipower, Gyro Igyro, AnalogInput IdistanceFinder) {
    	requires(Robot.drivetrain);	
    	
    	distance = Idistance;
    	power = Ipower;
		gyro = Igyro;
		distanceFinder = IdistanceFinder;
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	angle = gyro.getAngle();
		Robot.drivetrain.driveArcade(power, -angle*Kp);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ((distanceFinder.getAverageVoltage()*1000.0)/9.8 <= distance) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.driveTank(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
