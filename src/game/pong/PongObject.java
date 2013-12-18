package game.pong;

import hawte.GameObject;
import hawte.Transform;
import hawte.Vector2d;

import java.awt.*;

/**
 * Created by batman_2 on 12/18/13.
 */
public abstract class PongObject extends GameObject
{
	public PongObject(double posX, double posY, double sizeX, double sizeY)
	{
		super(new Transform(new Vector2d(posX, posY), new Vector2d(sizeX, sizeY), 0));
	}

	@Override
	public void render(Graphics g)
	{
		Vector2d pos = getTransform().getPos();
		Vector2d size = getTransform().getSize();

		//g.drawRoundRect((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY(), (int)(size.getX() / 2), (int)(size.getX() / 2));
		g.drawRect((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY());
	}
}
