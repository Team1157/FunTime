package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;
import org.usfirst.frc.team1157.robot.subsystems.CameraFeeds;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CameraToggle extends Command {

    CameraFeeds camera;

    public CameraToggle() {
	// Use requires() here to declare subsystem dependencies
	//requires(Robot.cam);
	camera = Robot.cam;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	camera.changeCam();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
