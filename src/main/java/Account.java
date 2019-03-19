import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Account {
    private double bilanz;
    private String name;

    public void addMoney(double amount){
        this.bilanz += amount;
    }

    public void takeMoney(double amount){
        this.bilanz -= amount;
    }
}
