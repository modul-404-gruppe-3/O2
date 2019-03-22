public class Main {
    public static void main(String[] args) {
        AccountService as = new AccountService(new AccountManager());
        while (!as.stop) {
            as.execute();
            System.out.println("");
            System.out.println("");
            System.out.println("geben sie 'stop' ein, um das Programm zu stoppen.");
            System.out.println("");
        }
        as.stop();
     }
}
