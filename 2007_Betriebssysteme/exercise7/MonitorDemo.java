

public class MonitorDemo extends Thread {
    long COUNT_MAX = 10000000;

    private SynchronisedCounter theCounter;

    public MonitorDemo(SynchronisedCounter counter) {
        theCounter = counter;
    }

    public void run() {
        for (int i = 0; i < COUNT_MAX; ++i) {
            theCounter.increment();
        }
    }

    public static void main(String[] args) {
        SynchronisedCounter counter = new SynchronisedCounter();

        MonitorDemo raceDemo0 = new MonitorDemo(counter);
        MonitorDemo raceDemo1 = new MonitorDemo(counter);
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
