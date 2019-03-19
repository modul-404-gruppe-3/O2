import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountService as = new AccountService();
        while (true) {
            as.execute();
            System.out.println("geben sie 'stop' ein, um das programm zu stoppen, geben sie alles andre ein um das programm weiter laufen zu lassen");
            String s = new Scanner(System.in).nextLine();
            if (s.equalsIgnoreCase("stop")){
                break;
            }
        }
        as.stop();
     }
}
