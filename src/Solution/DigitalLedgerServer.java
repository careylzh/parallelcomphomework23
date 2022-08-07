package Solution;

/**
 * Note that the following design utilizes producer-consumer design pattern.
 * We will explore more design patterns later.
 */
public class DigitalLedgerServer {

    /**
     * Main function. Initializes Producer and Consumers.
     * Sorts the result before writing it to the output file.
     * MODIFY IF NECESSARY
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String args[]) throws InterruptedException {
        DigitalLedger ledger = DigitalLedger.getInstance();
        int CThreads = 2;
        RequestsHandler[] handlers = new RequestsHandler[CThreads];
        //
        int serverId = 0;
        for (RequestsHandler handler : handlers) {
            handler = new RequestsHandler(ledger, serverId++,CThreads);
            handler.start();
        }

        // Wait for all threads to finish
        for (RequestsHandler handler : handlers) {
            handler.join();
        }
        //
    }
}