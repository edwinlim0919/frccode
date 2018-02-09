package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class newdrivetrain extends IterativeRobot 
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
		if(Joysticks.leftJoySticky > 0 && Joysticks.rightJoySticky > 0) //straight forward or turning while moving
		{
			if(Math.abs(Joysticks.leftJoySticky - Joysticks.rightJoySticky) < 0.1)
			{
				if(Math.abs(lEnc - rEnc) < 20)
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky);
					Motors.rightMotor.set(-Joysticks.rightJoySticky);
				}
				else if(lEnc > rEnc)
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky - 0.05);
					Motors.rightMotor.set(-Joysticks.rightJoySticky);
				}
				else
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky);
					Motors.rightMotor.set(-Joysticks.rightJoySticky + 0.05);
				}
			}
			else
			{
				Motors.leftMotor.set(Joysticks.leftJoySticky);
				Motors.rightMotor.set(-Joysticks.rightJoySticky);
			}
		}
		else if(Joysticks.leftJoySticky < 0 && Joysticks.rightJoySticky < 0) //straight backwards or turning while moving 
		{
			if(Math.abs(Joysticks.leftJoySticky - Joysticks.rightJoySticky) < 0.1)
			{
				if(Math.abs(lEnc - rEnc) < 20)
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky);
					Motors.rightMotor.set(-Joysticks.rightJoySticky);
				}
				else if(lEnc > rEnc)
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky);
					Motors.rightMotor.set(-Joysticks.rightJoySticky - 0.05);
				}
				else
				{
					Motors.leftMotor.set(Joysticks.leftJoySticky + 0.05);
					Motors.rightMotor.set(-Joysticks.rightJoySticky);
				}
			}
			else
			{
				Motors.leftMotor.set(Joysticks.leftJoySticky);
				Motors.rightMotor.set(-Joysticks.rightJoySticky);
			}
		}
		else if(Joysticks.leftJoySticky > 0 && Joysticks.rightJoySticky < 0) //fast turn right
		{
			
		}
		else if(Joysticks.leftJoySticky < 0 && Joysticks.rightJoySticky > 0) //fast turn left
		{
			
		}
		else //stay still
		{
			enc.resetEncoders();
			Motors.leftMotor.set(0);
			Motors.rightMotor.set(0);
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