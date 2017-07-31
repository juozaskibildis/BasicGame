package nerimta;

import java.util.Random;

public class Enemy 
{
	// speed
	double speedX;
	double speedY;
	
	
	// location
	double posX;
	double posY;
	double sizeX;
	double sizeY;
	
	
	int hp;
	
	int fire;				// number of frames until shooting
	int fireRate;			// delay after every shot in frames
	
	double bulletSpeed; 
	double bulletSpeedX;
	double bulletSpeedY;
	
	//-------------------------------------------------------------------------
	// methods
	
	// constructor
	public Enemy	(
			double speedX, 	double speedY,
			double posX,		double posY
					)	 
	{
		this.speedX = speedX;
		this.speedY = speedY;
		this.posX = posX;
		this.posY = posY;
	}
	
	
	
	public void calculateBulletSpeed(double characterPosX, double characterPosY)
	{
		
		/*
		there is a problem with this method because enemies that are off screen have 
		coordinates lets say -50 and when you make it an absolute value you get wrong distance.
		as far as i am concerned there is no easy fix so enemies off screen will be inaccurate.
		same problem when player character is off screen.
		
		also this seems very inaccurate overall for some reason
		*/
		
		// calculate distance
		double yDistance;
		double xDistance; 
		
		boolean xIsNegative = false;
		boolean yIsNegative = false;
		
		Random random = new Random();		// using random to avoid enemies firing huge strings of bullets into one spot
		
		
		// get negatives
		yDistance = characterPosY - posY;
		xDistance = characterPosX - posX; 
		if(xDistance < 0)
		{
				xIsNegative = true;
		}
		if(yDistance < 0)
		{
			yIsNegative = true;
		}
				
		// recalculate distance with absolute values
		xDistance = Math.abs(characterPosX) - Math.abs(posX);
		yDistance = Math.abs(characterPosY) - Math.abs(posY);
				
		// make distance absolute
		xDistance = Math.abs(xDistance);	
		yDistance = Math.abs(yDistance);
		
		// avoid dividing by zero
		if(yDistance == 0)			// this will make game inaccurate but there is no point in getting it absolutely correct
		{						
			yDistance = 0.00000000001;
		}
		if(xDistance == 0)
		{
			xDistance = 0.00000000001;
		}
		
		// proportion of speed
		double xAngle = xDistance / yDistance;					
		double yAngle = yDistance / xDistance;
		
		if(xAngle < 1)
		{
			// spreading x and y speed so that it targets player character
			// random variation of 1.5
			bulletSpeedX = (random.nextInt(2) - 1) + (random.nextFloat() - 0.5) + (bulletSpeed * xAngle);
			bulletSpeedY = (random.nextInt(2) - 1) + (random.nextFloat() - 0.5) + (bulletSpeed  * (1 - xAngle));
		}
		else
		if(yAngle < 1)
		{
			bulletSpeedY = (random.nextInt(2) - 1) + (random.nextFloat() - 0.5) + (bulletSpeed * yAngle);
			bulletSpeedX = (random.nextInt(2) - 1) + (random.nextFloat() - 0.5) + (bulletSpeed  * (1 - yAngle));
		}
		
		// take negative values into account
		if(xIsNegative == true)
		{
			bulletSpeedX = -bulletSpeedX;
		}
		if(yIsNegative == true)
		{
			bulletSpeedY = -bulletSpeedY;
		}
		
		
	}
	
	
	//-----------------------------------------------------------------------
	// setters and getters
	
	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getFire() {
		return fire;
	}

	public void setFire(int fire) {
		this.fire = fire;
	}

	public int getFireRate() {
		return fireRate;
	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}


	public void setBulletSpeed(float bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	public double getBulletSpeed() {
		return bulletSpeed;
	}

	public double getBulletSpeedX() {
		return bulletSpeedX;
	}

	public void setBulletSpeedX(float bulletSpeedX) {
		this.bulletSpeedX = bulletSpeedX;
	}

	public double getBulletSpeedY() {
		return bulletSpeedY;
	}

	public void setBulletSpeedY(float bulletSpeedY) {
		this.bulletSpeedY = bulletSpeedY;
	}


	
}
