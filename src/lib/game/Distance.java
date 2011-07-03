package lib.game;

public final class Distance
{
	public static float distance(GameObject g1, GameObject g2)
	{
		float distx = Math.abs(g1.getX() - g2.getX());
		float disty = Math.abs(g1.getY() - g2.getY());
		return (float) Math.sqrt((distx * distx) + (disty * disty));
	}
}
