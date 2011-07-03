package lib.game;

public class Aura
{
	private String name;
	
	public Aura(String name)
	{
		setName(name);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	
	public int hashCode()
	{
		return 90 + (getName().hashCode() * 94);
	}
	
	public boolean equals(Object o)
	{
		return o instanceof Aura && hashCode() == o.hashCode();
	}
}
