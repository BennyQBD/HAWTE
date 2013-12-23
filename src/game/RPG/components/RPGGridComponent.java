package game.RPG.components;

import game.RPG.RPGGrid;
import game.RPG.RPGGridObject;
import hawte.GameComponent;
import hawte.GameObject;
import hawte.Vector2d;

import java.awt.*;

/**
 * RPG Grid Component
 */
public class RPGGridComponent extends GameComponent
{
	private Color color = Color.WHITE;

	public void setColor(Color color) { this.color = color; }

	@Override
	public RPGGridObject getGameObject()
	{
		return (RPGGridObject)super.getGameObject();
	}

	@Override
	public void init(GameObject parent)
	{
		assert(parent instanceof RPGGridObject);
		super.init(parent);
	}

	public void bindColor(Graphics g)
	{
		g.setColor(color);
	}

	public Vector2d getGridCornerPos()
	{
		RPGGridObject object = getGameObject();
		Vector2d pos = object.getTransform().getCornerPos();
		pos.addEq(object.getX() * RPGGrid.GRID_SPACE_SIZE, object.getY() * RPGGrid.GRID_SPACE_SIZE);
		return pos;
	}

	@Override
	public void render(Graphics g)
	{
		Vector2d pos = getGridCornerPos();
		bindColor(g);
		g.fillRect((int) pos.getX(), (int) pos.getY(), RPGGrid.GRID_SPACE_SIZE, RPGGrid.GRID_SPACE_SIZE);
	}
}