package ThreadSyncronized;

/**
 * Counter 클래스를 통해 increment()와 decrement() 메서드를 동기화하고, 두 개의 스레드가 동시에 이 메서드를 호출하여 count를 증가 및 감소시킵니다.
 * synchronized 키워드를 사용하여 동기화를 구현합니다.
 */
public class SynchronizationExample {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.decrement();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final Count: " + counter.getCount());
    }
}
