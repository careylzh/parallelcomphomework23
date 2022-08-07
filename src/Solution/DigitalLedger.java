package Solution;

import Solution.Accounts.Account;
import Solution.Requests.DepositRequest;
import Solution.Requests.LookupRequest;
import Solution.Requests.Request;
import Solution.Requests.TransferRequest;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static Solution.Config.*;

public class DigitalLedger {
    private static DigitalLedger ledger = null;
    private static AtomicInteger tick = new AtomicInteger(-1);
    private static AtomicInteger progress = new AtomicInteger(1);

    /**
     * Create thread-safe blocking queue which will store up to 100 requests.
     * DO NOT MODIFY
     */
    private static BlockingQueue<Request> sharedQueue = new LinkedBlockingQueue<>(BUFF_SIZE);

    /**
     * Assume there are 100 accounts in this ledger.
     * DO NOT MODIFY
     */
    private static Account[] accounts = new Account[NUM_ACCOUNTS];

    static HashMap<Integer, Account> accountHashMap = new HashMap<>();

    public static Request getRequests(int id) {
        return requests[id];
    }

    /**
     * Assume there are 1000 requests in total to be served.
     * DO NOT MODIFY
     */
    private static Request[] requests = new Request[NUM_REQUESTS];

    public static Account getAccounts(int accountID) {
        return accountHashMap.get(accountID);
    }

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private DigitalLedger() {
        PopulateAccounts();
        PopulateRequests();
    }

    public static DigitalLedger getInstance() {
        if (ledger == null)
            ledger = new DigitalLedger();
        return ledger;
    }

    private void PopulateAccounts() {
        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            Random random = new Random();
            accounts[i] = new Account(i, random.nextInt(1000));//for simplicity, we assign account id sequentially. In reality, Id is randomly generated, e.g., your bank account number.
            accountHashMap.put(i, accounts[i]);//this is useful, e.g., if account [0].id!=0.
        }
    }

    /**
     * This is to initialize some dummy requests for testing purpose.
     */
    private void PopulateRequests() {
        for (int i = 0; i < NUM_REQUESTS; i++) {
            Random random = new Random();
            switch (i % 3) {
                case 0: {
                    requests[i] = new LookupRequest(this, random.nextInt(NUM_ACCOUNTS));
                }
                case 1: {
                    requests[i] = new DepositRequest(this, random.nextInt(50), random.nextInt(NUM_ACCOUNTS));
                }
                case 2: {
                    requests[i] = new TransferRequest(this, random.nextInt(50), random.nextInt(NUM_ACCOUNTS), random.nextInt(NUM_ACCOUNTS));
                }
            }
        }
    }

    public void sendRequest(Request request) {
        try {
            sharedQueue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Request receiveRequest() {
        Request request = null;
        try {
            request = sharedQueue.take();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return request;
    }

    public int tick() {
        int t = tick.incrementAndGet();
        System.out.println("Tick: "+ t);
        return t;
    }

    public int progress() {
        return progress.get();
    }

    public void updateProgress() {
        progress.incrementAndGet();
    }
}
