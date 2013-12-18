package hawte;

import java.awt.*;

/**
 * Primary Game Object interface of HAWTE
 */
public abstract class GameObject
{
	private Transform transform;

	public Transform getTransform() { return transform; }

	public GameObject(Transform transform)
	{
		this.transform = transform;
	}

	public abstract void input(Input input);
	public abstract void update(double delta);
	public abstract void render(Graphics g);
}
