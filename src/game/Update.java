package nerimta;

import java.util.List;
import java.util.Random;



public class Update 
{
	// lists
	public static List<Bullet> bulletsList;
	public static List<EnemyBullet> enemyBulletsList;
	public static List<PlayerBullet> characterBulletsList;
	public static List<Bullet> bulletsToRemove;
	
	public static List<Enemy> enemyList;
	public static List<Enemy> enemiesToRemove;
	
	public static List<Spawner> spawnerList;
	public static List<Spawner> spawnersToRemove;
	
	public static List<Wave> waveList;
	
	public static List<SpawnLocation> spawnLocationList0;
	public static List<SpawnLocation> spawnLocationList1;
	public static List<SpawnLocation> spawnLocationList2;
	public static List<SpawnLocation> spawnLocationList3;
	
	public static Random random = new Random();
	
	
	//----------------------------------------------------------
	
	public static void bullets(Character character)
	{
		bulletMovement();
		enemyBulletRemovalOnHit(character);
		characterBulletRemovalOnHit();
		bulletRemovalOnLeavingPlayArea();
	}
	
	public static void enemies(Character character)
	{
		enemyMovement();
		enemyTurnOnLeavingPlayArea();
		enemyShooting(character);
		enemyRemoval();
	}
	
	public static void spawners(Wave wave)
	{
		spawnerSpawn(wave);
		spawnerRemove();
	}
	
	public static void waves(Wave wave)
	{
		waveCreateSpawners(wave);
	}
	
	
	//------------------------------------------------------------------------------------------
	// bullet methods
	
	public static void bulletRemovalOnLeavingPlayArea()
	{
		if(!Update.bulletsList.isEmpty())
		{
			for(Bullet bullet: Update.bulletsList)
			{
				// if bullet is out of bounds
				if(bullet.getBulletPosX() > Game.resolutionX + 100 || bullet.getBulletPosX() < -100)
				{
					Update.bulletsToRemove.add(bullet);											
				}
				else 
					if(bullet.getBulletPosY() > Game.resolutionY + 100 || bullet.getBulletPosY() < -100)
					{
						Update.bulletsToRemove.add(bullet);
					}
			}
			
			// goes through list of bullets to remove and removes them from other lists
			for(Bullet bullet: Update.bulletsToRemove)
			{
				Update.bulletsList.remove(bullet);
				
				if(Update.enemyBulletsList.contains(bullet))
				{
					Update.enemyBulletsList.remove(bullet);
				}
				else
					if(Update.characterBulletsList.contains(bullet))
					{
						Update.characterBulletsList.remove(bullet);
					}
			}
			
			Update.bulletsToRemove.clear();			// clears itself
		
		}
	}
	
	public static void bulletMovement()
	{
		for(Bullet bullet: bulletsList)
		{
			bullet.setBulletPosX(bullet.updateX());
			bullet.setBulletPosY(bullet.updateY());
		}
	}
	
	// if player gets hit
	public static void enemyBulletRemovalOnHit(Character character)
	{
		for(Bullet bullet: enemyBulletsList)
		{
			// checking for collisions with player
			if(		bullet.posX >= character.posX - character.sizeX / 2 &&
					bullet.posX <= character.posX + character.sizeX / 2 &&
					bullet.posY >= character.posY - character.sizeY / 2 &&
					bullet.posY <= character.posY + character.sizeY / 2
					)
			{
				character.hp--;
				bulletsToRemove.add(bullet);		// flags bullet for removal
				
				// play sound
				Play.characterHit.play((float)( 8 + random.nextInt(4)) / 10 , 0.5f * (float)Game.masterVolume);
			}
		}
	}
	
	public static void characterBulletRemovalOnHit()
	{
		for(Bullet bullet: characterBulletsList)
		{
			for(Enemy enemy: enemyList)
			{
				// checks collision with size box
				if(		bullet.posX >= enemy.posX - enemy.sizeX / 2 && 
						bullet.posX <= enemy.posX + enemy.sizeX / 2 &&
						bullet.posY >= enemy.posY - enemy.sizeY / 2 &&
						bullet.posY <= enemy.posY + enemy.sizeY / 2
						)
				{
					bulletsToRemove.add(bullet);		// flags bullet for removal
					enemiesToRemove.add(enemy);			// flags enemy for removal
					
					// play sound
					Play.characterHit.play((float)( 14 + random.nextInt(4)) / 10 , 0.15f * (float)Game.masterVolume);
				}
			}
		}
	}
	
	//--------------------------------------------------------------------------------------------
	// enemy methods
	
	public static void enemyMovement()
	{
		for(Enemy enemy: enemyList)
		{
			enemy.posX = enemy.posX + enemy.speedX;
			enemy.posY = enemy.posY + enemy.speedY;
		}
	}
	
	public static void enemyTurnOnLeavingPlayArea()
	{
		if(!enemyList.isEmpty())
		{
			for(Enemy enemy: enemyList)
			{
				if(enemy.getPosX() > Game.resolutionX + 100 || enemy.getPosX() < -100)
				{
					enemy.speedX = -enemy.speedX;		// turn on reaching a wall							
				} 
				
				if(enemy.getPosY() > 300 || enemy.getPosY() < -100)
				{
					enemy.speedY = -enemy.speedY;		// turn on reaching ceiling or floor
				}
			}
		
		}
	}
	
	public static void enemyShooting(Character character)
	{
		for(Enemy enemy: enemyList)
		{
			if(enemy.fire == 0)
			{
				enemy.calculateBulletSpeed(character.posX, character.posY);
				EnemyBullet bullet = new EnemyBullet(enemy.posX, enemy.posY, enemy.bulletSpeedX, enemy.bulletSpeedY);	
				enemy.fire = enemy.fireRate;
				
				bulletsList.add(bullet);
				enemyBulletsList.add(bullet);
				
				// play sound
				Play.enemyFire.play((float)(8f + random.nextInt(4)) / 10, 0.15f * (float)Game.masterVolume);
			}
			else
			{
				enemy.fire--;
			}
		}
	}
	
	
	public static void enemyRemoval()
	{
		for(Enemy enemy: enemiesToRemove)
		{
			enemyList.remove(enemy);
		}
		enemiesToRemove.clear();
	}
	
	
	//----------------------------------------------------------------------------------------------------
	// enemy spawner methods
	
	public static void spawnerSpawn(Wave wave)
	{
		for(Spawner spawner: spawnerList)
		{
			if(spawner.enemies > 0)							// if it did not spawn all the enemies it should
			{
				if(spawner.rate == 0)						// if it is ready to spawn an enemy
				{
					spawner.spawnUnknown(spawner.posX, spawner.posY, 4.8 + (wave.waveNumber / 2), 1.2 + (wave.waveNumber / 2), wave);	
					spawner.rate = spawner.maxRate;
					spawner.enemies--;
				}
				else
				{
					spawner.rate--;
				}
			}
			else
			{
				spawnersToRemove.add(spawner);						// flag this object for removal
			}
		
		}
	
	}
	
	public static void spawnerRemove()
	{
		for(Spawner spawner: spawnersToRemove)
		{
			spawnerList.remove(spawner);
		}
		
		spawnersToRemove.clear();

	}
	
	
	//----------------------------------------------------------------------------------------------------------------
	// enemy wave methods
	
	public static void waveCreateSpawners(Wave wave)
	{
		// if wave is clear
		if(spawnerList.isEmpty() && enemyList.isEmpty())
		{		
			if(wave.delayLeft == 0)
			{
				wave.createSpawners();
				
				wave.delayLeft = wave.delay;			// reset the delay
			}
			else
			{
				// remove enemy bullets
				if(!enemyBulletsList.isEmpty())
				{
					bulletsToRemove.add(enemyBulletsList.get(0));		// removes a single bullet per frame
				}
				
				wave.delayLeft--;
			}
		}
	}

}

