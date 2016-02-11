package org.usfirst.frc.team2682.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import org.usfirst.frc.team2682.robot.subsystems.*;

/**
 *
 */
public class BallDetect extends Trigger {
    
	ClawArm sensors;
	
	public BallDetect(ClawArm a){
		super();
		sensors = a;
	}
	
    public boolean get() {
    	//return sensors.ballSense();
        return false;
    }
}
