import api.IProgram;
import api.IStopable;

import java.util.HashMap;

public class AccountService implements IStopable, IProgram {
    public HashMap<String, Account> accounts = new HashMap<>();

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
        switch (input) {
            case "1":
                handleAccountCreation();
                break;
            case "2":
                handleAccountOperations();
                break;
        }

    }

    private void handleAccountCreation() {
        System.out.println("bitte geben sie den namen des Accounts an, denn sie erstellen möchten:");
        String name = getScanner().next();
        accounts.put(name, new Account(0, name));
    }

    private void handleAccountOperations() {
        System.out.println("geben sie einen Accountnamen ein:");
        Account account = accounts.get(getScanner().next("Dieser Account existiert nicht, versuchen sie es erneut!",
                            accounts.keySet().toArray(new String[0])));
        System.out.println("bitte gebe ein, was du machen willst:");
        System.out.println("1 - Abheben");
        System.out.println("2 - Hinzufügen");
        System.out.println("3 - Bilanz anzeigen");
        String input = getScanner().next("Invalider Input, versuchen sie es erneut!", "1", "2", "3");
        switch (input) {
            case "1":
                System.out.println("Bitte geben sie eine Zahl ein:");
                account.takeMoney(getScanner().nextDouble("Bitte geben sie eine Valide Zahl ein!"));
                break;
            case "2":
                System.out.println("Bitte geben sie eine Zahl ein:");
                account.addMoney(getScanner().nextDouble("Bitte geben sie eine Valide Zahl ein!"));
                break;
            case "3":
                System.out.println("sie haben Aktuell " + account.getBilanz() + " auf ihrem Konto!");
                break;
        }
    }
}
