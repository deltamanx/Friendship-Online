package lib.game;

public class Item
{
	private String name;
	private int type;
	
	public Item(String name, int type)
	{
		setName(name);
		setType(type);
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
	
	public int getType()
	{
		return type;
	}
	
	public int hashCode()
	{
		int code = 92;
		code += getName().hashCode() * 92;
		code += getType() * 87;
		return code;
	}
	
	public boolean equals(Object o)
	{
		return o instanceof Item && hashCode() == o.hashCode();
	}
}
