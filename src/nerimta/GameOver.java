package nerimta;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;

public class GameOver extends BasicGameState
{
	Image gameOverScreen;
	
	public GameOver(int stateId)																									// constructor
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException											// initialization method
	{
		gameOverScreen = new Image("/res/gameOverScreen.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException							// state render
	{
		g.drawImage(gameOverScreen, 0, 0);
		
		g.drawString("Game over", 100, 200);
		g.drawString("Main menu", 100, 300);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException							// render update
	{
		int xpos = Mouse.getX(); 
		int ypos = Mouse.getY();
		
		Input input = gc.getInput();

		// main menu button
		if((xpos >= 90 && xpos <= 190) && (ypos >= 250 && ypos <= 300))
		{
			if(Mouse.isButtonDown(0))
			{
				sbg.initStatesList(gc);			// run initialization method of all states
				sbg.enterState(Game.menu);
			}
		}
		
		// escape button action
		if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			sbg.initStatesList(gc);				// run initialization method of all states
			sbg.enterState(Game.menu);
		}
		
		Game.soundChange(input);
		Game.fullscreenToggle(input, gc);
		
	}
	
	public int getID()
	{
		return Game.gameOver;
	}
}
