package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot 
{
	Encoders enc;
	Joysticks joystickObject;

	//This function is run whenever the robot starts. This function is used for any initialization of code
	@Override
	public void robotInit() 
	{
		enc = new Encoders();
		joystickObject = new Joysticks();
	}

	 //This function runs once, right before autonomous period starts. 
	@Override
	public void autonomousInit() 
	{
		
	}

	
	
	//This is the function that is called during the autonomous period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void autonomousPeriodic() 
	{
		
	}
	
	double JoyValue = 0;
	double rEnc, lEnc;
	//This is the function that is called during the Tele-operated period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void teleopPeriodic() 
	{
		rEnc = enc.getRightEncoder();
		lEnc = enc.getLeftEncoder();
		joystickObject.updateMainController();
		if(Joysticks.leftJoySticky > 0)//move straight
		{
			if(Math.abs(lEnc-rEnc) < 10)//moving straight no correction needed
			{
				Motors.leftMotor.set(Joysticks.leftJoySticky);
				Motors.rightMotor.set(-Joysticks.leftJoySticky);
			}
			else //correction
			{
				if(rEnc > lEnc)
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky);
					Motors.rightMotor.set(-Joysticks.leftJoySticky + .05);
				}
				else
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky - .05);
					Motors.rightMotor.set(-Joysticks.leftJoySticky);
				}
			}
		}
		else if(Joysticks.leftJoySticky < 0) //move backward
		{
			if(Math.abs(lEnc - rEnc) < 10) //moving straight
			{
				Motors.leftMotor.set(Joysticks.leftJoySticky);
				Motors.rightMotor.set(-Joysticks.leftJoySticky);
			}
			else //correction
			{
				if(lEnc > rEnc)
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky);
					Motors.rightMotor.set(Joysticks.leftJoySticky - .05);
				}
				else
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky + .05);
					Motors.rightMotor.set(Joysticks.leftJoySticky);
				}
			}
		}
		else
		{
			enc.resetEncoders();
			Motors.leftMotor.set(Joysticks.rightJoyStickx);
			Motors.leftMotor.set(-Joysticks.rightJoyStickx);
		}
	}
	//This is the function that is called during the test
	//Test is an option available in the driver station and can be used to test specific pieces of code.
	//This function runs periodically, meaning it acts like an infinite loop
	@Override
	public void testPeriodic() 
	{
		
	}
}
