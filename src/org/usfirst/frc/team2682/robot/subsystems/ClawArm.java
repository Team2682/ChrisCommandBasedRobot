package org.usfirst.frc.team2682.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.DigitalInput;

import org.usfirst.frc.team2682.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 *
 */
public class ClawArm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	DoubleSolenoid claw;
	DoubleSolenoid punch;
	
	VictorSP arm;
	
	Encoder armEncode;
	
	DigitalInput topLimit;
	DigitalInput bottomLimit;
	
	PIDController armControl;
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	claw = new DoubleSolenoid(RobotMap.CLAW_PNU_1, RobotMap.CLAW_PNU_2);
    	punch = new DoubleSolenoid(RobotMap.PUNCH_PNU_1, RobotMap.PUNCH_PNU_2);
    	
    	arm = new VictorSP(RobotMap.ARM_MOTOR);
    	
    	armEncode = new Encoder(RobotMap.ARM_ENCODE_1, RobotMap.ARM_ENCODE_2);
    	
    	//armEncode.setPIDSourceType(PIDSourceType.kDisplacement);
    	armControl = new PIDController(0.01,0,0, armEncode, arm);
    	
    }
    
    
    public void resetEncoder(){
    	armEncode.reset();
    }
    
    public double filterArmMotion(double speed){
    	if((speed > 0 && ! topLimit.get()) || (speed < 0 && ! bottomLimit.get())){
    		return speed;
    	} else {
    	return 0;
    	}
    }
    
    public void moveArm(double speed){
    	armControl.disable();
    	arm.set(filterArmMotion(speed));
    }
    
    public void closeClaw(){
    	claw.set(Value.kForward);
    }
    
    public void openClaw(){
    	claw.set(Value.kReverse);
    }
    
    public void toggleClaw(){
    	if(isGrab()){
    		openClaw();
    	} else {
    		closeClaw();
    	}
    	
    }
    
    
    public void punch(){
    	punch.set(Value.kForward);
    	
    }
    
    public void unPunch(){
    	punch.set(Value.kReverse);
    }
    
    
    
   
    
    public boolean isGrab(){
    	if(claw.get() == Value.kForward){
    		return true;
    	} else if(claw.get() == Value.kReverse){
    		return false;
    	} else {
    		return false;
    	}
    	
    }
    
    public void armPIDUpdate(double target){
    	
    	if( ! armControl.isEnabled()){
    		armControl.enable();
    	}
    	
    	armControl.setSetpoint(target);
    	
    }
    
    
    
    
    //Should return true if a ball is detected inside the mechanism
    /*public boolean ballSense(){
    
    
    }
    */
    
}

