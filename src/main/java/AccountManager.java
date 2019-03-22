import java.util.HashMap;
import java.util.Set;

public class AccountManager {
    private HashMap<String, Account> accounts;
    public AccountManager() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getName(), account);
    }

    public void removeAccount(String account) {
        if (exists(account)) {
            accounts.remove(account);
        }
    }

    public Account getAccount(String name) {
        return accounts.get(name);
    }

    public int getAccountCount() {
        return accounts.size();
    }

    public Set<String> getAccountNames() {
        return accounts.keySet();
    }

    public boolean exists(String accountName) {
        return accounts.containsKey(accountName);
    }
}
