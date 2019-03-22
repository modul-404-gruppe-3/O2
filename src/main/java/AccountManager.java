import java.util.HashMap;
import java.util.Set;

/**
 * Managing Accounts
 */
public class AccountManager {
    private HashMap<String, Account> accounts;
    public AccountManager() {
        accounts = new HashMap<>();
    }

    /**
     * sets a Account, even if it already exists.
     * @param account
     */
    public void addAccount(Account account) {
        accounts.put(account.getName(), account);
    }

    /**
     * removes account, if the given account does not exist, it will do nothing.
     * @param account
     */
    public void removeAccount(String account) {
        if (exists(account)) {
            accounts.remove(account);
        }
    }

    /**
     * @param name the name of the account
     * @return a Account Object according to the given name
     */
    public Account getAccount(String name) {
        return accounts.get(name);
    }

    /**
     * @return the Amount of Accounts that exists at the moment.
     */
    public int getAccountCount() {
        return accounts.size();
    }

    /**
     * @return all Accounts names that exist.
     */
    public Set<String> getAccountNames() {
        return accounts.keySet();
    }

    /**
     * checks if the given account exists.
     * @param accountName the name of the account to check for.
     * @return true of the account exists, false if it does not.
     */
    public boolean exists(String accountName) {
        return accounts.containsKey(accountName);
    }
}
