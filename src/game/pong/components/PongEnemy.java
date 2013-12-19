package game.pong.components;

import hawte.GameComponent;
import hawte.GameObject;

/**
 * Created by batman_2 on 12/19/13.
 */
public class PongEnemy extends PongComponent
{
	private GameObject ball;

	@Override
	public void update(double delta)
	{
		if(ball == null)
		{
			GameComponent[] components = getGameObject().getGame().findComponents(PongBall.class);
			ball = components[0].getGameObject();
		}

		getGameObject().getTransform().getPos().setY(ball.getTransform().getPos().getY());
	}
}
