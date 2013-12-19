package game.pong;

import game.pong.components.*;
import hawte.Game;
import hawte.GameObject;
import hawte.Transform;
import hawte.Vector2d;

/**
 * Game of Pong for HAWTE
 */
public class Pong extends Game
{
	@Override
	public void init()
	{
		this.addObject(new GameObject(new Transform(new Vector2d(this.getWidth() / 2,this.getHeight() / 2),
													new Vector2d(PongBall.SIZEX / 2, PongBall.SIZEY / 2), 0),
									  new PongBall(), this));

		this.addObject(new GameObject(new Transform(new Vector2d(PongPaddle.SIZEX / 2, this.getHeight() / 2),
													new Vector2d(PongPaddle.SIZEX / 2, PongPaddle.SIZEY / 2), 0),
									  new PongPlayer(), this));

		this.addObject(new GameObject(new Transform(new Vector2d(this.getWidth() - PongPaddle.SIZEX / 2, this.getHeight() / 2),
													new Vector2d(PongPaddle.SIZEX / 2, PongPaddle.SIZEY / 2), 0),
									  new PongEnemy(), this));

		this.addObject(new GameObject(new Transform(new Vector2d(this.getWidth() / 2, PongBall.SIZEY / 2),
													new Vector2d(this.getWidth() / 2, PongBall.SIZEY / 2), 0),
									  new PongWall(), this));

		this.addObject(new GameObject(new Transform(new Vector2d(this.getWidth() / 2, this.getHeight() - PongBall.SIZEY / 2),
													new Vector2d(this.getWidth() / 2, PongBall.SIZEY / 2), 0),
									  new PongWall(), this));
//		saveScene("./res/main.scn");
//
//		loadScene("./res/main.scn");
	}
}
