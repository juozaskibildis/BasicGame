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
	
	int choice;
	
	public GameOver(int stateId)																									// constructor
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException											// initialization method
	{
		gameOverScreen = new Image("/res/gameOverScreen.png");
		
		choice = 0;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException							// state render
	{
		g.drawImage(gameOverScreen, 0, 0);
		
		g.drawString("Game over", 100, 200);
		g.drawString("Main menu", 100, 300);
		
		// current selection
		if(choice == 1)
		{
			g.drawString("---->", 50, 300);
		}		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException							// render update
	{
		int xpos = Mouse.getX(); 
		int ypos = Mouse.getY();
		
		Input input = gc.getInput();

		// main menu button
		if((xpos >= 90 && xpos <= 190) && (ypos >= 250 && ypos <= 300))
		{
			choice = 1;
			if(Mouse.isButtonDown(0))
			{
				sbg.getState(Game.menu).init(gc, sbg);
				sbg.enterState(Game.menu);
			}
			if(input.isKeyPressed(Input.KEY_Z))
			{
				sbg.getState(Game.menu).init(gc, sbg);
				sbg.enterState(Game.menu);
			}
		}
		
		if(choice == 1)						// first item in menu selected
		{
			if(input.isKeyPressed(Input.KEY_ENTER))
			{
				sbg.getState(Game.menu).init(gc, sbg);
				sbg.enterState(Game.menu);
			}
			if(input.isKeyPressed(Input.KEY_Z))
			{
				sbg.getState(Game.menu).init(gc, sbg);
				sbg.enterState(Game.menu);
			}
		}
		
		
		// arrow keys
		if(input.isKeyPressed(Input.KEY_DOWN))
		{
			if(choice < 1)
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
			sbg.getState(Game.menu).init(gc, sbg);
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
