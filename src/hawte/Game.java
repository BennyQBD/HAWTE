package hawte;

import java.awt.*;

/**
 * The primary game interface of HAWTE
 */
public interface Game
{
	public void init();
	public void input(Input input);
	public void update(double delta);
	public void render(Graphics g);
}
