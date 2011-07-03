package lib.world;

import java.util.ArrayList;

import lib.game.GameObject;

public class World
{
	private ArrayList<GameObject> world;
	
	public World()
	{
		setWorld(new ArrayList<GameObject>());
	}
	
	public void addObject(GameObject go)
	{
		getWorld().add(go);
	}
	
	public GameObject getObjectAt (int index)
	{
		return getWorld().get(index);
	}
	
	public GameObject removeFromWorld(int index)
	{
		return getWorld().remove(index);
	}
	
	public boolean removeFromWorld(GameObject go)
	{
		return getWorld().remove(go);
	}

	public void setWorld(ArrayList<GameObject> world)
	{
		this.world = world;
	}

	public ArrayList<GameObject> getWorld()
	{
		return world;
	}
}
