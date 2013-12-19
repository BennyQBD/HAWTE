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
	public static final double AI_MOVE_SPEED = MOVE_SPEED * 1.3;

	private PongBall ball;

	@Override
	public void update(double delta)
	{
		if(ball == null)
		{
			GameComponent[] components = getGameObject().getGame().findComponents(PongBall.class);
			ball = (PongBall)components[0];
		}

		Vector2d directionToBall = ball.getTransform().getPos().sub(getTransform().getPos());

		if(Math.abs(directionToBall.getY()) > delta * 10)
		{
			if(directionToBall.getY() > 0)
				getVelocity().setY(AI_MOVE_SPEED);
			else
				getVelocity().setY(-AI_MOVE_SPEED);
		}
		else
			getVelocity().setY(ball.getVelocity().getY());

		if(getVelocity().getY() > AI_MOVE_SPEED)
			getVelocity().setY(AI_MOVE_SPEED);
		else if(getVelocity().getY() < -AI_MOVE_SPEED)
			getVelocity().setY(-AI_MOVE_SPEED);

//		double acceleration = directionToBall.getY() * DAMPING;
//
//		getVelocity().incY(acceleration);
//
//		if(getVelocity().getY() > AI_MOVE_SPEED)
//			getVelocity().setY(AI_MOVE_SPEED);
//		else if(getVelocity().getY() < -AI_MOVE_SPEED)
//			getVelocity().setY(-AI_MOVE_SPEED);
	}
}
