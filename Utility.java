import java.util.Scanner;
public class Utility implements Space{
	private String name;
	private Player owner;
	private boolean isMortgaged;
	public static final int mortgage = 75;
	public static final int cost = 150;
	public Utility(String utilName)
	{
		name = utilName;
	}
	public String getName()
	{
		return name;
	}
	public String getColor()
	{
		return null;
	}
	public Player getOwner()
	{
		return owner;
	}
	public void act(Player p)
	{
		if (owner == null)
		{
			Scanner in = new Scanner(System.in);
			System.out.println("Purchase property? y/n");
			boolean found = false;
			while (!found)
			{
				String ans = in.next();
				if (ans.equals("y"))
				{
					owner = p;
					found = true;
					p.changeMoney(0 - cost);
				}
				else if (ans.equals("n"))
				{
					found = true;
				}
			}
		}
		else if (!(p == owner) && !isMortgaged)
		{
			if (owner.getUtilities() == 2)
			{
				p.changeMoney(0 - p.lastRoll() * 10);
				owner.changeMoney(p.lastRoll() * 10);
			}
			else
			{
				p.changeMoney(0 - p.lastRoll() * 4);
				owner.changeMoney(p.lastRoll() * 4);
			}
}
	}
	public void mortgage()
	{
		if (!isMortgaged)
		{
			isMortgaged = true;
			owner.changeMoney(mortgage);
		}
	}
	public void unmortgage()
	{
		if (isMortgaged)
		{
			isMortgaged = false;
			owner.changeMoney(0 - (int) (mortgage * 1.1));
		}
}
}
