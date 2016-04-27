package org.usfirst.frc.team1157.robot;

import org.usfirst.frc.team1157.robot.commands.ArmButton;
import org.usfirst.frc.team1157.robot.commands.CameraToggle;
import org.usfirst.frc.team1157.robot.commands.DriveAuto;
import org.usfirst.frc.team1157.robot.commands.RollerButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick joy = new Joystick(0);
    private Joystick joy2 = new Joystick(1);

    public OI(Gyro gyro) {

	Button J1B10 = new JoystickButton(joy, 10);
	Button J1B11 = new JoystickButton(joy, 11);
	Button J2B11 = new JoystickButton(joy2, 11);
	Button J2B10 = new JoystickButton(joy2, 10);
	Button J2B3 = new JoystickButton(joy2, 3);
	Button J2B2 = new JoystickButton(joy2, 2);

	J1B10.whenPressed(new DriveAuto(2.5, 0.50, gyro));
	J1B11.whenPressed(new CameraToggle());
	J2B10.whileHeld(new RollerButton(1));
	J2B11.whileHeld(new RollerButton(-0.4));
	J2B3.whileHeld(new ArmButton(0.2));
	J2B2.whileHeld(new ArmButton(-0.2));

    }

    public Joystick getJoystick(boolean driveJoy) {
	if (driveJoy) {
	    return joy;
	} else {
	    return joy2;
	}
    }

    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    //// joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}
