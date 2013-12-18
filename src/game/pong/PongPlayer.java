package game.pong;

import hawte.Input;

import java.awt.event.KeyEvent;

/**
 * Created by batman_2 on 12/18/13.
 */
public class PongPlayer extends PongObject
{
	public static final double SIZEX = 16;
	public static final double SIZEY = SIZEX * 7;
	public static final double MOVE_SPEED = 160;

	public PongPlayer(double posX, double posY)
	{
		super(posX, posY, SIZEX, SIZEY);
	}

	private void move(double mag)
	{
		getTransform().getPos().setY(getTransform().getPos().getY() + mag );
	}

	@Override
	public void input(Input input)
	{
		if(input.getKey(KeyEvent.VK_W) || input.getKey(KeyEvent.VK_UP))
			this.move(MOVE_SPEED * -input.getDelta());
		if(input.getKey(KeyEvent.VK_S) || input.getKey(KeyEvent.VK_DOWN))
			this.move(MOVE_SPEED * input.getDelta());
	}

	@Override
	public void update(double delta)
	{

	}
}
