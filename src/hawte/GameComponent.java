package hawte;

import java.awt.*;

/**
 * Created by batman_2 on 12/18/13.
 */
public abstract class GameComponent
{
	private GameObject object;

	public GameObject getGameObject() { return object; }

	public void init(GameObject parent)
	{
		this.object = parent;
	}

	public abstract void input(Input input);
	public abstract void update(double delta);
	public abstract void render(Graphics g);
}
