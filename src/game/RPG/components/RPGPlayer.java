package game.RPG.components;

import game.RPG.RPGGrid;
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

	@Override
	public void init(GameObject parent)
	{
		super.init(parent);
		setColor(Color.GREEN);
		moveDelay = new Delay(MOVE_DELAY_MILLI);
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

		if(object.getX() != newX)
			object.setX(newX);
		if(object.getY() != newY)
			object.setY(newY);
	}

	@Override
	public void render(Graphics g)
	{
		long currentTime = System.nanoTime();
		if(currentTime > (lastMoveTime + MOVE_DELAY_NANO))
			super.render(g);
		else
		{
			double lerpAmount = ((double)(currentTime - lastMoveTime))/((double) MOVE_DELAY_NANO);
			Vector2d pos = startCornerPos.lerp(getGridCornerPos(), lerpAmount);

			bindColor(g);
			g.fillRect((int) pos.getX(), (int) pos.getY(), RPGGrid.GRID_SPACE_SIZE, RPGGrid.GRID_SPACE_SIZE);
		}
	}
}
