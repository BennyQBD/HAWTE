package game.pong;

import game.pong.components.PongBall;
import game.pong.components.PongComponent;
import game.pong.components.PongPlayer;
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
									  new PongBall()));

		this.addObject(new GameObject(new Transform(new Vector2d(PongPlayer.SIZEX / 2, this.getHeight() / 2),
													new Vector2d(PongPlayer.SIZEX / 2, PongPlayer.SIZEY / 2), 0),
									  new PongPlayer()));

		this.addObject(new GameObject(new Transform(new Vector2d(this.getWidth() - PongPlayer.SIZEX / 2, this.getHeight() / 2),
													new Vector2d(PongPlayer.SIZEX / 2, PongPlayer.SIZEY / 2), 0),
									  new PongComponent()));

		this.addObject(new GameObject(new Transform(new Vector2d(this.getWidth() / 2, PongBall.SIZEY / 2),
													new Vector2d(this.getWidth() / 2, PongBall.SIZEY / 2), 0),
									  new PongComponent()));

		this.addObject(new GameObject(new Transform(new Vector2d(this.getWidth() / 2, this.getHeight() - PongBall.SIZEY / 2),
													new Vector2d(this.getWidth() / 2, PongBall.SIZEY / 2), 0),
									  new PongComponent()));
//		saveScene("./res/main.scn");
//
//		loadScene("./res/main.scn");
	}
}
