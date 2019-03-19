public class Account {
    private double bilanz;

    public Account(double bilanz) {
        this.bilanz = bilanz;
    }

    public void addMoney(double amount){
        this.bilanz += amount;
    }

    public void takeMoney(double amount){
        this.bilanz -= amount;
    }
    public double getBilanz(){
        return this.bilanz;
    }

}
