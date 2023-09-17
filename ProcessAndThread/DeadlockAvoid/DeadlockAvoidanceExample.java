package DeadlockAvoid;

/**
 * 이 예제에서는 두 개의 Resource 객체를 생성하고, 데드락을 회피하기 위해 doSomething() 및 doAnotherThing() 메서드를 동기화합니다.
 * 스레드 1과 스레드 2가 서로 다른 순서로 doAnotherThing()을 호출하여 데드락을 회피합니다.
 */
public class DeadlockAvoidanceExample {
    public static void main(String[] args) {
        Resource resource1 = new Resource(1);
        Resource resource2 = new Resource(2);

        Thread thread1 = new Thread(() -> {
            resource1.doAnotherThing(resource2);
        });

        Thread thread2 = new Thread(() -> {
            resource2.doAnotherThing(resource1);
        });

        thread1.start();
        thread2.start();
    }
}
