package hawte;

/**
 * Created by batman_2 on 12/18/13.
 */
public class Transform
{
	private Vector2d pos;
	private Vector2d size;
	private double rotation;

	public Vector2d getPos() { return pos; }
	public Vector2d getSize() { return size; }
	public double getRotation() { return rotation; }

	public void setPos(Vector2d pos) { this.pos = pos; }
	public void setSize(Vector2d size) { this.size = size; }
	public void setRotation(double rotation) { this.rotation = rotation; }

	public Transform(Vector2d pos, Vector2d size, double rotation)
	{
		this.pos = pos;
		this.size = size;
		this.rotation = rotation;
	}

	public Vector2d getTransformedPosition()
	{
		return pos.rotate(rotation);
	}

	public Vector2d getCenter()
	{
		return new Vector2d(pos.getX() + size.getX() / 2, pos.getY() + size.getY() / 2);
	}
}
