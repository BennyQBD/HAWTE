package game;

import hawte.Game;
import hawte.GameEngine;
import hawte.Input;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main implements Game
{
	private BufferedImage image;
	private int textX;
	private int textY;

	@Override
	public void init()
	{
		textX = 100;
		textY = 100;

		try
		{
			image = ImageIO.read(new File("./res/test.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void input(Input input)
	{
		if(input.getKey(KeyEvent.VK_UP))
			textY--;
		if(input.getKey(KeyEvent.VK_DOWN))
			textY++;
		if(input.getKey(KeyEvent.VK_LEFT))
			textX--;
		if(input.getKey(KeyEvent.VK_RIGHT))
			textX++;
	}

	@Override
	public void update(double delta)
	{

	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(image, 0,0, null);

		g.setColor(Color.BLUE);
		g.setFont(new Font("Monospaced", 0, 100));
		g.drawString("Hello World!", textX, textY);
	}

	public static void main(String[] args)
	{
		GameEngine engine = new GameEngine(800, 600, 60, new Main());
		engine.createWindow("My Game!");
		engine.start();
	}
}
