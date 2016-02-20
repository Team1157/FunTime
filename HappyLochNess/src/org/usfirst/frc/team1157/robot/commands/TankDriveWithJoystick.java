package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveWithJoystick extends Command {

    public TankDriveWithJoystick() {
	requires(Robot.drivetraintalon);
	// requires(Robot.drivetraintalon);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	Robot.drivetraintalon.driveJoy(Robot.oi.getJoystick(true));
	// Robot.drivetraintalon.driveJoy(Robot.oi.getJoystick(true));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return false;
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
