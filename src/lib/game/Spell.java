package lib.game;

import java.util.ArrayList;

import lib.defs.SpellEffect;
import lib.defs.SpellType;
import lib.defs.TargetType;

public class Spell
{
	private String name;
	private SpellEffect spellEffect;
	private SpellType spellType;
	private TargetType targetType;
	private int damageMin;
	private int damageSpread;
	private int range;
	private int manaCost;

	private ArrayList<Aura> auraList;

	Spell(String name, SpellEffect spellEffect, SpellType spellType, TargetType targetType,
			int damageMin, int damageSpread, int range, int manaCost)
	{
		setName(name);
		setSpellEffect(spellEffect);
		setSpellType(spellType);
		setTargetType(targetType);
		setDamageMin(damageMin);
		setDamageSpread(damageSpread);
		setRange(range);
		setManaCost(manaCost);

		auraList = new ArrayList<Aura>();
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public Aura getAura(int i)
	{
		return auraList.get(i);
	}

	public int getAuraCount()
	{
		return auraList.size();
	}

	public boolean appliesAuras()
	{
		return auraList.size() > 0;
	}

	public int generateDamage()
	{
		return (int) ((Math.random() * getDamageSpread()) + getDamageMin());
	}

	public void setDamageMin(int damageMin)
	{
		this.damageMin = damageMin;
	}

	public int getDamageMin()
	{
		return damageMin;
	}

	public void setDamageSpread(int damageSpread)
	{
		this.damageSpread = damageSpread;
	}

	public int getDamageSpread()
	{
		return damageSpread;
	}

	public void setRange(int range)
	{
		this.range = range;
	}

	public int getRange()
	{
		return range;
	}

	public void setManaCost(int manaCost)
	{
		this.manaCost = manaCost;
	}

	public int getManaCost()
	{
		return manaCost;
	}

	public void setTargetType(TargetType targetType)
	{
		this.targetType = targetType;
	}

	public TargetType getTargetType()
	{
		return targetType;
	}

	public void setSpellEffect(SpellEffect spellEffect)
	{
		this.spellEffect = spellEffect;
	}

	public SpellEffect getSpellEffect()
	{
		return spellEffect;
	}

	public void setSpellType(SpellType spellType)
	{
		this.spellType = spellType;
	}

	public SpellType getSpellType()
	{
		return spellType;
	}
	
	public boolean canTarget(Unit caster, Unit target)
	{
		if(getSpellType() != SpellType.SPELL_TARGET)
			return false;
		switch(getTargetType())
		{
			case SPELL_T_NONE:
			{
				return false;
			}
			case SPELL_T_SELF:
			{
				return caster == target;
			}
			case SPELL_T_FNOTSELF:
			{
				if(caster == target)
					return false;
				if(target.getFaction() != 1)
					return false;
				return Distance.distance(caster, target) <= range;
			}
			case SPELL_T_ENEMY:
			{
				if(caster == target)
					return false;
				if(target.getFaction() == 1)
					return false;
				return Distance.distance(caster, target) <= range;
			}
			case SPELL_T_FRIEND:
			{
				if(caster == target)
					return true;
				if(target.getFaction() != 1)
					return false;
				return Distance.distance(caster, target) <= range;
			}
			default:
				return false;
		}
	}
}
