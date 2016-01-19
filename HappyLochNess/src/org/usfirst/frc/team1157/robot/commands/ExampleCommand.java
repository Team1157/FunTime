
package org.usfirst.frc.team1157.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1157.robot.Robot;

/**
 *
 */
public class ExampleCommand extends Command {

	boolean finished = false;
	
    public ExampleCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.exampleSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int x = 0;
    	if(x>5){
    		finished = true;
    	}else{
    		x++;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
