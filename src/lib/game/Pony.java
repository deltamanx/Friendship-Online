package lib.game;

import java.awt.Color;

public class Pony extends Unit
{
	private Color baseColor;
	private Color hlColor;
	private Color maneColor;
	private Color eyeColor;
	
	public Pony(String name, int level, float x, float y, float scale, Stats s, int faction,
			Color baseColor, Color hlColor, Color maneColor, Color eyeColor)
	{
		super(name, level, x, y, scale, true, s, true, faction);
		setBaseColor(baseColor);
		setHLColor(hlColor);
		setManeColor(maneColor);
		setEyeColor(eyeColor);
	}

	public void setBaseColor(Color baseColor)
	{
		this.baseColor = baseColor;
	}

	public Color getBaseColor()
	{
		return baseColor;
	}

	public void setHLColor(Color hlColor)
	{
		this.hlColor = hlColor;
	}

	public Color getHLColor()
	{
		return hlColor;
	}

	public void setManeColor(Color maneColor)
	{
		this.maneColor = maneColor;
	}

	public Color getManeColor()
	{
		return maneColor;
	}

	public void setEyeColor(Color eyeColor)
	{
		this.eyeColor = eyeColor;
	}

	public Color getEyeColor()
	{
		return eyeColor;
	}

	public int hashCode()
	{
		int code = 94;
		code += getName().hashCode() * 64;
		code += getBaseColor().hashCode() * 26;
		code += getHLColor().hashCode() * 35;
		code += getManeColor().hashCode() * 27;
		return code += getEyeColor().hashCode() * 31;
	}
	
	public boolean equals(Object o)
	{
		return o instanceof Pony && hashCode() == o.hashCode();
	}
}
