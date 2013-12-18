package game.pong;

import hawte.GameObject;
import hawte.Vector2d;

import java.awt.*;

/**
 * Created by batman_2 on 12/18/13.
 */
public abstract class PongObject extends GameObject
{
	private Vector2d pos;
	private Vector2d size;

	public Vector2d getPos() { return pos; }
	public Vector2d getSize() { return size; }

	public void setPos(Vector2d pos) { this.pos = pos; }
	public void setSize(Vector2d size) { this.size = size; }

	public double getCenterY() { return pos.getY() + size.getY() / 2; }

	public PongObject(double posX, double posY, double sizeX, double sizeY)
	{
		pos = new Vector2d(posX, posY);
		size = new Vector2d(sizeX, sizeY);
	}

	@Override
	public void render(Graphics g)
	{
		//g.drawRoundRect((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY(), (int)(size.getX() / 2), (int)(size.getX() / 2));
		g.drawRect((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY());
	}
}
