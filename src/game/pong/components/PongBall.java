package game.pong.components;

import hawte.*;

/**
 * Created by batman_2 on 12/18/13.
 */
public class PongBall extends PongComponent
{
	public static final int SIZEX = 16;
	public static final int SIZEY = SIZEX;
	public static final double MAX_SPEEDX = 160;
	private static final double MAX_SPEEDY = MAX_SPEEDX * 2;

	private Vector2d initialPosition;

	private void initState(double amt)
	{
		getVelocity().setX(MAX_SPEEDX * amt);
		getVelocity().setY(0);
		getGameObject().getTransform().setPos(new Vector2d(initialPosition));
	}

	@Override
	public void init(GameObject gameObject)
	{
		super.init(gameObject);
		initialPosition = new Vector2d(getGameObject().getTransform().getPos());
		initState(-1.0);
	}

	@Override
	public void update(double delta)
	{
		Transform transform = getGameObject().getTransform();
		Game game = getGameObject().getGame();

		if(transform.getPos().getX() > game.getWidth())
			initState(-1.0);
		else if(transform.getPos().getX() < 0)
			initState(1.0);
	}

	@Override
	public void onCollision(Contact contact, PhysicsComponent collidedWith)
	{
		double offsetAngle = 0;

		if(collidedWith instanceof PongPlayer)
		{
			Transform myTransform = getGameObject().getTransform();
			Transform otherTransform = collidedWith.getGameObject().getTransform();

			double difference = myTransform.getPos().getY() - otherTransform.getPos().getY();
			difference /= (PongPlayer.SIZEY / 2);

			offsetAngle = 45 * difference;
		}

		bounce(contact, 1.05, Math.toRadians(offsetAngle));
	}
}
