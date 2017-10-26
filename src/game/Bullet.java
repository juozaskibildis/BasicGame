package nerimta;

public class Bullet 
{
	
	// speed and angle
	double speedX;
	double speedY;
	
	// location
	double posX;
	double posY;
	
	double sizeX;	// currently not used for simplicity`s sake
	double sizeY;
	
	
	
	//------------------------------------------------------------------------
	// methods
	
	// constructor
	public Bullet(double bulletPosX, double bulletPosY, double speedX, double speedY)
	{
		setBulletPosX(bulletPosX + speedX);				// + speed so that bullets appear one frame away from player
		setBulletPosY(bulletPosY + speedY);
		setSpeedX(speedX);
		setSpeedY(speedY);
	}
	
	// position updates
	public double updateX()
	{
		setBulletPosX(getBulletPosX() + getSpeedX());
		return posX;
	}
	
	public double updateY()
	{
		setBulletPosY(getBulletPosY() + getSpeedY());
		return posY;
	}
	
	//--------------------------------------------------------------
	// getters and setters

	public double getSpeedX() {
		return speedX;
	}


	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}


	public double getSpeedY() {
		return speedY;
	}


	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}


	public double getBulletPosX() {
		return posX;
	}


	public void setBulletPosX(double bulletPosX) {
		this.posX = bulletPosX;
	}


	public double getBulletPosY() {
		return posY;
	}


	public void setBulletPosY(double bulletPosY) {
		this.posY = bulletPosY;
	}
}
