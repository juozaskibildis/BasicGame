package nerimta;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import nerimta.Game;

import org.lwjgl.input.Mouse;								// for mouse coordinates

public class Menu extends BasicGameState
{
	Image menuBackground;
	
	public Menu(int stateId)																									// constructor
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException											// initialization method
	{
		menuBackground = new Image("/res/menuBackground.png");
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException							// state render
	{
		g.drawImage(menuBackground, 0, 0);
																				
		g.drawString("!!!!!", 300, 100);
		g.drawString("Play!", 300, 200);
		g.drawString("Exit", 300, 300);
		
		g.drawString("'z' -> shoot", 450, 25);
		g.drawString("'arrow keys' -> move", 450, 125);
		g.drawString("'shift' -> focus", 450, 225);
		g.drawString("'.' -> volume up", 450, 325);
		g.drawString("',' -> volume down", 450, 425);
		g.drawString("'F11' -> fullscreen", 450, 525);
		
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException							// render update
	{
		// user input
		int xpos = Mouse.getX(); 
		int ypos = Mouse.getY();

		Input input = gc.getInput();
		
		// play button
		if((xpos >= 290 && xpos <= 390) && (ypos >= 370 && ypos <= 400))
		{
			if(Mouse.isButtonDown(0))
			{
				sbg.getState(Game.play).init(gc, sbg);
				sbg.enterState(Game.play);
			}
		}
		
		// exit button
		if((xpos >= 290 && xpos <= 390) && (ypos >= 250 && ypos <= 300))
		{
			if(Mouse.isButtonDown(0))
			{
				if(Game.fullscreen == true)
				{
					Game.fullscreen = false;
					try
					{
						gc.setFullscreen(Game.fullscreen);	// quitting full screen seems to help with the game not closing completely
					}
					catch(SlickException e)
					{
						e.printStackTrace();
					}
				}
				gc.exit();
			}
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			if(Game.fullscreen == true)
			{
				Game.fullscreen = false;
				try
				{
					gc.setFullscreen(Game.fullscreen);	// quitting full screen seems to help with the game not closing completely
				}
				catch(SlickException e)
				{
					e.printStackTrace();
				}		
			}
			gc.exit();
		}
		
		Game.soundChange(input);
		Game.fullscreenToggle(input, gc);
		
	}
	
	public int getID()
	{
		return Game.menu;
	}
	
}
