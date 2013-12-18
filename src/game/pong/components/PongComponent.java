package game.pong.components;

import hawte.GameComponent;
import hawte.Input;
import hawte.Vector2d;

import java.awt.*;

/**
 * Created by batman_2 on 12/18/13.
 */
public abstract class PongComponent extends GameComponent
{
	@Override
	public void render(Graphics g)
	{
		Vector2d pos = getGameObject().getTransform().getPos();
		Vector2d size = getGameObject().getTransform().getSize();

		//g.drawRoundRect((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY(), (int)(size.getX() / 2), (int)(size.getX() / 2));
		g.drawRect((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY());
	}
}
