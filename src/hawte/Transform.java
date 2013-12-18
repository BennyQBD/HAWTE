package hawte;

/**
 * Created by batman_2 on 12/18/13.
 */
public class Transform
{
	private Vector2d pos;
	private Vector2d size;
	private float rotation;

	public Vector2d getPos() { return pos; }
	public Vector2d getSize() { return size; }
	public float getRotation() { return rotation; }

	public void setPos(Vector2d pos) { this.pos = pos; }
	public void setSize(Vector2d size) { this.size = size; }
	public void setRotation(float rotation) { this.rotation = rotation; }

	public Transform(Vector2d pos, Vector2d size, float rotation)
	{
		this.pos = pos;
		this.size = size;
		this.rotation = rotation;
	}
}
