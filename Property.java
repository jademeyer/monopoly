import java.util.Scanner;
public class Property implements Space{
	private String name;
	private Player owner;
	private boolean isMortgaged;
	private int numHouses;
	private String color;
	private int price;
	private int rent_normal;
	private int rent_monopoly;
	private int rent_onehouse;
	private int rent_twohouse;
	private int rent_threehouse;
	private int rent_fourhouse;
	private int rent_hotel;
	private int house_cost;
	private int mortgage;
	public Property(String propName, String propColor, int cost, int rn, int rm, int r1, int r2, int r3, int r4, int rh, int hc, int mt)
	{
		name = propName;
		color = propColor;
		price = cost;
		rent_normal = rn;
		rent_monopoly = rm;
		rent_onehouse = r1;
		rent_twohouse = r2;
		rent_threehouse = r3;
		rent_fourhouse = r4;
		rent_hotel = rh;
		house_cost = hc;
		mortgage = mt;
	}
	public String getName()
	{
		return name;
	}
	public Player getOwner()
	{
		return owner;
	}
	public void ownerNull()
	{
		owner = null;
	}
	public String getColor()
	{
		return color;
	}
	public int getHouses()
	{
		return numHouses;
	}
	public void addHouse() 
	{
		if (owner.hasMonopoly(color) && numHouses < 5)
		{
			numHouses++;
			owner.changeMoney(0 - house_cost);
			if (numHouses == 5)
			{
				owner.changeHouses(0 - 4);
				owner.changeHotel(1);
			}
			else {owner.changeHouses(1);}
			System.out.println(owner.getName() + " has added a house to " + this.getName());
		}	
	}
	public void removeHouse()
	{
		if (owner.hasMonopoly(color) && numHouses > 1)
		{
			numHouses--;
			owner.changeMoney(0 - house_cost / 2);
			if (numHouses == 4)
			{
				owner.changeHouses(4);
				owner.changeHotel(0 - 1);
			}
			else
			{
				owner.changeHouses(0 - 1);
			}
			System.out.println(owner.getName() + "has removed a house from " + this.getName());
		}
	}
	public void mortgage()
	{
		if (!isMortgaged)
		{
			isMortgaged = true;
			owner.changeMoney(mortgage);
			System.out.println(owner.getName() + " has mortgaged " + this.getName());
		}
	}
	public void unmortgage()
	{
		if (isMortgaged)
		{
			isMortgaged = false;
			owner.changeMoney(0 - (int) (mortgage * 1.1));
			System.out.println(owner.getName() + " has unmortgaged " + this.getName());
		}
	}
	public void act(Player p)
	{
		if (owner == null)
		{
			System.out.println("Purchase property? Yes (1) or No (2)");
			boolean found = false;
			while (!found)
			{
				Scanner in = new Scanner(System.in);
				int ans = 0;
				if (in.hasNextInt()) {ans = in.nextInt();}
				if (ans == 1)
				{
					owner = p;
					p.getProperties().add(this.getName());
					found = true;
					System.out.println(p.getName() + " has purchased " + this.getName());
					p.changeMoney(0 - price);
				}
				else if (ans == 2)
				{
					found = true;
				}
			}
		}
		else if (!(p == owner) && !isMortgaged)
		{
			if (numHouses == 0) 
			{
				if (owner.hasMonopoly(color))
				{
					p.changeMoney(0 - rent_monopoly);
					owner.changeMoney(rent_monopoly);
				}
				else 
				{
					p.changeMoney(0 - rent_normal);
					owner.changeMoney(rent_normal);
				}
			}
			if (numHouses == 1) 
			{
				p.changeMoney(0 - rent_onehouse);
				owner.changeMoney(rent_onehouse);
			}
			if (numHouses == 2) 
			{
				p.changeMoney(0 - rent_twohouse);
				owner.changeMoney(rent_twohouse);
			}
			if (numHouses == 3) 
			{
				p.changeMoney(0 - rent_threehouse);
				owner.changeMoney(rent_threehouse);
			}
			if (numHouses == 4) 
			{
				p.changeMoney(0 - rent_fourhouse);
				owner.changeMoney(rent_fourhouse);
			}
			if (numHouses == 5) 
			{
				p.changeMoney(0 - rent_hotel);
				owner.changeMoney(rent_hotel);
			}
		}
	}
}
