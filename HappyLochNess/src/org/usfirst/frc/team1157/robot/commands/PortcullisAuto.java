package org.usfirst.frc.team1157.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class PortcullisAuto extends CommandGroup {
    
    public  PortcullisAuto(Gyro gyro) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new TurnAuto(180, gyro));
    	addParallel(new DriveAuto(3, 0.5, gyro));
    	addSequential(new ArmPIDAuto(0));
    	
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
