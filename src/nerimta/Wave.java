package nerimta;

public class Wave 
{
	
	public int waveNumber;
	public int delay;	
	public int delayLeft;
	
	public int spawnRate;	
	public int enemyNumber;
	public int enemyNumberIncrease;
	
	public int variation;
	
	
	public Wave(int waveNumber, int delay, int spawnRate, int enemyNumber, int enemyNumberIncrease, int variation) 
	{
		this.waveNumber = waveNumber;
		this.delay = delay;
		this.delayLeft = delay;
		this.spawnRate = spawnRate;
		this.enemyNumber = enemyNumber;
		this.enemyNumberIncrease = enemyNumberIncrease;
		this.variation = variation;
	}


	public static void populateSpawnLocationList()
	{
		// only two because for now
		Update.spawnLocationList0.add(new SpawnLocation(-42, 50));
		Update.spawnLocationList0.add(new SpawnLocation(Game.resolutionX + 42, 50));
		
		Update.spawnLocationList1.add(new SpawnLocation((Game.resolutionX * 2) / 3, -50));
		Update.spawnLocationList1.add(new SpawnLocation(Game.resolutionX / 3, -50));
		
		Update.spawnLocationList2.add(new SpawnLocation(Game.resolutionX + 42, 50));
		
		Update.spawnLocationList3.add(new SpawnLocation(Game.resolutionX + 42, 200));
		Update.spawnLocationList3.add(new SpawnLocation(-42, 200));
		Update.spawnLocationList3.add(new SpawnLocation(Game.resolutionX / 2, 200));
	}
	
	
	public void createSpawners()		// i is id of preset spawner locations
	{
		
		
		if(variation == 1)
		{
			for(SpawnLocation spawnLocation: Update.spawnLocationList1)
			{
				// enemy number per round remain the same regardless of spawner number
				Spawner spawner = new Spawner(spawnRate * Update.spawnLocationList1.size(), enemyNumber / Update.spawnLocationList1.size(), spawnLocation.posX, spawnLocation.posY);
				Update.spawnerList.add(spawner);
			}
			variation++;
		}
		else
		if(variation == 2)
		{
			for(SpawnLocation spawnLocation: Update.spawnLocationList2)
			{
				// enemy number per round remain the same regardless of spawner number
				Spawner spawner = new Spawner(spawnRate * Update.spawnLocationList2.size(), enemyNumber / Update.spawnLocationList2.size(), spawnLocation.posX, spawnLocation.posY);
				Update.spawnerList.add(spawner);
			}
			variation++;
		}
		else
		if(variation == 3)
		{
			for(SpawnLocation spawnLocation: Update.spawnLocationList3)
			{
				// enemy number per round remain the same regardless of spawner number
				Spawner spawner = new Spawner(spawnRate * Update.spawnLocationList3.size(), enemyNumber / Update.spawnLocationList3.size(), spawnLocation.posX, spawnLocation.posY);
				Update.spawnerList.add(spawner);
			}
			variation++;
		}
		else
		{
			for(SpawnLocation spawnLocation: Update.spawnLocationList0)
			{
				// enemy number per round remain the same regardless of spawner number
				Spawner spawner = new Spawner(spawnRate * Update.spawnLocationList0.size(), enemyNumber / Update.spawnLocationList0.size(), spawnLocation.posX, spawnLocation.posY);
				Update.spawnerList.add(spawner);
			}
			variation = 1;
		}
		
		if(spawnRate > 3)		// limit spawn rate
		{
			spawnRate -= 2;		// but decrease it every wave
		}
		
		waveNumber++;
		
		enemyNumber += enemyNumberIncrease;
		
	}
	
	
	public String waveNumberString()
	{
		return waveNumber + "";
	}
	
}
