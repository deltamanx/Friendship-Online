package lib.game;

import java.awt.Color;

public class Player extends Pony
{
	private long EXP;
	private Inventory inv;
	private PStats pStats;
	
	public Player(String name, int level, long exp, float x, float y, float scale, Stats s,
			int faction, Color baseColor, Color hlColor, Color maneColor,
			Color eyeColor, PStats pStats, Inventory inv)
	{
		super(name, level, x, y, scale, s, faction, baseColor, hlColor, maneColor, eyeColor);
		setEXP(exp);
		setPStats(pStats);
		setInv(inv);
	}

	public void setEXP(long eXP)
	{
		EXP = eXP;
	}

	public long getEXP()
	{
		return EXP;
	}

	public void setInv(Inventory inv)
	{
		this.inv = inv;
	}

	public Inventory getInv()
	{
		return inv;
	}

	public void setPStats(PStats pStats)
	{
		this.pStats = pStats;
	}

	public PStats getPStats()
	{
		return pStats;
	}

	public void levelUp()
	{
		setLevel(getLevel() + 1);
		pStats.generosity += 1;
		pStats.honesty += 1;
		pStats.intellect += 1;
		pStats.kindness += 1;
		pStats.loyalty += 1;
		pStats.strength += 1;
		calculateStats();
	}
	
	public void calculateStats()
	{
		getStats().hitpoints = (int)((getLevel() * 0.3) + (pStats.strength * 0.4) +
				(pStats.loyalty * 0.4)) * 10;
		getStats().mana = (int)((getLevel() * 0.3) + (pStats.intellect * 0.4) +
				(pStats.honesty * 0.4)) * 11;
	}
}
