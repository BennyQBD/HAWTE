package game.pong.components;

import hawte.GameObject;
import hawte.Input;
import hawte.Transform;
import hawte.Vector2d;

/**
 * Created by batman_2 on 12/18/13.
 */
public class PongBall extends PongComponent
{
	public static final int SIZEX = 16;
	public static final int SIZEY = SIZEX;
	public static final double MAX_SPEEDX = 160;
	private static final double MAX_SPEEDY = MAX_SPEEDX * 2;

	private Vector2d velocity;

	@Override
	public void init(GameObject gameObject)
	{
		super.init(gameObject);
		velocity = new Vector2d(-MAX_SPEEDX, 0);
	}

	@Override
	public void input(Input input)
	{

	}

	@Override
	public void update(double delta)
	{
		Transform transform = getGameObject().getTransform();

		transform.setPos(transform.getPos().add(velocity.mul(delta)));
	}
}
