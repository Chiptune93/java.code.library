package ProcessAndThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ProcessPoolExample {
    public static void main(String[] args) {
        // 프로세스 풀 생성
        ExecutorService processPool = Executors.newFixedThreadPool(3); // 최대 3개의 프로세스 인스턴스

        // 여러 작업을 시뮬레이션
        for (int i = 1; i <= 10; i++) {
            final int taskNumber = i;
            processPool.submit(() -> {
                System.out.println("프로세스 " + taskNumber + " 시작");
                try {
                    // 프로세스가 작업하는 시간 시뮬레이션
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("프로세스 " + taskNumber + " 종료");
            });
        }

        // 프로세스 풀 종료
        processPool.shutdown();
        try {
            processPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("모든 작업 완료");
    }
}
