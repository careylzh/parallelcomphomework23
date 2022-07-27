import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO: design this class to support the account information storage.
 */


public class Account {
    //schema <id, amt> (given in question slide 6/30)
    ConcurrentHashMap<String, Integer> accountSchema = new ConcurrentHashMap<>(); //implements Serializable interface: may be good for Client-Server communications later on

    public void setAccount(String id, int balance) { //set acc id and its initial value
        accountSchema.put(id, balance);
    }

    public int getAmtFromId(String id){
        return accountSchema.get(id);
    }

    public void add(String id, int amt){ //you only want modifications in sequence bro
        accountSchema.put(id, getAmtFromId(id)+amt);
    }

    public void sub(String id, int amt){
        accountSchema.put(id, getAmtFromId(id)-amt);
    }

    public ConcurrentHashMap<String, Integer> getAccountSchema() {
        return accountSchema;
    }

    public static void main(String[] args) {
        Account account = new Account();
//        account.accountSchema.put("1231", 10);
//        account.add("1231", 2);
//        account.sub("1231", 3);
        System.out.println(account.accountSchema);
    }
}
