package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnAuto extends Command {

    Gyro gyro;
    double toAngle, angle;
    double Kp = 0.025;
    double tolerance = 0.5;

    /**
     * 
     * @param Iangle the number of degrees to turn
     * @param Igyro pass in gyro
     */
    public TurnAuto(double Iangle, Gyro Igyro) {
	// Use requires() here to declare subsystem dependencies
	requires(Robot.drivetraintalon);
	gyro = Igyro;
	toAngle = Iangle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	gyro.reset();
	tolerance = SmartDashboard.getNumber("Tol");
	Kp = SmartDashboard.getNumber("KP");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	double dif = gyro.getAngle() - toAngle;
	Robot.drivetraintalon.driveArcade(0, dif * Kp);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	if (Math.abs(gyro.getAngle() - toAngle) < tolerance) {
	    return true;
	} else {
	    return false;
	}
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.drivetraintalon.driveTank(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
