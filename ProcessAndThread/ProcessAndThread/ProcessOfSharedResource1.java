package ProcessAndThread;

public class ProcessOfSharedResource1 {
    public static void main(String[] args) {
        // 공유 자원
        SharedResource sharedResource = new SharedResource();

        // 첫 번째 스레드
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                sharedResource.setValue(1);
            }
        });

        // 두 번째 프로세스 실행 (다른 JVM에서 실행)
        // 이 프로세스도 공유 자원에 접근하고 수정하는 스레드를 가질 것입니다.

        // 첫 번째 스레드 시작
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 최종 공유 자원 값 출력
        System.out.println("첫 번째 프로세스의 공유 자원 값: " + sharedResource.getValue());
    }
}
