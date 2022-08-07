package Solution;

/**
 * Note that the following design utilizes producer-consumer design pattern.
 * We will explore more design patterns later.
 */
public class DigitalLedgerClient {

    public static void main(String args[]) throws InterruptedException {
        int PThreads = 2;
        RequestsGenerator[] generators = new RequestsGenerator[PThreads];

        DigitalLedger ledger = DigitalLedger.getInstance();

        // Start Threads.
        int clientId = 0;

        for (int i=0; i< generators.length; i++) {
            generators[i] = new RequestsGenerator(ledger, clientId++,PThreads);
            generators[i].start();
        }


        // Wait for all threads to finish
        for (RequestsGenerator generator : generators) {
            generator.join();
        }

    }
}