package nerimta;

public class EnemyBullet extends Bullet
{
	public EnemyBullet(double bulletPosX, double bulletPosY, double speedX, double speedY) 
	{
		super(bulletPosX, bulletPosY, speedX, speedY);
		sizeX = 25;										// size of an image
		sizeY = 25;
	}
}
