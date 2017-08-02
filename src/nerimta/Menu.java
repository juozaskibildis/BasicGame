package nerimta;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import nerimta.Game;

import org.lwjgl.input.Mouse;								// for mouse coordinates

public class Menu extends BasicGameState
{
	Image menuBackground;
	
	int choice;
	
	public Menu(int stateId)																									// constructor
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException											// initialization method
	{
		menuBackground = new Image("/res/menuBackground.png");
		
		choice = 0;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException							// state render
	{
		g.drawImage(menuBackground, 0, 0);
																				
		g.drawString("!!!!!", 300, 100);
		g.drawString("Play!", 300, 200);
		g.drawString("Exit", 300, 300);
		
		// current selection
		if(choice == 1)
		{
			g.drawString("---->", 250, 200);
		}
		if(choice == 2)
		{
			g.drawString("---->", 250, 300);
		}
		
		
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
			choice = 1;
			if(Mouse.isButtonDown(0))
			{
				// hide mouse cursor
				gc.setMouseCursor(Play.blankImage, 0, 0);
				sbg.getState(Game.play).init(gc, sbg);
				sbg.enterState(Game.play);
			}
		}
		
		// exit button
		if((xpos >= 290 && xpos <= 390) && (ypos >= 250 && ypos <= 300))
		{
			choice = 2;
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
		
		// keyboard
		if(choice == 1)						// first item in menu selected
		{
			if(input.isKeyPressed(Input.KEY_ENTER))
			{
				// hide mouse cursor
				gc.setMouseCursor(Play.blankImage, 0, 0);
				sbg.getState(Game.play).init(gc, sbg);
				sbg.enterState(Game.play);
			}
			if(input.isKeyPressed(Input.KEY_Z))
			{
				// hide mouse cursor
				gc.setMouseCursor(Play.blankImage, 0, 0);
				sbg.getState(Game.play).init(gc, sbg);
				sbg.enterState(Game.play);
			}
		}
		
		if(choice == 2)
		{
			if(input.isKeyPressed(Input.KEY_ENTER))
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
			if(input.isKeyPressed(Input.KEY_Z))
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
		
		// arrow keys
		if(input.isKeyPressed(Input.KEY_DOWN))
		{
			if(choice < 2)
			{
				choice++;
			}
		}
		
		if(input.isKeyPressed(Input.KEY_UP))
		{
			if(choice > 1)
			{
				choice--;
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
