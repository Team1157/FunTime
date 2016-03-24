package org.usfirst.frc.team1157.robot.commands;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class distanceTurnAndShootRight extends CommandGroup {

    public distanceTurnAndShootRight(Gyro gyro, AnalogInput distanceFinder, boolean reverse) {
	if(reverse) {
	    addSequential(new DriveAuto(1.5, -1, gyro));
	    addSequential(new TurnAuto(180, gyro));
	} else {
	    addSequential(new DriveAuto(3, 0.50, gyro));
	}
	
	addSequential(new DriveAutoDistance(0, 0.2, gyro, distanceFinder));
	addSequential(new TurnAuto(-50, gyro));
	addSequential(new DriveAuto(3, 0.4, gyro));
	addSequential(new RollerMove(1, 4));

	// Add Commands here:
	// addSequential();
	// To run multiple commands at the same time:
	// addParallel()
	// A command group will require all of the subsystems that each member
	// would require.
    }
}
