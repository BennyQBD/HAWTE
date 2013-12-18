package hawte;

import java.awt.*;
import java.util.ArrayList;

/**
 * The primary game interface of HAWTE.
 * Provides basic object list and updates with game clock
 */
public abstract class Game
{
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private int width;
	private int height;
	private double frameRate;

	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public double getFrameRate() { return frameRate; }

	public abstract void initGame();

	public void init()
	{
		initGame();

		for(GameObject gameObject : gameObjects)
			gameObject.init();
	}

	public void input(Input input)
	{
		for(GameObject gameObject : gameObjects)
			gameObject.input(input);
	}

	public void update(double delta)
	{
		for(GameObject gameObject : gameObjects)
			gameObject.update(delta);
	}

	public void render(Graphics g)
	{
		for(GameObject gameObject : gameObjects)
			gameObject.render(g);
	}

	//TODO: At some point, make this scale with changes to the engine
	public void updateEngineParameters(int width, int height, double frameRate)
	{
		this.width = width;
		this.height = height;
		this.frameRate = frameRate;
	}

	public void addObject(GameObject object)
	{
		gameObjects.add(object);
	}
}
