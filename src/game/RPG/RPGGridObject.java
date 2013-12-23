package game.RPG;

import hawte.*;

import java.awt.*;

/**
 * RPG grid object
 */
public class RPGGridObject extends GameObject implements Comparable
{
	private RPGGrid grid;
	private Vector2d renderOffset;
	private int x;
	private int y;
	private int drawPriority = Integer.MIN_VALUE;
	private boolean isBlocking = false;

	public RPGGrid getGrid() { return grid; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getDrawPriority() { return drawPriority; }
	public boolean isBlocking() { return isBlocking; }

	public void setParent(RPGGrid grid, int x, int y) {this.grid = grid; this.x = x; this.y = y;}
	public void setRenderOffset(Vector2d offset) { this.renderOffset = offset; }
	public void setBlocking(boolean value) { this.isBlocking = value; }

	public RPGGridObject(Transform transform, Game game)
	{
		super(transform, game);
	}

	@Override
	public RPGGridObject addComponent(GameComponent component)
	{
		return (RPGGridObject)super.addComponent(component);
	}

	public RPGGridObject setDrawPriority(int value)
	{
		drawPriority = value;
		return this;
	}

	public void setX(int x)
	{
		if(x < 0 || x >= grid.getSizeX())
			return;

		grid.removeFromGrid(this, this.x, this.y);
		grid.addToGrid(this, x, y);
	}

	public void setY(int y)
	{
		if(y < 0 || y >= grid.getSizeY())
			return;

		grid.removeFromGrid(this, this.x, this.y);
		grid.addToGrid(this, x, y);
	}

	public void fillOffsetRect(Graphics g, int x, int y, int width, int height)
	{
		g.fillRect((int)(x + renderOffset.getX()), (int)(y + renderOffset.getY()), width, height);
	}

	@Override
	public int compareTo(Object o)
	{
		return ((RPGGridObject)o).getDrawPriority() - drawPriority;
	}
}
