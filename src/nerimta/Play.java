package nerimta;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/*
to do:

features
make menus controllable by keyboard
make different enemy spawn locations
make more enemy types

sound
make Background music

art
make buttons

*/


public class Play extends BasicGameState
{
	
	// sounds
	public static Sound characterHit;
	public static Sound characterFire1;
	public static Sound characterFire2;
	public static Sound characterFire3;
	public static Sound enemyFire;
	public static Sound characterMove;
	
	// Image variables
	Image playerSprite;
	Image playerHitbox;
	Image enemySprite;
	Image playerBulletSprite;
	Image enemyBulletSprite;
	Image gameBackground;
	
	// temporary variables
	PlayerBullet tempBullet = null;
	
	public Character character;
	public Wave wave;
	
	
	//---------------------------------------------------------------------------
	// main methods
	
	public Play(int stateId)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		// image initialization
		playerSprite = new Image("/res/playerSprite.png");
		playerBulletSprite = new Image("/res/playerBulletSprite.png");
		
		enemySprite = new Image("/res/enemySprite.png");
		enemyBulletSprite = new Image("/res/enemyBulletSprite.png");
		
		gameBackground = new Image("/res/gameBackground.png");
	
		// sounds
		characterHit = new Sound("/res/hit.wav");
		characterFire1 = new Sound("/res/fire1.wav");			// three fire sounds for variation
		characterFire2 = new Sound("/res/fire2.wav");			// technically random pitch should be enough but since
		characterFire3 = new Sound("/res/fire3.wav");			// this sound will be heard a lot it is good practice
		enemyFire = new Sound("/res/enemy.wav");
		characterMove = new Sound("/res/movement.wav");
		
		
		// list initialization
		Update.bulletsList = new LinkedList<Bullet>();
		Update.characterBulletsList = new LinkedList<PlayerBullet>();
		Update.enemyBulletsList = new LinkedList<EnemyBullet>();
		Update.bulletsToRemove = new LinkedList<Bullet>();
		
		Update.enemyList = new LinkedList<Enemy>();
		Update.enemiesToRemove = new LinkedList<Enemy>();
		
		Update.spawnerList = new LinkedList<Spawner>();
		Update.spawnersToRemove = new LinkedList<Spawner>();
		
		Update.waveList = new LinkedList<Wave>();
		
		Update.spawnLocationList0 = new LinkedList<SpawnLocation>();
		Update.spawnLocationList1 = new LinkedList<SpawnLocation>();
		
		// a method to populate spawn location list
		Wave.populateSpawnLocationList();
		
		// new game objects
		character = new Character(100, 7, 6, 7, 400, 300, 8);
		wave = new Wave(0, 240, 10, 60, 0);
	
	}
	
	//----------------------------------------------------------------------------
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException					
	{
		g.drawImage(gameBackground, 0, 0);
		
		g.drawString("Wave " + wave.waveNumberString(), 50, 50);
		g.drawString("Hp " + character.hpToString(), 50, 100);
		
		// draw player character
		playerSprite.draw((float)character.posX - ((float)character.sizeX / 2), (float)character.posY - ((float)character.sizeY / 2));
		
		// draw bullets
		if(!Update.characterBulletsList.isEmpty())
		{
			for(Bullet bullet: Update.characterBulletsList)
			{
				playerBulletSprite.draw((float)bullet.posX - ((float)bullet.sizeX /2), (float)bullet.posY - ((float)character.sizeY /2));
			}
		}
		
		if(!Update.enemyBulletsList.isEmpty())
		{
			for(Bullet bullet: Update.enemyBulletsList)
			{
				enemyBulletSprite.draw((float)bullet.posX - ((float)bullet.sizeX / 2), (float)bullet.posY - ((float)bullet.sizeY / 2));
			}
		}
		
		if(!Update.enemyList.isEmpty())
		{
			for(Enemy enemy: Update.enemyList)
			{
				enemySprite.draw((float)enemy.posX - ((float)enemy.sizeX /2), (float)enemy.posY - ((float)enemy.sizeX /2));
			}
		}
		
	}
	
	//-----------------------------------------------------------------------------
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException				
	{	
		// controls
		//-----------------------------------------------------------------------------------------------------
		Input input = gc.getInput();
		
		character.focus = character.controlFocus(input, character.focus);												// ability to slow down movement
		character.posX = character.controlHorizontal(input, character.focus, character.speed,  character.posX);			// character horizontal movements
		character.posY = character.controlVertical(input, character.focus, character.speed,  character.posY);			// characters vertical movement	
		tempBullet = character.controlShoot(input, character.posX, character.posY, character.bulletSpeed, character);	// shooting
		
		//-------------------------------------------------------------------------------------------------------
		// updates
		
		Update.bullets(character);									// all logic associated with bullets
		Update.enemies(character);
		Update.waves(wave);										// updates spawn waves
		Update.spawners(wave);									// all logic associated with enemy creation
		character.boostDecay();
		character.movementSoundDelay();
		Game.soundChange(input);
		Game.fullscreenToggle(input, gc);
		
		//-------------------------------------------------------------------------------------------------------
		// pausing
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			sbg.enterState(Game.pause);
		}
		
		// failure state
		if(character.hp <= 0)
		{
			sbg.enterState(Game.gameOver);
		}
		
	}
	
	//------------------------------------------------------------------------------------------
	// setters and getters
	
	public int getID()
	{
		return Game.play;
	}

	public double getPlayerPosX() {
		return character.posX;
	}

	public void setPlayerPosX(double playerPosX) {
		this.character.posX = playerPosX;
	}

	public double getPlayerPosY() {
		return character.posY;
	}

	public void setPlayerPosY(double playerPosY) {
		this.character.posY = playerPosY;
	}

	public Image getEnemyBulletSprite() {
		return enemyBulletSprite;
	}

	public void setEnemyBulletSprite(Image enemyBulletSprite) {
		this.enemyBulletSprite = enemyBulletSprite;
	}

	public List<Bullet> getBulletsList() {
		return Update.bulletsList;
	}
	
	
	//------------------
	// sound methods
	
	public static Sound pickCharacterFire()
	{
		Sound picked;
		Random random = new Random();
		int number = random.nextInt(2);
		if(number == 0)
		{
			picked = characterFire1;
		}
		else
		if(number == 1)
		{
			picked = characterFire2;
		}
		else
		{
			picked = characterFire3;
		}
		
		return picked;
	}
	
}
