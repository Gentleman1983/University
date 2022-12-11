

public class SynchronisedCounter {
    long in = 0;
    
    public synchronized void increment() {
        long next_free_slot;

        next_free_slot = in;
        next_free_slot = next_free_slot + 1;
        in = next_free_slot;
    }
}
