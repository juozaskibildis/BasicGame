package nerimta;

import java.util.Random;

import org.newdawn.slick.Input;

public class Character 
{
	public static int movementSoundIsPlaying = 7;
	public static int movementSoundWait = 7;
	
	
	// speed
	double speed;
	double bulletSpeed;
	double boost;			// for calculating
	double boostGain;		// max boost
	
	// position
	double posX;
	double posY;
	double sizeX = 35;
	double sizeY = 35;
	
	boolean focus = false;
	
	// statistics
	int hp;					// amount of times allowed to get hit
	
	int fire;				// number of frames until shooting
	int fireRate;			// delay after every shot in frames
	
	//---------------------------------------------------------------------------
	// constructor
	
	public Character (int hp, int firerate, double speed, double bulletspeed, double posX, double posY, double boostGain)
	{
		setHp(hp);
		setFireRate(firerate);
		setSpeed(speed);
		setBulletSpeed(bulletspeed);
		setPosX(posX);
		setPosY(posY);
		this.boostGain = boostGain;
		this.boost = boostGain;
	}
	
	//----------------------------------------------------------------------------------------------------------------
	// methods
	
	public void boostDecay()
	{
		if(boost > 0)
		{
			boost--;
		}
	}
	
	public void movementSoundDelay()
	{
		if(Character.movementSoundIsPlaying != 0)
		{
			Character.movementSoundIsPlaying--;
		}
	}
	
	//------------------------------------------------------------------------------------------------------------
	// focus
	
	public boolean controlFocus (Input input, boolean focus)
	{
		if(input.isKeyDown(Input.KEY_LSHIFT))
		{
			focus = true;
		}
		else
		{
			focus = false;
		}
		
		return focus;
	}
		
		
		//------------------------------------------------------------------------------------------------------------
		// vertical movement
		
		public double controlVertical (Input input, boolean focus, double speed, double playerPosY)
		{
			
			if(playerPosY > sizeY)
			{
				if(input.isKeyDown(Input.KEY_UP))
				{
					
					if(focus == true)
					{
						boost = boostGain;			// reset boost on focus
						playerPosY -= speed / 3;
					}
					else
					{
						// so that movement sound does not play on every single frame
						if(movementSoundIsPlaying == 0)
						{
							Random random = new Random();
							Play.characterMove.play((float)(6 + random.nextInt(4)) / 10, 0.33f * (float)Game.masterVolume);
							movementSoundIsPlaying = movementSoundWait;
						}
						playerPosY -= (speed + boost);
					}
				}
			}	
			
			if(playerPosY < Game.resolutionY - sizeY)
			{
				if(input.isKeyDown(Input.KEY_DOWN))
				{
					
					if(focus == true)
					{
						boost = boostGain;
						playerPosY += speed / 3;
					}
					else
					{
						if(movementSoundIsPlaying == 0)
						{
							Random random = new Random();
							Play.characterMove.play((float)(6 + random.nextInt(4)) / 10, 0.33f * (float)Game.masterVolume);
							movementSoundIsPlaying = movementSoundWait;
						}
						playerPosY += (speed + boost);
					}
				}
			}
			
			return playerPosY;
			
		}
		
		//-----------------------------------------------------------------------------------------------
		// horizontal movement
		
		public double controlHorizontal(Input input, boolean focus, double speed, double playerPosX)
		{			
			
			if(input.isKeyDown(Input.KEY_LEFT))
			{
				if(playerPosX > sizeX)									// if player is not near the border
				{
					
					if(focus == true)
					{	
						boost = boostGain;
						playerPosX -= speed / 3;
					}
					else
					{
						if(movementSoundIsPlaying == 0)
						{
							Random random = new Random();
							Play.characterMove.play((float)(6 + random.nextInt(4)) / 10, 0.33f * (float)Game.masterVolume);
							movementSoundIsPlaying = movementSoundWait;
						}
						playerPosX -= (speed + boost);
					}
				}
			}
					
			if(input.isKeyDown(Input.KEY_RIGHT))
			{
				if(playerPosX < Game.resolutionX - sizeX)
				{
					
					if(focus == true)
					{
						boost = boostGain;
						playerPosX += speed / 3;
					}
					else
					{
						if(movementSoundIsPlaying == 0)
						{
							Random random = new Random();
							Play.characterMove.play((float)(6 + random.nextInt(4)) / 10, 0.33f * (float)Game.masterVolume);
							movementSoundIsPlaying = movementSoundWait;
						}
						playerPosX += (speed + boost);
					}
				}
			}
			
			return playerPosX;
			
		}
		
		//-----------------------------------------------------------------------------------------------------------------------------------
		// shooting
		
		public PlayerBullet controlShoot(Input input, double playerPosX, double playerPosY, double playerBulletSpeed, Character character)
		{
			if(character.fire == 0)																				// if ready to fire
			{
				if(input.isKeyDown(Input.KEY_Z))
				{
					PlayerBullet bullet = new PlayerBullet(playerPosX, playerPosY, 0, -playerBulletSpeed);
					Update.bulletsList.add(bullet);	
					Update.characterBulletsList.add(bullet);
					
					Random random = new Random();
					
					Play.pickCharacterFire().play((float)(8 + random.nextInt(4)) / 10, 0.8f * (float)Game.masterVolume);
					character.setFire(character.fireRate);														// set fire delay
					return bullet;
				}
				else
				{
					return null;
				}
			}
			else
			{
				character.setFire(character.getFire() - 1);
				return null;
			}
		}
	
	//----------------------------------------------------------------------------
	// setters and getters
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getBulletSpeed() {
		return bulletSpeed;
	}
	public void setBulletSpeed(double bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
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


	public double getPosX() {
		return posX;
	}


	public void setPosX(double posX) {
		this.posX = posX;
	}


	public double getPosY() {
		return posY;
	}


	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	public String hpToString()
	{
		return hp + "";
	}
	
}
