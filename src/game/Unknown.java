package nerimta;

public class Unknown extends Enemy 
{
	
	public Unknown	(double speedX, double speedY, double posX, double posY, Wave wave) 
		{
			super(speedX, speedY, posX, posY);
			
			// this enemy`s statistics
			sizeX = 35;						
			sizeY = 35;
			
			fire = 5;				// so it shoots the moment it spawns
			
			// statistics that get better after every wave
			if(80 - wave.waveNumber > 40)			// less than forty makes game too hard?
			{
				fireRate = 80 - wave.waveNumber;		
			}
			
			bulletSpeed = 3 + (wave.waveNumber / 10);
		}
}
