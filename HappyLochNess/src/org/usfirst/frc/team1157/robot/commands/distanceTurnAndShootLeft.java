package org.usfirst.frc.team1157.robot.commands;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class distanceTurnAndShootLeft extends CommandGroup {
    
    public  distanceTurnAndShootLeft(Gyro gyro, AnalogInput distanceFinder, boolean reverse) {
	if(reverse) {
	    addSequential(new DriveAuto(1.5, -1, gyro)); 
	    addSequential(new TurnAuto(180, gyro));
	} else {
	    addSequential(new DriveAuto(3, 0.6, gyro)); 
	}
	
	addSequential(new DriveAutoDistance(0, 0.2, gyro, distanceFinder));
	addSequential(new TurnAuto(50, gyro));
	addSequential(new DriveAuto(3, 0.4, gyro));
	addSequential(new RollerMove(1, 4));
	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
