package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.Encoder;

public class Encoders 
{
	Encoder leftEncoder = new Encoder(0,1, false, Encoder.EncodingType.k4X);
	Encoder rightEncoder = new Encoder(4,5, false, Encoder.EncodingType.k4X);
	
	public double getRightEncoder()
	{
		return rightEncoder.get();
	}
	public double getLeftEncoder()
	{
		return -leftEncoder.get();
	}
	
	public void resetEncoders()
	{
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	
}
