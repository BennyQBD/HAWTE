package game.RPG.components;

import game.RPG.RPGGridObject;
import hawte.Delay;
import hawte.GameObject;
import hawte.Input;
import hawte.Vector2d;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * RPG Player
 */
public class RPGPlayer extends RPGGridComponent
{
	public static final int MOVE_DELAY_MILLI = 200;
	public static final int MOVE_DELAY_NANO = MOVE_DELAY_MILLI * 1000000;
	private int newX = 0;
	private int newY = 0;
	private boolean moved = false;
	private Delay moveDelay;
	private long lastMoveTime = 0;
	private Vector2d startCornerPos;
	private Vector2d displayPos;

	@Override
	public void init(GameObject parent)
	{
		super.init(parent);
		setColor(Color.GREEN);
		moveDelay = new Delay(MOVE_DELAY_MILLI);
		displayPos = new Vector2d(0,0);
	}

	private void move(int x, int y)
	{
		if(moveDelay.isOver())
		{
			lastMoveTime = System.nanoTime();
			newY += y;
			newX += x;
			moved = true;
			startCornerPos = getGridCornerPos();
		}
	}

	@Override
	public void input(Input input)
	{
		RPGGridObject object = getGameObject();
		newX = object.getX();
		newY = object.getY();
		moved = false;

		if(input.getKey(KeyEvent.VK_W) || input.getKey(KeyEvent.VK_UP))
			move(0, -1);//newY--;
		if(input.getKey(KeyEvent.VK_S) || input.getKey(KeyEvent.VK_DOWN))
			move(0, 1);//newY++;
		if(input.getKey(KeyEvent.VK_A) || input.getKey(KeyEvent.VK_LEFT))
			move(-1, 0);//newX--;
		if(input.getKey(KeyEvent.VK_D) || input.getKey(KeyEvent.VK_RIGHT))
			move(1, 0);//newX++;

		if(moved && moveDelay.isOver())
			moveDelay.restart();
	}

	@Override
	public void update(double delta)
	{
		RPGGridObject object = getGameObject();

		if(object.getX() != newX || object.getY() != newY)
		{
			if(!getGameObject().getGrid().isBlocking(newX, newY))
			{
				object.setX(newX);
				object.setY(newY);
			}
			else if(!getGameObject().getGrid().isBlocking(getGameObject().getX(), newY))
				object.setY(newY);
			else if(!getGameObject().getGrid().isBlocking(newX, getGameObject().getY()))
				object.setX(newX);
		}

		calcDisplayPos();
	}

	private void calcDisplayPos()
	{
		long currentTime = System.nanoTime();
		if(currentTime > (lastMoveTime + MOVE_DELAY_NANO))
			displayPos = getGridCornerPos();
		else
		{
			double lerpAmount = ((double)(currentTime - lastMoveTime))/((double) MOVE_DELAY_NANO);
			displayPos = startCornerPos.lerp(getGridCornerPos(), lerpAmount);
		}

		Vector2d halfWindowSize = new Vector2d(getGameObject().getGame().getWidth() / 2, getGameObject().getGame().getHeight() / 2);
		getGameObject().getGrid().getTransform().setPos(displayPos.mul(-1).add(halfWindowSize));
	}

	@Override
	public void render(Graphics g)
	{
		drawAtGridPos(g, displayPos);
	}
}
