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

	@Override
	public void init(GameObject gameObject)
	{
		super.init(gameObject);
		getVelocity().setX(-MAX_SPEEDX);
	}

	@Override
	public void onCollision(Contact contact)
	{
		bounce(contact, 1.0, Math.toRadians(0));
	}
}
