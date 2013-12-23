package game.RPG;

import hawte.Game;
import hawte.GameComponent;
import hawte.GameObject;
import hawte.Transform;

/**
 * RPG grid object
 */
public class RPGGridObject extends GameObject
{
	private RPGGrid grid;
	private int x;
	private int y;

	public RPGGridObject(Transform transform, Game game)
	{
		super(transform, game);
	}

	@Override
	public RPGGridObject addComponent(GameComponent component)
	{
		return (RPGGridObject)super.addComponent(component);
	}

	public void setParent(RPGGrid grid, int x, int y) {this.grid = grid; this.x = x; this.y = y;}

	public int getX() { return x; }
	public int getY() { return y; }

	public void setX(int x)
	{
		if(x < 0 || x >= grid.getSizeX())
			return;

		grid.removeFromGrid(this.x, this.y);
		grid.addToGrid(this, x, y);
	}

	public void setY(int y)
	{
		if(y < 0 || y >= grid.getSizeY())
			return;

		grid.removeFromGrid(this.x, this.y);
		grid.addToGrid(this, x, y);
	}
}
