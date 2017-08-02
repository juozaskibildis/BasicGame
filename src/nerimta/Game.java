package nerimta;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Game extends StateBasedGame
{
	public static final String GAMENAME = "!";
	public static final int menu = 0;				// state id
	public static final int play = 1;	
	public static final int pause = 2;
	public static final int gameOver = 3;
	
	// options
	public static int resolutionPreset = 0;
	public static int resolutionX = 800;			// later I should make it 720p at least
	public static int resolutionY = 600;
	public static boolean fullscreen = false;
	public static double masterVolume = 1;
	
	public Game(String GAMENAME)
	{
		super(GAMENAME);							// window name
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new Pause(pause));
		this.addState(new GameOver(gameOver));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException
	{
		this.getState(menu).init(gc, this);			// initialize state
		this.getState(play).init(gc, this);			// initialize state
		this.getState(pause).init(gc, this);
		this.getState(gameOver).init(gc, this);
		this.enterState(menu);							// start state
	}
	
	public static void main(String[] args)
	{
		AppGameContainer appgc;
		try
		{
			appgc = new AppGameContainer(new Game(GAMENAME));						// creates game window
			appgc.setDisplayMode(resolutionX, resolutionY, fullscreen);	
			appgc.setTargetFrameRate(60);											// frame rate lock
			appgc.start();
			
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------
	// methods
	
	public static void soundChange(Input input)
	{
		if(input.isKeyPressed(Input.KEY_PERIOD))
		{
			if(masterVolume < 0.8)
			{
				masterVolume += 0.2;
			}
		}
		if(input.isKeyPressed(Input.KEY_COMMA))
		{
			if(masterVolume > 0.2)
			{
				masterVolume -= 0.2;
			}
		}
	}
	
	public static void fullscreenToggle(Input input, GameContainer gc)
	{
		if(input.isKeyPressed(Input.KEY_F11))
		{
			if(fullscreen == false)
			{
				fullscreen = true;
				try
				{
					gc.setFullscreen(fullscreen);
				}
				catch(SlickException e)
				{
					e.printStackTrace();
				}
			}
			else
			if(fullscreen == true)
			{
				fullscreen = false;
				try
				{
					gc.setFullscreen(fullscreen);
				}
				catch(SlickException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
