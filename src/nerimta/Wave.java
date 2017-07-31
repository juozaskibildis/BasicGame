package nerimta;

public class Wave 
{
	
	public int waveNumber;
	public int delay;	
	public int delayLeft;
	
	public int spawnRate;	
	public int enemyNumber;	
	
	public int variation;
	
	
	public Wave(int waveNumber, int delay, int spawnRate, int enemyNumber, int variation) 
	{
		this.waveNumber = waveNumber;
		this.delay = delay;
		this.delayLeft = delay;
		this.spawnRate = spawnRate;
		this.enemyNumber = enemyNumber;
		this.variation = variation;
	}


	public static void populateSpawnLocationList()
	{
		// only two because for now
		Update.spawnLocationList0.add(new SpawnLocation(-42, 50));
		Update.spawnLocationList1.add(new SpawnLocation(842, 50));
	}
	
	
	public void createSpawners()		// i is id of preset spawner locations
	{
		
		
		if(variation == 0)
		{
			for(SpawnLocation spawnLocation: Update.spawnLocationList0)
			{
				Spawner spawner = new Spawner(spawnRate, enemyNumber, spawnLocation.posX, spawnLocation.posY);
				Update.spawnerList.add(spawner);
			}
			variation++;
		}
		else
		{
			for(SpawnLocation spawnLocation: Update.spawnLocationList1)
			{
				Spawner spawner = new Spawner(spawnRate, enemyNumber, spawnLocation.posX, spawnLocation.posY);
				Update.spawnerList.add(spawner);
			}
			variation = 0;
		}
		if(spawnRate > 3)
		{
			spawnRate -= 2;
		}
		
		waveNumber++;
		enemyNumber += 5;
		
	}
	
	
	public String waveNumberString()
	{
		return waveNumber + "";
	}
	
}
