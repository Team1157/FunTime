package org.usfirst.frc.team1157.robot.commands;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class distanceTurnAndShoot extends CommandGroup {

    public distanceTurnAndShoot(Gyro gyro, AnalogInput distanceFinder) {
	addSequential(new DriveAuto(3, 0.75, gyro));
	addSequential(new DriveAutoDistance(75, 0.6, gyro, distanceFinder));
	addSequential(new TurnAuto(-45, gyro));
	addSequential(new DriveAuto(2, 0.6, gyro));
	addSequential(new RollerMove(-1, 2));

	// Add Commands here:
	// addSequential();
	// To run multiple commands at the same time:
	// addParallel()
	// A command group will require all of the subsystems that each member
	// would require.
    }
}
