package org.usfirst.frc.team2682.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;
import org.usfirst.frc.team2682.robot.*;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	VictorSP wheelR;
	VictorSP wheelL;
	
	SuperVelocityPID rightControl;
	SuperVelocityPID leftControl;
	
	RobotDrive PWMChassis;
	
	Encoder nCodeR;
	Encoder nCodeL;
	
    public void initDefaultCommand() {
    	
    	nCodeL = new Encoder(RobotMap.LEFT_ENCODE_1, RobotMap.LEFT_ENCODE_2);
    	nCodeR = new Encoder(RobotMap.RIGHT_ENCODE_1, RobotMap.RIGHT_ENCODE_2);
    	
    	PWMChassis = new RobotDrive(RobotMap.RIGHT_WHEELS, RobotMap.LEFT_WHEELS);
    	
    	nCodeL.setPIDSourceType(PIDSourceType.kRate);
    	nCodeR.setPIDSourceType(PIDSourceType.kRate);
    	
    	wheelR = new VictorSP(8/*RobotMap.RIGHT_WHEELS*/);
    	wheelL = new VictorSP(7/*RobotMap.LEFT_WHEELS*/);
    	
    	rightControl = new SuperVelocityPID(RobotMap.WHEEL_P, RobotMap.WHEEL_I, RobotMap.WHEEL_D, nCodeR);
    	leftControl = new SuperVelocityPID(RobotMap.WHEEL_P, RobotMap.WHEEL_I, RobotMap.WHEEL_D, nCodeL);
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public double getPWMSpeedMult(Joystick joy1){
    	
    	if(joy1.getRawButton(RobotMap.JOY_SECOND_SPEED)){
    		return RobotMap.SECOND_PWM;
    	}else if(joy1.getRawButton(RobotMap.JOY_THIRD_SPEED)){
    		return RobotMap.THIRD_PWM;
    	}else {
    		return RobotMap.FIRST_PWM;
    	}
    }
    
    public double getFeedSpeed(Joystick joy1){
    	if(joy1.getRawButton(RobotMap.JOY_SECOND_SPEED)){
    		return RobotMap.SECOND_DRIVE_SPEED;
    	}else if(joy1.getRawButton(RobotMap.JOY_THIRD_SPEED)){
    		return RobotMap.THIRD_DRIVE_SPEED;
    	}else {
    		return RobotMap.FIRST_DRIVE_SPEED;
    	}
    	
    }
    
    
    public void JoyPWMDrive(Joystick joy1){
    	double speedMult = getPWMSpeedMult(joy1);
    	
    	if(rightControl.isEnable() || leftControl.isEnable()){
    		rightControl.disable();
    		leftControl.disable();
    	}
    	PWMChassis.arcadeDrive(joy1.getY()*speedMult, joy1.getX()*speedMult);
    }
    
    
    public void enableFeedbackControl(){
    	rightControl.enable();
    	leftControl.enable();
    }
    
    public void joyFeedbackUpdate(Joystick joy1){
    	
    	
    	if(! rightControl.isEnable() || ! leftControl.isEnable()){
    		rightControl.enable();
    		leftControl.enable();
    	}
    	
    	double driveSpeed = getFeedSpeed(joy1);
    	
    	double moveValue = joy1.getY();
    	double rotateValue = joy1.getX();
    	
    	
    	double leftMotorSpeed = 0.0;
    	double rightMotorSpeed = 0.0;
    	
    	if (moveValue > RobotMap.DEAD_UP) {
			if (rotateValue > RobotMap.DEAD_RIGHT) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			} else if (rotateValue < -RobotMap.DEAD_LEFT){
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		} else if (moveValue < -RobotMap.DEAD_DOWN){
			if (rotateValue > RobotMap.DEAD_RIGHT) {
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			} else if (rotateValue < -RobotMap.DEAD_LEFT) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}
    	
    	
    	
    	rightControl.setSetpoint(rightMotorSpeed * driveSpeed);
    	leftControl.setSetpoint( - leftMotorSpeed * driveSpeed);
    	
    	wheelR.set(rightControl.getOutput());
    	wheelL.set(leftControl.getOutput());
    	
    }
	
	
	
	
	
}

