package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BBBBBorzadrivetrain extends IterativeRobot 
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
		joystickObject.updateMainController();
		lEnc = enc.getLeftEncoder();
		rEnc = enc.getRightEncoder();
		if(Joysticks.rightTrigger > 0) //forwards
		{
			if(Joysticks.rightJoyStickx > 0) //turn right
			{
				Motors.leftMotor.set(Joysticks.rightTrigger);
				Motors.rightMotor.set(-Joysticks.rightTrigger + (0.75 * Joysticks.rightJoyStickx));
			}
			else if(Joysticks.rightJoyStickx < 0) //turn left
			{
				Motors.leftMotor.set(Joysticks.rightTrigger + (0.75 * Joysticks.rightJoyStickx));
				Motors.rightMotor.set(-Joysticks.rightTrigger);
			}
			else //go straight
			{
				if(Math.abs(lEnc - rEnc) < 10)
				{
					Motors.leftMotor.set(Joysticks.rightTrigger);
					Motors.rightMotor.set(-Joysticks.rightTrigger);
				}
				else if(lEnc > rEnc)
				{
					Motors.leftMotor.set(Joysticks.rightTrigger - 0.03);
					Motors.rightMotor.set(-Joysticks.rightTrigger);
				}
				else
				{
					Motors.leftMotor.set(Joysticks.rightTrigger);
					Motors.rightMotor.set(-Joysticks.rightTrigger + 0.03);
				}
			}
		}
		else if(Joysticks.leftTrigger > 0) //backwards
		{
			
		}
		else //staying still OR TURNING NIBBA
		{
			enc.resetEncoders();
			Motors.leftMotor.set(Joysticks.rightJoyStickx);
			Motors.rightMotor.set(Joysticks.rightJoyStickx);
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