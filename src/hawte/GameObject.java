package hawte;

import java.awt.*;
import java.util.ArrayList;

/**
 * Primary Game Object of HAWTE
 */
public class GameObject
{
	private ArrayList<GameComponent> components;
	private Transform transform;

	public int getNumComponents() { return components.size(); }
	public GameComponent getComponent(int index) { return components.get(index); }
	public Transform getTransform() { return transform; }

	public GameObject(Transform transform)
	{
		components = new ArrayList<GameComponent>();
		this.transform = transform;
	}

	public GameObject(Transform transform, GameComponent component)
	{
		components = new ArrayList<GameComponent>();
		this.transform = transform;
		addComponent(component);
	}

	public void addComponent(GameComponent component)
	{
		component.init(this);
		components.add(component);
	}

	public void input(Input input)
	{
		for(GameComponent component : components)
			component.input(input);
	}

	public void update(double delta)
	{
		for(GameComponent component : components)
			component.update(delta);
	}

	public void render(Graphics g)
	{
		for(GameComponent component : components)
			component.render(g);
	}
}
