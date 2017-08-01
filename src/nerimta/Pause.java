package nerimta;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import nerimta.Game;

import org.lwjgl.input.Mouse;

public class Pause extends BasicGameState
{
	
	int choice;
	
	public Pause(int stateId)																									// constructor
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException											// initialization method
	{
		choice = 0;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException							// state render
	{
		g.drawString("Resume", 100, 200);
		g.drawString("Main menu", 100, 300);
		
		// current selection
		if(choice == 1)
		{
			g.drawString("---->", 50, 200);
		}
		if(choice == 2)
		{
			g.drawString("---->", 50, 300);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException							// render update
	{
		int xpos = Mouse.getX(); 
		int ypos = Mouse.getY();
		
		Input input = gc.getInput();
	
		// resume button
		if((xpos >= 90 && xpos <= 190) && (ypos >= 370 && ypos <= 400))
		{
			choice = 1;
			if(Mouse.isButtonDown(0))
			{
				sbg.enterState(Game.play);
			}
		}
		
		// main menu button
		if((xpos >= 90 && xpos <= 190) && (ypos >= 250 && ypos <= 300))
		{
			choice = 2;
			if(Mouse.isButtonDown(0))
			{
				sbg.getState(Game.menu).init(gc, sbg);
				sbg.enterState(Game.menu);
			}
		}
		
		// keyboard
		if(choice == 1)						// first item in menu selected
		{
			if(input.isKeyPressed(Input.KEY_ENTER))
			{
				sbg.enterState(Game.play);
			}
		}
				
		if(choice == 2)
		{
			if(input.isKeyPressed(Input.KEY_ENTER))
			{
				sbg.getState(Game.menu).init(gc, sbg);
				sbg.enterState(Game.menu);
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
		
		// escape button action
		if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			sbg.enterState(Game.play);
		}
		
		Game.soundChange(input);
		Game.fullscreenToggle(input, gc);
		
		
	}
	
	public int getID()
	{
		return Game.pause;
	}
}
