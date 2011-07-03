package lib.game;

import java.util.ArrayList;

import lib.defs.SpellEffect;

public class Unit extends GameObject
{
	private boolean mobile;
	private float xSpeed;
	private float ySpeed;
	private int faction;
	private Stats stats;
	private ArrayList<Aura> auraList;
	private int level;

	public Unit(String name, int level, float x, float y, float scale, boolean vis, Stats s,
			boolean mobile, int faction)
	{
		super(name, x, y, scale, vis);
		setStats(s);
		setMobile(mobile);
		setFaction(faction);
		setLevel(level);
	}

	public void setMobile(boolean mobile)
	{
		this.mobile = mobile;
	}

	public boolean isMobile()
	{
		return mobile;
	}

	public void setXSpeed(float xSpeed)
	{
		this.xSpeed = xSpeed;
	}

	public float getXSpeed()
	{
		return xSpeed;
	}

	public void setYSpeed(float ySpeed)
	{
		this.ySpeed = ySpeed;
	}

	public float getYSpeed()
	{
		return ySpeed;
	}

	public void setFaction(int faction)
	{
		this.faction = faction;
	}

	public int getFaction()
	{
		return faction;
	}

	public void setStats(Stats stats)
	{
		this.stats = stats;
	}

	public Stats getStats()
	{
		return stats;
	}

	public void setAuraList(ArrayList<Aura> auraList)
	{
		this.auraList = auraList;
	}

	public ArrayList<Aura> getAuraList()
	{
		return auraList;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public int getLevel()
	{
		return level;
	}
	
	public boolean canMove(int delta)
	{
		return true;
	}
	
	public void move(int delta)
	{
		moveTo (getX() + (getXSpeed() * (delta / 1000)),
			getY() + (getYSpeed() * (delta / 1000)));
	}

	public boolean canCastSpell(Spell s)
	{
		return s.getManaCost() <= getStats().mana;
	}
	
	public boolean canCastAt(Spell s, Unit target)
	{
		return canCastSpell(s) && s.canTarget(this, target);
	}
	
	public void castSpell(Spell s, Unit target)
	{
		getStats().mana -= s.getManaCost();
		//TODO: Spell effects.
		target.spellHit(s);
	}
	
	public void castSpell(Spell s, Unit []targets)
	{
		getStats().mana -= s.getManaCost();
		//TODO: Spell effects.
		for(int i = 0; i < targets.length; i++)
			targets[i].spellHit(s);
	}
	
	public void spellHit(Spell s)
	{
		if(s.getSpellEffect() == SpellEffect.SPELL_E_DAMAGE)
			getStats().hitpoints -= s.generateDamage();
		else if(s.getSpellEffect() == SpellEffect.SPELL_E_HEAL)
			getStats().hitpoints += s.generateDamage();
		else
			getStats().hitpoints += 0;
		applySpellAuras(s);
		//TODO: Spell effects
	}
	
	public void applySpellAuras(Spell s)
	{
		if(s.appliesAuras())
		{
			for(int i = 0; i < s.getAuraCount(); i++)
				auraList.add(s.getAura(i));
		}
	}
}
