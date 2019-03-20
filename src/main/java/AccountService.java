import api.IProgram;
import api.IStopable;

import java.util.HashMap;

/**
 * the main class behind the account system, this class is most responsible for the Gui part and it will call more low
 * level Methods.
 */
public class AccountService implements IStopable, IProgram {
    public HashMap<String, Account> accounts = new HashMap<>();

    /**
     * This Method will be executed every time the Program gets started. This means that it will be executed every time
     * the Program in intsself will be started as well es everty time a Action like see Bank balance is complete and the
     * user choses not to stop the program via the stop command.
     */
    @Override
    public void execute() {
        if (accounts.size() < 1) {
            handleAccountCreation();
            return;
        }

        System.out.println("Bitte gebe ein ob du einen Account erstellen oder dich bei einem anmelden möchtest:");
        System.out.println("1 - Account erstellen");
        System.out.println("2 - sich bei Account anmelden!");
        String input = getScanner().next("Fehlerhafte eingabe, versuchen sie es erneut!", "1", "2");

        if (input == null) {
            return;
        }

        switch (input) {
            case "1":
                handleAccountCreation();
                break;
            case "2":
                handleAccountOperations();
                break;
        }
    }

    /**
     * This Method creates Accounts regaredless if the already exists or not, if the account already exists it will be overriden.
     */
    private void handleAccountCreation() {
        System.out.println("bitte geben sie den Namen des Accounts an, den sie erstellen möchten:");
        String name = getScanner().next();

        if (name == null) {
            return;
        }

        accounts.put(name, new Account(0, name));
    }

    /**
     * This Method is a Part the GUI for the Account Management.
     * It Handles every Possible thing that can be done with a Account.
     */
    private void handleAccountOperations() {
        System.out.println("geben sie einen Accountnamen ein:");

        String[] allAccountNames = accounts.keySet().toArray(new String[0]);
        String accountName = getScanner().next("Dieser Account existiert nicht, versuchen sie es erneut!", allAccountNames);
        Account account = accounts.get(accountName);

        System.out.println("bitte gebe ein, was du machen willst:");
        System.out.println("1 - Abheben");
        System.out.println("2 - Hinzufügen");
        System.out.println("3 - Bilanz anzeigen");
        System.out.println("4 - Löschen");
        String input = getScanner().next("Invalider Input, versuchen sie es erneut!", "1", "2", "3", "4");

        if (input == null) {
            return;
        }
        switch (input) {
            case "1":
                System.out.println("Bitte geben sie eine Zahl ein:");

                account.takeMoney(getScanner().nextDouble("Bitte geben sie eine Valide Zahl grösser als 0 ein!", aDouble -> aDouble > 0));
                break;
            case "2":
                System.out.println("Bitte geben sie eine Zahl ein:");
                account.addMoney(getScanner().nextDouble("Bitte geben sie eine Valide Zahl grösser als 0 ein!", aDouble -> aDouble > 0));
                break;
            case "3":
                System.out.println("sie haben Aktuell " + account.getBilanz() + " auf ihrem Konto!");
                break;
            case "4":
                System.out.println("sie haben den Account " + account.getName() + " gelöscht!");
                accounts.remove(account.getName());
                break;
        }
    }
}
