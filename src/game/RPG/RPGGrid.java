package game.RPG;

import hawte.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * RPG Grid
 */
public class RPGGrid extends GameObject
{
	public static final int GRID_SPACE_SIZE = 32;
	private ArrayList<RPGGridObject>[][] gridObjects;

	public int getSizeX() { return gridObjects.length; }
	public int getSizeY() { return gridObjects[0].length; }

	public RPGGrid(Transform transform, Game game)
	{
		super(transform, game);
	}

	public RPGGrid initGrid(int sizeX, int sizeY)
	{
		gridObjects = new ArrayList[sizeX][sizeY];
		for(int i = 0; i < getSizeX(); i++)
			for(int j = 0; j < getSizeY(); j++)
				gridObjects[i][j] = new ArrayList<RPGGridObject>();

		return this;
	}

	public void addToGrid(RPGGridObject object, int x, int y)
	{
		object.setParent(this, x, y);
		gridObjects[x][y].add(object);
	}

	public void removeFromGrid(RPGGridObject object, int x, int y)
	{
		gridObjects[x][y].remove(object);
	}

	public boolean isBlocking(int x, int y)
	{
		if(x < 0 || x >= getSizeX() || y < 0 || y >= getSizeY())
			return true;

		for(RPGGridObject object : gridObjects[x][y])
			if(object.isBlocking())
				return true;

		return false;
	}

	@Override
	public void input(Input input)
	{
		super.input(input);

		ArrayList<RPGGridObject> inputObjects = new ArrayList<RPGGridObject>();

		for(ArrayList<RPGGridObject>[] xArray : gridObjects)
		{
			for(ArrayList<RPGGridObject> gridObjectArrayList : xArray)
			{
				for(RPGGridObject gridObject : gridObjectArrayList)
					inputObjects.add(gridObject);
			}
		}

		for(RPGGridObject object : inputObjects)
			object.input(input);
	}

	@Override
	public void update(double delta)
	{
		super.update(delta);
		ArrayList<RPGGridObject> updateObjects = new ArrayList<RPGGridObject>();

		for(ArrayList<RPGGridObject>[] xArray : gridObjects)
		{
			for(ArrayList<RPGGridObject> gridObjectArrayList : xArray)
			{
				for(RPGGridObject gridObject : gridObjectArrayList)
					updateObjects.add(gridObject);
			}
		}

		for(RPGGridObject object : updateObjects)
			object.update(delta);
	}

	@Override
	public void render(Graphics g)
	{
		super.render(g);

		ArrayList<RPGGridObject> objects = new ArrayList<RPGGridObject>();

		for(ArrayList<RPGGridObject>[] xArray : gridObjects)
			for(ArrayList<RPGGridObject> gridObjectArrayList : xArray)
				for(RPGGridObject gridObject : gridObjectArrayList)
					objects.add(gridObject);

		Collections.sort(objects);

		for(RPGGridObject gridObject : objects)
		{
			gridObject.setRenderOffset(getTransform().getPos());
			gridObject.render(g);
		}
	}
}
