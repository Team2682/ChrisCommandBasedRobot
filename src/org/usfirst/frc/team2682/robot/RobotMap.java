package org.usfirst.frc.team2682.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//Mode switches
	
	//Reverse values - !!!!!!!!! NOT YET IMPLEMENTED !!!!!!!!
	//1 is default, -1 is inverted
	public final static int LAD_REVERSE = 1;
	public final static int CLAW_REVERSE = 1;
	
	public final static int RIGHT_DRIVE_REVERSE = 1;
	public final static int LEFT_DRIVE_REVERSE = 1;
	
	public final static int RIGHT_PID_REVERSE = 1;
	public final static int LEFT_PID_REVERSE = 1;
	
	
	
	//JOYSTICKS HERE
	public final static int DRIVE_STICK = 0;
	public final static int AUX_STICK = 1;
	public final static int AUX_PANEL = 2;


	//JOYSTICK BUTTONS


	//Stick 1
	
	public final static int JOY_SECOND_SPEED = 3;
	public final static int JOY_THIRD_SPEED = 4;
	
	
	//Stick 2
	
	public final static int JOY_ARM_UP = 4;
	public final static int JOY_ARM_DOWN = 6;
	
	public final static int JOY_GRAB_TOGGLE = 1;
	
	public final static int JOY_PUNCH = 2;
	
	public final static int JOY_COMBO_EJECT = 5;
	
	
	//Stick 3
	
	
	//Conrols related variables here
	
	public final static double FIRST_DRIVE_SPEED = 2200;
	public final static double SECOND_DRIVE_SPEED = 1200;
	public final static double THIRD_DRIVE_SPEED = 800;
	
	
	public final static double FIRST_PWM = 1.0;
	public final static double SECOND_PWM = 0.62;
	public final static double THIRD_PWM = 0.35;
	
	public final static double BUTTON_ARM_SPEED = 0.5;
	
	
	
	//PWMS HERE
	public final static int RIGHT_WHEELS = 0;
	public final static int LEFT_WHEELS = 1;

	public final static int LADDER_EXTENDER = 3;
	public final static int LADDER_WINCH = 4;
	
	public final static int ARM_MOTOR = 5;


	//DIOs
	public final static int RIGHT_ENCODE_1 = 0;
	public final static int RIGHT_ENCODE_2 = 1;
	public final static int LEFT_ENCODE_1 = 2;
	public final static int LEFT_ENCODE_2 = 3;
	
	
	public final static int ARM_TOP_LIMIT = 4;
	public final static int ARM_BOT_LIMIT = 5;
	
	
	public final static int ARM_ENCODE_1 = 6;
	public final static int ARM_ENCODE_2 = 7;
	
	public final static int LAD_TOP_LIMIT = 8;
	public final static int LAD_BOT_LIMIT = 9;
	
	public final static int LAD_EXT_ENCODE_1 = 10;
	public final static int LAD_EXT_ENCODE_2 = 11;
	
	
	//public final static int LAD_PNU_TOPLIM = TBD
	//public final static int LAD_PNU_TOPLIM = TBD


	//Analog Input
	
	//public final static int CLAW_SENSOR_1 = TBD
	//public final static int CLAW_SENSOR_2 = TBD
	
	
	//PNUMATICS

	public final static int COMPRESSOR_INDEX = 0;

	public final static int LADDER_PNU_1 = 0;
	public final static int LADDER_PNU_2 = 1;

	public final static int CLAW_PNU_1 = 2;
	public final static int CLAW_PNU_2 = 3;
	
	public final static int PUNCH_PNU_1 = 4;
	public final static int PUNCH_PNU_2 = 5;

	//LOGIC VARIABLES


	
	//PID parameters for the wheels -- may need to be negative in the subsystem
	public final static double WHEEL_P = -0.0008;
	public final static double WHEEL_I = 0;
	public final static double WHEEL_D = -0.0002;

	//Dead zones for the drive stick
	public final static double DEAD_UP = 0.1;
	public final static double DEAD_DOWN = 0.1;
	public final static double DEAD_LEFT = 0.1;
	public final static double DEAD_RIGHT = 0.1;
	
	//Timing
	
	public final static int LADDER_UNLOCK_TIME = 20;
	
	





}
