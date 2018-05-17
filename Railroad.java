
public class Railroad implements Space {
  
  private String name;
  private String owner;
  private boolean isMortgaged;
  private static int numOwned;
  private int price;
  private int rent;
  private int mortgageAmt;
  private boolean canCollectRent;
  
  public Railroad(String n) {
    name = n;
    owner = "";
    isMortgaged = false;
    numOwned = 0;
    price = 200;
    rent = 25;
    mortgageCost = 100;
  }
  
  public void act(Player p) {
    if (owner.equals("")) {
        if (p.askToBuy(getName())) {
          numOwned++;
          rent *= numOwned;
        }
    } else if (canCollectRent) {
       if (!owner.equals(p.getName())) {
        p.changeMoney(0 - rent);
      }
    }
  }
  
  public String getName() {
    return name;
  }
  
  public int getPrice() {
    return price;
  }
  
  public int getRent() {
    return rent;
  }
  
  public int getMortgageAmt() {
    return mortgageAmt;
  }
  
  public void changeMortgageState() {
    if (isMortgaged) {
      isMortgaged = false;
      p.changeMoney(0 - Math.round(mortgageAmt * 0.1));
    } else {
      p.changeMoney(mortgageAmt);
  }

}
