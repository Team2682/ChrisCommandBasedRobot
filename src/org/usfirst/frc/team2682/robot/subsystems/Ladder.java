package org.usfirst.frc.team2682.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2682.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class Ladder extends Subsystem {
    	
	DoubleSolenoid swing;
	
	TalonSRX winch;
	TalonSRX extend;
	
	Timer time;
	
	DigitalInput topLimit;
	DigitalInput bottomLimit;
	
	Encoder exEncode;
	//Encoder winEncode;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	swing = new DoubleSolenoid(RobotMap.LADDER_PNU_1, RobotMap.LADDER_PNU_2);
    	swing.set(Value.kForward);
    	
    	time = new Timer();
    }
    
    
    //Swings the ladder to the ready position with pnumatics
    public void swingOut(){
    	if (! getSwing() && time.getMatchTime() > RobotMap.LADDER_UNLOCK_TIME){
    		swing.set(Value.kForward);
    	}
    }
    
    
    public void swingIn(){
    	if(getSwing()){
    		swing.set(Value.kReverse);
    	}	
    }
    
    //!!!!!! CHECK FUNCTION !!!!!!
    //This will need to be modified after construction
    //The intended function is to return true when the ladder is extended and false when it is not
    public boolean getSwing(){
    	if(swing.get() == Value.kForward){
    		return true;
    	} else if (swing.get() == Value.kReverse){
    		return false;
    	} else {
    		System.out.println("!!!!!! Get ladder swing has failed !!!!!!");
    		return false;
    	}
    }
    
    //Currently going on the assumption That positive values are moveing up
    //Winch is master, Extendor is slave - if the value > 1 the extend is moving faster. Value < 1
    public void syncMove(double speed, double syncVal){
    	speed = moveFilter(speed);
    	winch.set(speed);	
    	extend.set(speed*syncVal);
    }
    
    
    //This sets the conditions under which the ladder is able to extend
    public boolean canLadder(double speed){
    	if(((speed > 0 &&  ! getTopLimit()) || (speed < 0 &&  ! getBottomLimit())) && time.getMatchTime() > RobotMap.LADDER_UNLOCK_TIME){
    		return true;
    	} else{
    		return false;
    	}
    }
    
    //filters out invalid movement -- returns 0 if the ladder cannot move
    public double moveFilter(double speed){
    	if(((speed > 0 &&  ! getTopLimit()) || (speed < 0 &&  ! getBottomLimit())) && time.getMatchTime() > RobotMap.LADDER_UNLOCK_TIME){
        	return speed;
        	} else{
        	return 0;
        	}
    }
    
    /*
    public void debugCanMove(){
    	SmartDashboard.putBoolean("Can Move Positive?", canMove(0.75));
    	SmartDashboard.putBoolean("Can Move Negative", canMove(-0.75));
    	
    }*/
    
    public void stopLift(){
    	extend.set(0);
    	winch.set(0);
    }
    
    public void stopExtend(){
    	extend.set(0);
    }
    
    public void stopWinch(){
    	winch.set(0);
    }
    
    public void moveExtend(double speed){
    	extend.set(moveFilter(speed));
    }
    
    public void moveWinch(double speed){
    	winch.set(moveFilter(speed));
    }
    
    //!!! This may need to be inverted !!!
    //Returns true if the top limit is actuated
    public boolean getTopLimit(){
    	return topLimit.get();
    }
    
  //!!! This may need to be inverted !!!
    //Returns true if the bottom limit is actuated
    public boolean getBottomLimit(){
    	return bottomLimit.get();
    }
    
}

