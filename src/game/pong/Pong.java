package game.pong;

import game.pong.components.PongBall;
import game.pong.components.PongPlayer;
import hawte.Game;
import hawte.GameObject;
import hawte.Transform;
import hawte.Vector2d;

/**
 * Game of Pong for HAWTE
 */
public class Pong extends Game
{
	@Override
	public void init()
	{
		this.addObject(new GameObject(new Transform(new Vector2d(this.getWidth() / 2 - PongBall.SIZEX / 2,
																 this.getHeight() / 2 - PongBall.SIZEY / 2),
													new Vector2d(PongBall.SIZEX, PongBall.SIZEY), 0),
									  new PongBall()));

		this.addObject(new GameObject(new Transform(new Vector2d(0, this.getHeight() / 2 - PongPlayer.SIZEY / 2),
													new Vector2d(PongPlayer.SIZEX, PongPlayer.SIZEY), 0),
									  new PongPlayer()));
	}
}
