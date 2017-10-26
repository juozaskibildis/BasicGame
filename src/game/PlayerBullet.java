package nerimta;

public class PlayerBullet extends Bullet
{
	public PlayerBullet(double bulletPosX, double bulletPosY, double speedX, double speedY) 
	{
		super(bulletPosX, bulletPosY, speedX, speedY);
		sizeX = 25;										// size should be size of image at all times
		sizeY = 25;										// otherwise all collision calculations get messed up
	}
}
