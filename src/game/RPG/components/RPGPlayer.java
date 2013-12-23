package game.RPG.components;

import game.RPG.RPGGridObject;
import hawte.GameObject;
import hawte.Input;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * RPG Player
 */
public class RPGPlayer extends RPGGridComponent
{
	@Override
	public void init(GameObject parent)
	{
		super.init(parent);
		setColor(Color.GREEN);
	}

	private int newX = 0;
	private int newY = 0;

	@Override
	public void input(Input input)
	{
		RPGGridObject object = getGameObject();
		newX = object.getX();
		newY = object.getY();

		if(input.getKey(KeyEvent.VK_W) || input.getKey(KeyEvent.VK_UP))
			newY--;
		if(input.getKey(KeyEvent.VK_S) || input.getKey(KeyEvent.VK_DOWN))
			newY++;
		if(input.getKey(KeyEvent.VK_A) || input.getKey(KeyEvent.VK_LEFT))
			newX--;
		if(input.getKey(KeyEvent.VK_D) || input.getKey(KeyEvent.VK_RIGHT))
			newX++;
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
}
