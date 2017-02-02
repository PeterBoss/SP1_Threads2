package ex2;


import ex1.UrlRetriever;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <h2>Task 2</h2>
 * <p>
 * By using the {@link UrlRetriever} you implemented in the previous exercise, your job is now to run synchronously
 * and asynchronously.
 * </p>
 * <p>
 * First of all find three different {@link java.net.URL}s you can use. Then create three instances of the
 * {@link UrlRetriever} with these URL's.
 * </p>
 * <ol>
 * <li>The first subtask is to execute the three {@link UrlRetriever}s with the {@link UrlRetriever#run()}
 * method. Note that this is <b>not</b> the start method, so the code is run synchronously.</li>
 * <li>The second subtask is to change the execution to use {@link UrlRetriever#start()}. Remember that this
 * starts an asynchronous thread, so you need to wait for them all to finish!</li>
 * </ol>
 * <p>
 * Both methods will print a run-time on the execution. Can you see a difference on how fast it went? Can you
 * explain why you get that result?
 * </p>
 * <p>
 * Try to run the {@link #getAvailableProcessors()} method to see how many processors you have. Perhaps this
 * can explain the result.
 * </p>
 */
public class UrlRetrieverSynchronisation {

    /**
     * Returns the number of processors Java has detected.
     *
     * @return A positive number.
     */
    public static int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        final long startTime = System.currentTimeMillis();
        
        UrlRetriever retri1 = new UrlRetriever(new URL("https://google.com/"));
        UrlRetriever retri2 = new UrlRetriever(new URL("https://facebook.com/"));
        UrlRetriever retri3 = new UrlRetriever(new URL("https://amazon.com/"));
        
//        retri1.run();
//        retri2.run(); //~2500ms
//        retri3.run();
        
        
        
        retri1.start();
        retri2.start(); //~1800ms
        retri3.start();
            
        retri3.join();
        retri2.join();
        retri1.join();


        // Run your three UrlRetrievers
        // First with the #run() method from the UrlRetriever. How long did that take?
        // Second with the #start() method from the UrlRetriever. How long did that take?
        // - Remember to wait for all the threads to finish!

        final long endTime = System.currentTimeMillis() - startTime;
        System.out.println(String.format("The execution took %d milliseconds", endTime));
    }

}
