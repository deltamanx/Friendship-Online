package lib.world;

import lib.game.GameObject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class BoundedWorld extends World
{
	private float width;
	private float height;
	
	public BoundedWorld (float width, float height)
	{
		super();
		this.width = width;
		this.height = height;
	}
	
	public float getWidth(){
		return width;
	}
	
	public Image generateImage (){
		Image i = null;
		try
		{
			i = new Image ((int)width,(int)height);
			Graphics g = i.getGraphics();
			for (GameObject o : getWorld()){
				g.drawImage (o.getImage(), o.getX(), o.getY());
			}
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		return i;
	}
	
	public float getHeight(){
		return height;
	}
	
}
