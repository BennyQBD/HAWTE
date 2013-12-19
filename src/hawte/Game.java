package hawte;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The primary game interface of HAWTE.
 * Provides basic object list and updates with game clock
 */
public abstract class Game
{
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private int width;
	private int height;

	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public abstract void init();

	public void input(Input input)
	{
		for(GameObject gameObject : gameObjects)
			gameObject.input(input);
	}

	public void update(double delta)
	{
		for(GameObject gameObject : gameObjects)
			gameObject.update(delta);


		//Update physics simulation. Possibly move this to other method/class
		ArrayList<PhysicsComponent> physicsComponents = new ArrayList<PhysicsComponent>();

		for(GameObject gameObject : gameObjects)
		{
			for(int i = 0; i < gameObject.getNumComponents(); i++)
			{
				PhysicsComponent component;

				if(gameObject.getComponent(i) instanceof PhysicsComponent)
					component = (PhysicsComponent)gameObject.getComponent(i);
				else
					continue;

				component.integrate(delta);
				physicsComponents.add(component);
			}
		}

		for(int i = 0; i < physicsComponents.size(); i++)
		{
			PhysicsComponent component1 = physicsComponents.get(i);
			for(int j = i + 1; j < physicsComponents.size(); j++)
			{
				PhysicsComponent component2 = physicsComponents.get(j);

//				if(component1 == component2)
//					continue;

				Transform transform1 = component1.getGameObject().getTransform();
				Transform transform2 = component2.getGameObject().getTransform();

				Contact contact = transform1.checkBoxCollision(transform2);

				if(contact != null)
				{
					component1.onCollision(contact);
					component2.onCollision(contact);
				}
			}
		}
	}

	public void render(Graphics g)
	{
		for(GameObject gameObject : gameObjects)
			gameObject.render(g);
	}

	//TODO: At some point, make this scale with changes to the engine
	public void updateEngineParameters(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	public void addObject(GameObject object)
	{
		gameObjects.add(object);
	}

	public void saveScene(String filePath)
	{
		ArrayList<String> outputStrings = new ArrayList<String>();

		for(GameObject gameObject : gameObjects)
		{
			outputStrings.add("go " + gameObject.getTransform().toString());

			for(int i = 0; i < gameObject.getNumComponents(); i++)
			{
				outputStrings.add("cmp " + gameObject.getComponent(i).getClass().getCanonicalName());
			}
		}

		PrintWriter out = null;
		try
		{
			out = new PrintWriter(new FileWriter(filePath));

			for(String string : outputStrings)
				out.println(string);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
	}

	public void loadScene(String filePath)
	{
		gameObjects.clear();
		Scanner scan = null;
		try
		{
			scan = new Scanner(new File(filePath));

			GameObject gameObject = null;
			boolean readingTransform = false;
			boolean readingComponents = false;
			int transformReadPosition = 0;
			while(scan.hasNext())
			{
				String token = scan.next();

				if(token.equals("go"))
				{
					if(gameObject != null)
						gameObjects.add(gameObject);

					gameObject = new GameObject(new Transform(new Vector2d(0,0), new Vector2d(0,0), 0));
					readingTransform = true;
					readingComponents = false;
					transformReadPosition = 0;
					continue;
				}

				if(token.equals("cmp"))
				{
					readingTransform = false;
					readingComponents = true;
					continue;
				}

				if(readingComponents)
				{
					try
					{
						gameObject.addComponent((GameComponent)Class.forName(token).newInstance());
					}
					catch(InstantiationException e)
					{
						e.printStackTrace();
					}
					catch(IllegalAccessException e)
					{
						e.printStackTrace();
					}
					catch(ClassNotFoundException e)
					{
						e.printStackTrace();
					}
				}

				if(readingTransform)
				{
					double value = Double.parseDouble(token);
					Transform transform = gameObject.getTransform();

					switch(transformReadPosition)
					{
						case 0:
							transform.getPos().setX(value);
							break;
						case 1:
							transform.getPos().setY(value);
							break;
						case 2:
							transform.getScale().setX(value);
							break;
						case 3:
							transform.getScale().setY(value);
							break;
						case 4:
							transform.setRotation(value);
							break;
					}
					transformReadPosition++;
					if(transformReadPosition == 5)
						readingTransform = false;
				}
			}

			if(gameObject != null)
				gameObjects.add(gameObject);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			scan.close();
		}
	}
}
