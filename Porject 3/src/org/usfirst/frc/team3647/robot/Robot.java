package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot 
{
	Encoders encodersObject;
	Joysticks joystickObject;

	//This function is run whenever the robot starts. This function is used for any initialization of code
	@Override
	public void robotInit() 
	{
		encodersObject = new Encoders();
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
	
	//double leftSpeed = joystickObject.fixController(Joysticks.leftJoySticky);
	//double rightSpeed = joystickObject.fixController(Joysticks.leftJoySticky);
	double JoyValue;
	double rightSpeed = JoyValue;
	double leftSpeed = -JoyValue;
	//This is the function that is called during the Tele-operated period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void teleopPeriodic() 
	{
		if(JoyValue >= 0.15 && JoyValue <= 1)
		{
			if(Math.abs(encodersObject.getRightEncoder() - encodersObject.getLeftEncoder()) < 300)//straight
			{
				joystickObject.updateMainController();
				JoyValue = joystickObject.fixController(Joysticks.leftJoySticky);
				rightSpeed = -JoyValue;
				leftSpeed = JoyValue;
				Motors.leftMotor.set(leftSpeed);
				Motors.rightMotor.set(rightSpeed);
				
			}
			else // not straight
			{
				if(encodersObject.getRightEncoder() > encodersObject.getLeftEncoder())
				{
					joystickObject.updateMainController();
					rightSpeed = rightSpeed + 0.05;
					Motors.leftMotor.set(leftSpeed);
					Motors.rightMotor.set(rightSpeed);
				}
				else
				{
					joystickObject.updateMainController();
					leftSpeed = leftSpeed - 0.05;
					Motors.leftMotor.set(leftSpeed);
					Motors.rightMotor.set(rightSpeed);
				}
			}
		}
		else if(JoyValue >= -1 && JoyValue <= -0.15)
		{
			if(Math.abs(encodersObject.getRightEncoder() - encodersObject.getLeftEncoder()) < 300)
			{
				joystickObject.updateMainController();
				JoyValue = joystickObject.fixController(Joysticks.leftJoySticky);
				rightSpeed = -JoyValue;
				leftSpeed = JoyValue;
				Motors.leftMotor.set(leftSpeed);
				Motors.rightMotor.set(rightSpeed);
			}
			else
			{
				if(encodersObject.getRightEncoder() < encodersObject.getLeftEncoder())
				{
					joystickObject.updateMainController();
					rightSpeed = rightSpeed - 0.05;
					Motors.leftMotor.set(leftSpeed);
					Motors.rightMotor.set(rightSpeed);
				}
				else
				{
					joystickObject.updateMainController();
					leftSpeed = leftSpeed + 0.05;
					Motors.leftMotor.set(leftSpeed);
					Motors.rightMotor.set(rightSpeed);
				}
			}
		}
		else
		{
			encodersObject.resetEncoders();
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
