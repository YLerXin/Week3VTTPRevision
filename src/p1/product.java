package p1;

public class product {
    private long ID;
    private String Name;
    private double Cost;
    private String Category;
    private int Amount;

    public product(long ID,String Name,double Cost,String Category,int Amount){
        this.ID = ID;
        this.Name = Name;
        this.Cost = Cost;
        this.Category = Category;
        this.Amount = Amount;
    }
    public long getID() {return ID;}
    public void setID(long iD) {ID = iD;}
    public String getName() {return Name;}
    public void setName(String name) {Name = name;}
    public double getCost() {return Cost;}
    public void setCost(double cost) {Cost = cost;}
    public String getCategory() {return Category;}
    public void setCategory(String category) { Category = category;}
    public int getAmount() {return Amount;}
    public void setAmount(int amount) {Amount = amount;}
    @Override
    public String toString() {
        return  ID +" "+ Name+" " + Cost +" "+ Category +" "+ Amount;
    }
    public void print(){
        System.out.println(toString());
    }

}
