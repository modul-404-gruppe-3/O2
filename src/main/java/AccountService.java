import api.IProgram;
import api.IStopable;
import lombok.Getter;

import java.util.HashMap;

/**
 * the main class behind the account system, this class is most responsible for the Gui part and it will call more low
 * level Methods.
 *
 * This Service is not designed to be able to work Concurrent, it can handle multiple accounts but it can not really
 * handle multiple Users at once.
 */
public class AccountService implements IStopable, IProgram {
    @Getter
    boolean stop = false;
    private AccountManager manager;

    public AccountService(AccountManager manager) {
        this.manager = manager;
    }

    /**
     * This Method will be executed every time the Program gets started. This means that it will be executed every time
     * the Program in initsself will be started as well es every time a Action like see Bank balance is complete and the
     * user choses not to stop the program via the stop command.
     */
    @Override
    public void execute() {
        if (manager.getAccountCount() < 1) {
            handleAccountCreation();
            return;
        }

        System.out.println("Bitte gebe ein ob du einen Account erstellen oder dich bei einem anmelden möchtest:");
        System.out.println("1 - Account erstellen");
        System.out.println("2 - sich bei Account anmelden!");
        System.out.println("3 - Accounts auflisten");
        String input = getScanner().next("Fehlerhafte eingabe, versuchen sie es erneut!", "1", "2", "3");

        if (input == null) {
            stop = true;
            return;
        }

        switch (input) {
            case "1":
                handleAccountCreation();
                break;
            case "2":
                handleAccountOperations();
                break;
            case "3":
                System.out.println("Alle Accounts:");
                for (String s : manager.getAccountNames()) {
                    System.out.println(s);
                }
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
            System.out.println("Account erstellen abgebrochen.");
            return;
        }

        if (manager.exists(name)) {
            System.out.println("Dieser Account existiert bereits, sicher das du diesen überschreiben willst? ('y' für ja, 'n' für nein)");
            String input = getScanner().next("Bitte gebe entwerder 'y' oder 'n' ein.", "y", "n", "Y", "N");
            switch (input.toLowerCase()) {
                case "n":
                    System.out.println("Account erstellen abgebrochen.");
                    return;
                case "y":
                    System.out.println("okay, account wird überschrieben!");
                    break;
            }
        }

        System.out.println("Bitte geben sie Ihr startkapital ein.");
        Double startKapital = getScanner().nextDouble("Bitte geben sie eine Valide Zahl grösser oder gleich 0 ein!", aDouble -> aDouble >= 0);

        if (startKapital == null) {
            System.out.println("Account erstellen abgebrochen.");
            return;
        }

        manager.addAccount(new Account(startKapital, name));
        System.out.println("Account mit dem Namen " + name + " wurde erstellt!");
    }

    /**
     * This Method is a Part the GUI for the Account Management.
     * It Handles every Possible thing that can be done with a Account.
     */
    private void handleAccountOperations() {
        System.out.println("geben sie einen Accountnamen ein:");

        String[] allAccountNames = manager.getAccountNames().toArray(new String[0]);
        String accountName = getScanner().next("Dieser Account existiert nicht, versuchen sie es erneut!", allAccountNames);

        if (accountName == null) {
            System.out.println("Account anmelden abgebrochen!");
            return;
        }

        Account account = manager.getAccount(accountName);

        allAccountNames = manager.getAccountNames().stream().filter(s -> !s.equalsIgnoreCase(accountName)).toArray(String[]::new);


        System.out.println("bitte gebe ein, was du machen willst:");
        System.out.println("1 - Abheben");
        System.out.println("2 - Hinzufügen");
        System.out.println("3 - Bilanz anzeigen");
        System.out.println("4 - Geld überweisen");
        System.out.println("5 - Löschen");
        String input = getScanner().next("Invalider Input, versuchen sie es erneut!", "1", "2", "3", "4", "5");

        if (input == null) {
            System.out.println("Auswahl abgebrochen.");
            return;
        }
        switch (input) {
            case "1":
                System.out.println("Bitte geben sie eine Zahl ein:");

                account.takeMoney(getScanner().nextDouble("Bitte geben sie eine Valide Zahl grösser als 0 ein!", aDouble -> aDouble > 0  && account.getBilanz() - aDouble > 0));
                break;
            case "2":
                System.out.println("Bitte geben sie eine Zahl ein:");
                account.addMoney(getScanner().nextDouble("Bitte geben sie eine Valide Zahl grösser als 0 ein!", aDouble -> aDouble > 0));
                break;
            case "3":
                System.out.println("sie haben Aktuell " + account.getBilanz() + " auf ihrem Konto!");
                break;
            case "4":
                System.out.println("geben sie den Namen des Accounts an auf den sie Geld überweisen wollen.");
                String targetAccountName = getScanner().next("Dieser Account existiert nicht, bitte versuchen sie er erneut.", allAccountNames);

                if (targetAccountName == null) {
                    System.out.println("Überweisung abgebrochen.");
                    return;
                }

                Account targetAccount = manager.getAccount(targetAccountName);
                System.out.println("geben sie bitte jetzt an, wie viel sie überweisen wollen.");
                Double amount = getScanner().nextDouble("Bitte geben sie eine Valide Zahl grösser als 0 ein, die auf ihrem Konto vorhanden ist!", aDouble -> aDouble > 0 && account.getBilanz() - aDouble > 0);


                if (amount == null) {
                    System.out.println("Überweisung abgebrochen.");
                    return;
                }

                account.transfer(targetAccount, amount);
                System.out.println("Du hast " + targetAccountName + " " + amount + " überwiesen.");
                break;
            case "5":
                System.out.println("sie haben den Account " + account.getName() + " gelöscht!");
                manager.removeAccount(account.getName());
                break;
        }
    }
}
