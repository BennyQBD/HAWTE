package game.RPG;

import game.RPG.components.RPGPlayer;
import game.RPG.components.RPGWall;
import hawte.Game;
import hawte.Transform;

/**
 * RPG Game class
 */
public class RPGGame extends Game
{
	@Override
	public void init()
	{
		RPGGrid grid = new RPGGrid(new Transform(), this).initGrid(64, 64);

		grid.addToGrid(new RPGGridObject(new Transform(), this).addComponent(new RPGWall()), 5, 1);

		grid.addToGrid(new RPGGridObject(new Transform(), this).addComponent(new RPGPlayer()).setDrawPriority(Integer.MAX_VALUE), 0, 0);
		addObject(grid);

		//saveScene("./res/rpg/world.scn");
	}
}
