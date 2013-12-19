package game.pong.components;

import hawte.*;

import java.awt.*;

/**
 * Created by batman_2 on 12/18/13.
 */
public class PongBall extends PongComponent
{
	public static final int SIZEX = 16;
	public static final int SIZEY = SIZEX;
	public static final double MAX_SPEEDX = 160;
	public static final int FONT_SIZE = 100;
	public static final int FONT_OFFSET_CONSTANT = (int)(FONT_SIZE * 0.6);
	public static final double BALL_CATCH_SCALE = 0.25;

	private Vector2d initialPosition;
	private Font scoreFont;
	private int playerScore;
	private int enemyScore;

	private void initState(double amt)
	{
		getVelocity().setX(MAX_SPEEDX * amt);
		getVelocity().setY(0);
		getGameObject().getTransform().setPos(new Vector2d(initialPosition));

		if(amt < 0)
			playerScore++;
		else
			enemyScore++;
	}

	@Override
	public void init(GameObject gameObject)
	{
		super.init(gameObject);
		initialPosition = new Vector2d(getGameObject().getTransform().getPos());
		initState(-1.0);
		playerScore = 0;
		enemyScore = 0;
	}

	@Override
	public void update(double delta)
	{
		Transform transform = getGameObject().getTransform();
		Game game = getGameObject().getGame();

		if(transform.getPos().getX() > game.getWidth())
			initState(-1.0);
		else if(transform.getPos().getX() < 0)
			initState(1.0);

		keepBallFromGlitchingPastTheWalls();
	}

	private void keepBallFromGlitchingPastTheWalls()
	{
		Transform transform = getGameObject().getTransform();
		Game game = getGameObject().getGame();

		//Catch the ball if it's out of screen
		if(transform.getPos().getY() > (game.getHeight() * (1.0 + BALL_CATCH_SCALE)) || transform.getPos().getY() < -game.getHeight() * BALL_CATCH_SCALE)
		{
			double initAmt = 1.0;
			int lastPlayerScore = playerScore;
			int lastEnemyScore = enemyScore;

			if(getVelocity().getX() < 0)
				initAmt *= -1;

			initState(initAmt);
			playerScore = lastPlayerScore;
			enemyScore = lastEnemyScore;
		}
	}

	@Override
	public void render(Graphics g)
	{
		if(scoreFont == null)
			scoreFont = new Font("Monospaced", 0, FONT_SIZE);
		g.setFont(scoreFont);

		String scoreText = playerScore + " " + enemyScore;
		//g.fillRect(getGameObject().getGame().getWidth() / 2 - 8, 0, 16, getGameObject().getGame().getHeight());
		g.drawString(scoreText, getGameObject().getGame().getWidth() / 2 - (scoreText.length() * FONT_OFFSET_CONSTANT) / 2, 100);

		super.render(g);
	}

	@Override
	public void onCollision(Contact contact, PhysicsComponent collidedWith)
	{
		double offsetAngle = 0;

		if(collidedWith instanceof PongPlayer || collidedWith instanceof PongEnemy)
		{
			Transform myTransform = getGameObject().getTransform();
			Transform otherTransform = collidedWith.getGameObject().getTransform();

			double difference = myTransform.getPos().getY() - otherTransform.getPos().getY();
			difference /= (PongPlayer.SIZEY / 2);

			offsetAngle = 45 * difference;
		}

		bounce(contact, 1.05, Math.toRadians(offsetAngle));
	}
}
