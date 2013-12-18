package hawte;

import java.awt.*;

/**
 * Primary Game Object interface of HAWTE
 */
public abstract class GameObject
{
	public abstract void init();
	public abstract void input(Input input);
	public abstract void update(double delta);
	public abstract void render(Graphics g);
}
