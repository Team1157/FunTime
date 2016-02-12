package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.commands.CameraUpdate;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraFeeds extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CameraUpdate());
    }
	private final int camFront;
	private final int camBack;
	private int curCam;
	private Image frame;
	private CameraServer server;
	
	public CameraFeeds()
	{
        // Get camera ids by supplying camera name ex 'cam0', found on roborio web interface
        camFront = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        camBack = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        curCam = camBack;
        // Img that will contain camera img
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // Server that we'll give the img to
        server = CameraServer.getInstance();
        server.setQuality(40);
	}
	
	public void init()
	{
		changeCam();
	}
	
	/**
	 * Stop aka close camera stream
	 */
	public void end()
	{
		NIVision.IMAQdxStopAcquisition(curCam);
	}
	
	/**
	 * Change the camera to get imgs from to a different one
	 * @param newId for camera
	 */
	public void changeCam() {
		int newId;
		if(curCam == camFront){
			newId = camBack;
		} else {
			newId = camFront;
		}
		NIVision.IMAQdxStopAcquisition(curCam);
    	NIVision.IMAQdxConfigureGrab(newId);
    	NIVision.IMAQdxStartAcquisition(newId);
    	curCam = newId;
    	updateCam();
    }
    
	/**
	 * Get the img from current camera and give it to the server
	 */
    public void updateCam()
    {
    	NIVision.IMAQdxGrab(curCam, frame, 1);
        server.setImage(frame);
    }
    
}

