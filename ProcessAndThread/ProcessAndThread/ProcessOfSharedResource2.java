package ProcessAndThread;

public class ProcessOfSharedResource2 {
    public static void main(String[] args) {
        // 공유 자원
        SharedResource sharedResource = new SharedResource();

        // 두 번째 스레드
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                sharedResource.setValue(2);
            }
        });

        // 두 번째 스레드 시작
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 최종 공유 자원 값 출력
        System.out.println("두 번째 프로세스의 공유 자원 값: " + sharedResource.getValue());
    }
}
