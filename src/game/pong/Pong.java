package game.pong;

import hawte.Game;

/**
 * Game of Pong for HAWTE
 */
public class Pong extends Game
{
	@Override
	public void init()
	{
		this.addObject(new PongBall(this.getWidth() / 2 - PongBall.SIZE / 2,
									this.getHeight() / 2 - PongBall.SIZE / 2));

		this.addObject(new PongPlayer(0, this.getHeight() / 2 - PongPlayer.SIZEY / 2));
	}
}
