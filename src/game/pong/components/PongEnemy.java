package game.pong.components;

import hawte.GameComponent;
import hawte.GameObject;
import hawte.Vector2d;

/**
 * Created by batman_2 on 12/19/13.
 */
public class PongEnemy extends PongPaddle
{
	public static final double DAMPING = 0.25;
	public static final double AI_MOVE_SPEED = MOVE_SPEED * 1.5;

	private GameObject ball;

	@Override
	public void update(double delta)
	{
		if(ball == null)
		{
			GameComponent[] components = getGameObject().getGame().findComponents(PongBall.class);
			ball = components[0].getGameObject();
		}

		Vector2d directionToBall = ball.getTransform().getPos().sub(getTransform().getPos());

//		double distance = directionToBall.length();
//		if(distance < 0)
//			distance = 0.0001;
		double acceleration = directionToBall.getY() * DAMPING; /// (distance * DAMPING);

		getVelocity().incY(acceleration);

		if(getVelocity().getY() > AI_MOVE_SPEED)
			getVelocity().setY(AI_MOVE_SPEED);
		else if(getVelocity().getY() < -AI_MOVE_SPEED)
			getVelocity().setY(-AI_MOVE_SPEED);

		//getGameObject().getTransform().getPos().setY(ball.getTransform().getPos().getY());
	}
}
