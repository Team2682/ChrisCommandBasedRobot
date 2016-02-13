package org.usfirst.frc.team2682.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2682.robot.RobotMap;
import org.usfirst.frc.team2682.robot.subsystems.Ladder;

/**
 *
 */

public class LadderControl extends Command {


	Ladder lad;
	Joystick  joy1;
	boolean swingLatch = false;
	boolean swingLatch2 = false;
	
    public LadderControl(Ladder a, Joystick joy) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	lad = a;
    	joy1 = joy;
    	
    	requires(lad);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	SmartDashboard.putNumber("SyncVal", 1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	if(joy1.getRawButton(RobotMap.JOY_LAD_UP)){
    		
    		
    	} else if (){
    		
    		
    	} else {
    		
    		
    	}*/
    	
    	if(joy1.getRawButton(RobotMap.JOY_LAD_SWING_TOGGLE)){
    		if(! swingLatch){
    			swingLatch2 = true;
    		}
    		swingLatch = true;
    	
    	} else {
    		swingLatch = false;
    		swingLatch2 = false;
    		
    	}
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
