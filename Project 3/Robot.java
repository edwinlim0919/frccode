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
	
	double lEnc, rEnc;
	//This is the function that is called during the Tele-operated period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void teleopPeriodic() 
	{
		lEnc = enc.getLeftEncoder();
		rEnc = enc.getRightEncoder();
		joystickObject.updateMainController();
		if(Joysticks.leftJoySticky > 0) //go forward
		{
			if(Joysticks.leftJoyStickx > 0) //turn right
			{
				Motors.leftMotor.set(Joysticks.leftJoySticky);
				Motors.rightMotor.set(-Joysticks.leftJoySticky + (.5 * Joysticks.leftJoyStickx));
			}
			else if(Joysticks.leftJoyStickx < 0) //turn left
			{
				Motors.leftMotor.set(Joysticks.leftJoySticky + (.5 * Joysticks.leftJoyStickx));
				Motors.rightMotor.set(-Joysticks.leftJoySticky);
			}
			else //go straight
			{
				if(Math.abs(lEnc - rEnc) < 20) //encoders equal
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky);
					Motors.rightMotor.set(-Joysticks.leftJoySticky);
				}
				else if(lEnc > rEnc) //left has gone farther
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky - .05);
					Motors.rightMotor.set(-Joysticks.leftJoySticky);
				}
				else //right has gone farther
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky);
					Motors.rightMotor.set(-Joysticks.leftJoySticky + .05);
				}
			}
		}
		else if(Joysticks.leftJoySticky < 0) //go backward
		{
			if(Joysticks.leftJoyStickx > 0) //turn right 
			{
				Motors.leftMotor.set(Joysticks.leftJoySticky);
				Motors.rightMotor.set(-Joysticks.leftJoySticky - (.5 * Joysticks.leftJoyStickx));
			}
			else if(Joysticks.leftJoyStickx < 0) //turn left 
			{
				Motors.leftMotor.set(Joysticks.leftJoySticky - (.5 * Joysticks.leftJoyStickx));
				Motors.rightMotor.set(-Joysticks.leftJoySticky);
			}
			else //go straight
			{
				if(Math.abs(lEnc - rEnc) < 20)
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky);
					Motors.rightMotor.set(-Joysticks.leftJoySticky);
				}
				else if(lEnc > rEnc)
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky);
					Motors.rightMotor.set(-Joysticks.leftJoySticky - .05);
				}
				else
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky + .05);
					Motors.rightMotor.set(-Joysticks.leftJoySticky);
				}
			}
		}
		else //staying still or stationary turning
		{ 
			enc.resetEncoders();
			Motors.leftMotor.set(Joysticks.leftJoyStickx);
			Motors.rightMotor.set(Joysticks.leftJoyStickx);
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