package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveAuto extends Command {

    double startTime, power, angle;
    double Kp = 0.1;
    Gyro gyro;
    double targetAngle;

    /**
     * 
     * @param Itime how long to go for in seconds
     * @param Ipower what speed to go at (from -1 to 1)
     * @param Igyro pass gyro
     */
    public DriveAuto(double Itime, double Ipower, Gyro Igyro) {
	requires(Robot.drivetraintalon);
	setTimeout(Itime);
	power = Ipower;
	gyro = Igyro;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	angle = gyro.getAngle();
	Robot.drivetraintalon.driveArcade(power, -angle * Kp);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return isTimedOut();
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
