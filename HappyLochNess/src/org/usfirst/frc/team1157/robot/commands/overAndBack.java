package org.usfirst.frc.team1157.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class overAndBack extends CommandGroup {

    public overAndBack(Gyro gyro) {
	addSequential(new DriveAuto(4, 0.75, gyro));
	addSequential(new TurnAuto(180, gyro));
	addSequential(new DriveAuto(4, 0.75, gyro));

	// Add Commands here:
	// addSequential();
	// To run multiple commands at the same time:
	// addParallel()
	// A command group will require all of the subsystems that each member
	// would require.
    }
}
