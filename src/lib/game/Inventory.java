package lib.game;

public class Inventory
{
	private Item [] inv;
	
	public Inventory()
	{
		inv = new Item[16];
	}
	
	public void setSlot(int index, Item i)
	{
		if(index > 15 || index < 0)
			return;
		inv[index] = i;
	}
	
	public Item getSlot(int index)
	{
		return index > 15 || index < 0 ? null : inv[index];
	}
}
