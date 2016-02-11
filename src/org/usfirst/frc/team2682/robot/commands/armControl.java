package org.usfirst.frc.team2682.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team2682.robot.RobotMap;
import org.usfirst.frc.team2682.robot.subsystems.ClawArm;

/**
 *
 */
public class armControl extends Command {

	ClawArm arm;
	Joystick joy1;
	
	boolean grabLatch;
	boolean grabLatch2;
	
    public armControl(ClawArm a, Joystick joy) {
    	
    	arm = a;
    	joy1 = joy;
    	requires(arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(joy1.getRawButton(RobotMap.JOY_ARM_UP)){
    		arm.moveArm(RobotMap.BUTTON_ARM_SPEED);
    	} else if(joy1.getRawButton(RobotMap.JOY_ARM_DOWN)){
    		arm.moveArm(-RobotMap.BUTTON_ARM_SPEED);
    	} else {
    		arm.moveArm(0);
    	}
    	
    	
    	if(joy1.getRawButton(RobotMap.JOY_GRAB_TOGGLE)){
    		if(! grabLatch)
    			grabLatch2 = true;
    		grabLatch = true;
    	} else {
    		grabLatch = false;
    		grabLatch2 = false;
    	}
    	
    	if(grabLatch2){
    		arm.toggleClaw();
    		grabLatch2 = false;
    	}
    	
    	if(joy1.getRawButton(RobotMap.JOY_PUNCH) || joy1.getRawButton(RobotMap.JOY_COMBO_EJECT)){
    		arm.punch();
    	} else {
    		arm.unPunch();
    	}
    		
    	if(joy1.getRawButton(RobotMap.JOY_COMBO_EJECT)){
    		arm.closeClaw();
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
