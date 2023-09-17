package ProcessAndThread;

public class ProcessThreadExample {
    // 공유 변수
    private static int sharedVariable = 0;

    public static void main(String[] args) {
        // 두 개의 스레드 생성
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    sharedVariable++;
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    sharedVariable--;
                }
            }
        });

        // 스레드 시작
        thread1.start();
        thread2.start();

        try {
            // 스레드가 모두 종료될 때까지 대기
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 최종 공유 변수 값 출력
        System.out.println("공유 변수 값: " + sharedVariable);
    }
}
