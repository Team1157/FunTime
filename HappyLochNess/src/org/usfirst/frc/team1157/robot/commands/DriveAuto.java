package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveAuto extends Command {

	double startTime, leftSpeed, rightSpeed;

	public DriveAuto(double Itime, double IleftSpeed, double IrightSpeed) {
		requires(Robot.drivetrain);
		setTimeout(Itime);
		leftSpeed = IleftSpeed;
		rightSpeed = IrightSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.driveTank(leftSpeed, rightSpeed, false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.driveTank(leftSpeed, rightSpeed, false);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.driveTank(0, 0, false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
