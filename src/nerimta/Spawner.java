package nerimta;

// this object is going to create enemies

public class Spawner 
{
	boolean alive = true;		// flag for disposal
	
	int maxRate;		// rate of spawning
	int rate;			// for calculating
	int enemies;		// number of enemies it will spawn
	
	double posX;
	double posY;
	
	double enemySpeedX;		// for moving
	double enemySpeedY;
	
	// methods
	
	public Spawner(int maxRate, int enemies, double posX, double posY) 
	{
		this.maxRate = maxRate;
		this.rate = this.maxRate;
		this.enemies = enemies;
		this.posX = posX;
		this.posY = posY;
	}
	


	public Unknown spawnUnknown(double posX, double posY, double speedX, double speedY, Wave wave)
	{
		Unknown unknown = new Unknown(speedX, speedY, posX, posY, wave);
		Update.enemyList.add(unknown);
		return unknown;
	}
	
	
}
