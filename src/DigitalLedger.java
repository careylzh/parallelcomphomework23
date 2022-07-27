import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Note that the following design utilizes producer-consumer design pattern.
 * We will explore more design patterns later.
 */
public class DigitalLedger {
    /**
     * Create thread-safe blocking queue which will store up to 100 requests.
     * DO NOT MODIFY
     */
    private static BlockingQueue<Request> sharedQueue = new LinkedBlockingQueue<>(100);

    /**
     * Assume there are 1000 requests in total to be served.
     * DO NOT MODIFY
     */
    private static Request[] requests = new Request[1000];

    /**
     * Assume there are 100 accounts in this ledger.
     * DO NOT MODIFY
     */
    private static Account[] accounts =new Account[100];

    /**
     * Main function. Initializes Producer and Consumers.
     * Sorts the result before writing it to the output file.
     * MODIFY IF NECESSARY
     * @param args
     * @throws InterruptedException
     */
    public static void main(String args[]) throws InterruptedException {
        int PThreads = 2;
        int CThreads = 2;
        RequestsGenerator[] generators = new RequestsGenerator[PThreads];
        RequestsHandler[] handlers = new RequestsHandler[CThreads];

        // Start Threads.
        for (RequestsGenerator generator : generators) {
            generator = new RequestsGenerator();
            generator.start();
        }

        for (RequestsHandler handler : handlers) {
            handler = new RequestsHandler();
            handler.start();
        }

        // Wait for all threads to finish
        for (RequestsGenerator generator : generators) {
            generator.join();
        }

        for (RequestsHandler handler : handlers) {
            handler.join();
        }
    }

    /**
     * RequestsGenerator continuously generates requests
     * MODIFY ACCORDINGLY
     */
    public static class RequestsGenerator extends Thread {

        public RequestsGenerator() {
        }

        @Override
        public void run() {
            // Pick up a request from Request[]
            // new it with a random request
            // and send it to the shared queue.
            //TODO: finish the rest.
//            try {
//                sharedQueue.put(request);
//                System.out.println("Sending Requests...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }
    }

    /**
     * RequestsHandler continuously serves requests
     * MODIFY ACCORDINGLY
     */
    public static class RequestsHandler extends Thread {

        @Override
        public void run() {
            //TODO: How to make sure all requests are processed?
        }

    }

}