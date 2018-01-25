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
	
	double RightJoyValueY;
	double RightJoyValueX;
	double rightSpeed;
	double leftSpeed;
	//This is the function that is called during the Tele-operated period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void teleopPeriodic() 
	{
		RightJoyValueY = joystickObject.fixController(Joysticks.rightJoySticky);
		RightJoyValueX = joystickObject.fixController(Joysticks.rightJoyStickx);
		rightSpeed = -RightJoyValueY;
		leftSpeed = RightJoyValueY;
		
		if(RightJoyValueY <= 1 && RightJoyValueY >= 0.15)
		{
			if(RightJoyValueX >= 0.15 && RightJoyValueX <= 1)
			{
				joystickObject.updateMainController();
				rightSpeed = rightSpeed + 0.2;
				Motors.rightMotor.set(rightSpeed);
				Motors.leftMotor.set(leftSpeed);
			}
			else if(RightJoyValueX >= -1 && RightJoyValueX <= -0.15)
			{
				joystickObject.updateMainController();
				leftSpeed = leftSpeed - 0.2;
				Motors.rightMotor.set(rightSpeed);
				Motors.leftMotor.set(leftSpeed);
			}
			else if(RightJoyValueX == 0)
			{
				if(encodersObject.getRightEncoder() > encodersObject.getLeftEncoder())
				{
					joystickObject.updateMainController();
					rightSpeed = rightSpeed + 0.05;
					Motors.leftMotor.set(leftSpeed);
					Motors.rightMotor.set(rightSpeed);
				}
				else if(encodersObject.getRightEncoder() < encodersObject.getLeftEncoder())
				{
					joystickObject.updateMainController();
					leftSpeed = leftSpeed - 0.05;
					Motors.leftMotor.set(leftSpeed);
					Motors.rightMotor.set(rightSpeed);
				}
				else if(encodersObject.getRightEncoder() == encodersObject.getLeftEncoder())
				{
					joystickObject.updateMainController();
					Motors.leftMotor.set(leftSpeed);
					Motors.rightMotor.set(rightSpeed);
				}
			}
		}
		else if(RightJoyValueY >= -1 && RightJoyValueY <= -0.15)
		{
			if(RightJoyValueX >= 0.15 && RightJoyValueX <= 1)
			{
				joystickObject.updateMainController();
				rightSpeed = rightSpeed - 0.2;
				Motors.rightMotor.set(rightSpeed);
				Motors.leftMotor.set(leftSpeed);
			}
			else if(RightJoyValueX >= -1 && RightJoyValueX <= -0.15)
			{
				joystickObject.updateMainController();
				leftSpeed = leftSpeed + 0.2;
				Motors.rightMotor.set(rightSpeed);
				Motors.leftMotor.set(leftSpeed);
			}
			else if(RightJoyValueX == 0)
			{
				if(encodersObject.getRightEncoder() < encodersObject.getLeftEncoder())
				{
					joystickObject.updateMainController();
					rightSpeed = rightSpeed - 0.05;
					Motors.leftMotor.set(leftSpeed);
					Motors.rightMotor.set(rightSpeed);
				}
				else if(encodersObject.getRightEncoder() > encodersObject.getLeftEncoder())
				{
					joystickObject.updateMainController();
					leftSpeed = leftSpeed + 0.05;
					Motors.leftMotor.set(leftSpeed);
					Motors.rightMotor.set(rightSpeed);
				}
				else if(encodersObject.getRightEncoder() == encodersObject.getLeftEncoder())
				{
					joystickObject.updateMainController();
					Motors.leftMotor.set(leftSpeed);
					Motors.rightMotor.set(rightSpeed);
				}
			}
		}
		else if(RightJoyValueY == 0)
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