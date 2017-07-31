package nerimta;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import nerimta.Game;

import org.lwjgl.input.Mouse;

public class Pause extends BasicGameState
{
	public Pause(int stateId)																									// constructor
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException											// initialization method
	{
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException							// state render
	{
		g.drawString("Resume", 100, 200);
		g.drawString("Main menu", 100, 300);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException							// render update
	{
		int xpos = Mouse.getX(); 
		int ypos = Mouse.getY();
		
		Input input = gc.getInput();
	
		// resume button
		if((xpos >= 90 && xpos <= 190) && (ypos >= 370 && ypos <= 400))
		{
			if(Mouse.isButtonDown(0))
			{
				sbg.enterState(Game.play);
			}
		}
		
		// main menu button
		if((xpos >= 90 && xpos <= 190) && (ypos >= 250 && ypos <= 300))
		{
			if(Mouse.isButtonDown(0))
			{
				sbg.initStatesList(gc);					// reinitialize all states
				sbg.enterState(Game.menu);
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
