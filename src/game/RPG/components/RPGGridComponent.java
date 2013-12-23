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

	@Override
	public void render(Graphics g)
	{
		Vector2d pos = getGameObject().getTransform().getCornerPos();
		g.setColor(color);
		g.fillRect((int) pos.getX(), (int) pos.getY(), RPGGrid.GRID_SPACE_SIZE, RPGGrid.GRID_SPACE_SIZE);
	}
}
