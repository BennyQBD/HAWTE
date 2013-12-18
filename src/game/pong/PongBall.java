package game.pong;

import hawte.Input;
import hawte.Vector2d;

/**
 * Created by batman_2 on 12/18/13.
 */
public class PongBall extends PongObject
{
	public static final int SIZE = 16;
	public static final double MAX_SPEEDX = 160;
	private static final double MAX_SPEEDY = MAX_SPEEDX * 2;

	private Vector2d velocity;

	public PongBall(double posX, double posY)
	{
		super(posX, posY, SIZE, SIZE);
	}

	@Override
	public void init()
	{
		velocity = new Vector2d(-MAX_SPEEDX, 0);
	}

	@Override
	public void input(Input input)
	{

	}

	@Override
	public void update(double delta)
	{
		setPos(getPos().add(velocity.mul(delta)));
	}
}
