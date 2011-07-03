package lib.game;

import org.newdawn.slick.Image;

public abstract class GameObject
{
	private String name;
	private float x;
	private float y;
	private float scale;
	private Image img;
	private boolean visible;
	
	public GameObject(String name, float x, float y, float scale, boolean vis)
	{
		setName(name);
		setX(x);
		setY(y);
		setScale(scale);
		setVisible(vis);
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public float getX()
	{
		return x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public float getY()
	{
		return y;
	}
	
	public void setScale(float scale)
	{
		this.scale = scale;
	}
	
	public float getScale()
	{
		return scale;
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public void moveTo(float x, float y)
	{
		setX(x);
		setY(y);
	}
	
	public void setImage (Image i){
		img = i;
	}
	
	public Image getImage (){
		return img;
	}
	
	public int hashCode()
	{
		int code = 90;
		code += getName().hashCode() * 97;
		return code;
	}
	
	public boolean equals(Object o)
	{
		return o instanceof GameObject && hashCode() == o.hashCode();
	}
}
