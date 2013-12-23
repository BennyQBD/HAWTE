package game.RPG;

import hawte.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * RPG Grid
 */
public class RPGGrid extends GameObject
{
	public static final int GRID_SPACE_SIZE = 32;
	private RPGGridObject[][] gridObjects;

	public int getSizeX() { return gridObjects.length; }
	public int getSizeY() { return gridObjects[0].length; }

	public RPGGrid(Transform transform, Game game)
	{
		super(transform, game);
	}

	public RPGGrid initGrid(int sizeX, int sizeY)
	{
		gridObjects = new RPGGridObject[sizeX][sizeY];
		return this;
	}

	public void addToGrid(RPGGridObject object, int x, int y)
	{
		object.setParent(this, x, y);
		gridObjects[x][y] = object;
	}

	public void removeFromGrid(int x, int y)
	{
		gridObjects[x][y] = null;
	}

//	public Vector2d getObjectPos(RPGGridObject object)
//	{
//		for(int i = 0; i < gridObjects.length; i++)
//			for(int j = 0; j < gridObjects[0].length; j++)
//				if(gridObjects[i][j] == object) return new Vector2d(i, j);
//
//		return null;
//	}

	@Override
	public void input(Input input)
	{
		super.input(input);

		//ArrayList<RPGGridObject> inputObjects = new ArrayList<RPGGridObject>();

		for(RPGGridObject[] xArray : gridObjects)
		{
			for(RPGGridObject gridObject : xArray)
			{
				if(gridObject == null) continue;
				gridObject.input(input);//inputObjects.add(gridObject);
			}
		}

//		for(RPGGridObject object : inputObjects)
//			object.input(input);
	}

	@Override
	public void update(double delta)
	{
		super.update(delta);
		ArrayList<RPGGridObject> updateObjects = new ArrayList<RPGGridObject>();

		for(RPGGridObject[] xArray : gridObjects)
		{
			for(RPGGridObject gridObject : xArray)
			{
				if(gridObject == null) continue;
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
		Vector2d posOffset = new Vector2d(0,0);

		for(RPGGridObject[] xArray : gridObjects)
		{
			for(RPGGridObject gridObject : xArray)
			{
				if(gridObject == null) continue;

				Transform transform = gridObject.getTransform();
				Vector2d oldGameObjectPos = transform.getPos();

				posOffset.set(gridObject.getX() * GRID_SPACE_SIZE, gridObject.getY() * GRID_SPACE_SIZE);
				transform.setPos(oldGameObjectPos.add(posOffset));

				gridObject.render(g);

				transform.setPos(oldGameObjectPos);
			}
		}
	}
}
