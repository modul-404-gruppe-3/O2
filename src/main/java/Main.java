public class Main {
    public static void main(String[] args) {
        AccountService as = new AccountService();
        while (!as.stop) {
            as.execute();
            System.out.println("");
            System.out.println("geben sie 'stop' ein, um das programm zu stoppen.");
            System.out.println("");
        }
        as.stop();
     }
}
