

public class RaceDemo extends Thread {
    long COUNT_MAX = 10000000;

    private Counter theCounter;

    public RaceDemo(Counter counter) {
        theCounter = counter;
    }

    public void run() {
        for (int i = 0; i < COUNT_MAX; ++i) {
            theCounter.increment();
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        
        RaceDemo raceDemo0 = new RaceDemo(counter);
        RaceDemo raceDemo1 = new RaceDemo(counter);
        raceDemo0.start();
        raceDemo1.start();
        try {
            raceDemo0.join();
            raceDemo1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("in=");
        System.out.println(counter.in);
    }
}
