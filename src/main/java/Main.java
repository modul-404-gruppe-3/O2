import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountService as = new AccountService();
        while (true) {
            as.execute();
            System.out.println("drücken sie eine belibige taste um das Program erneut zu starten.");
            System.out.println("geben sie 'stop' ein, um das programm zu stoppen.");
            String s = new Scanner(System.in).nextLine();
            if (s.equalsIgnoreCase("stop")){
                break;
            }
        }
        as.stop();
     }
}
