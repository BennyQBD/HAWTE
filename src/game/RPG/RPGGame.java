package game.RPG;

import game.RPG.components.RPGPlayer;
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

		grid.addToGrid(new RPGGridObject(new Transform(), this).addComponent(new RPGPlayer()), 0, 0);

		addObject(grid);
	}
}
