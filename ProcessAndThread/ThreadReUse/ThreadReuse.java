package ThreadReUse;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

class ThreadPoolExample {
    public static void main(String[] args) {
        // 쓰레드 풀 생성 (4개의 스레드)
        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        // 작업 추가
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            threadPool.execute(() -> {
                System.out.println("작업 " + taskNumber + "을 처리 중");
            });
        }

        // 쓰레드 풀 종료
        threadPool.shutdown();
    }
}


class WorkerThreadExample {
    public static void main(String[] args) {
        // 작업 큐 생성
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

        // Worker 스레드 생성 및 실행
        for (int i = 0; i < 4; i++) {
            Thread workerThread = new Thread(() -> {
                while (true) {
                    try {
                        Runnable task = workQueue.take(); // 큐에서 작업을 가져와 실행
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break; // 스레드 종료
                    }
                }
            });
            workerThread.start();
        }

        // 작업 추가
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            workQueue.add(() -> {
                System.out.println("작업 " + taskNumber + "을 처리 중");
            });
        }

        // Worker 스레드 종료
        for (int i = 0; i < 4; i++) {
            workQueue.add(() -> {
                Thread.currentThread().interrupt();
            });
        }
    }
}

class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        // 스레드 1
        Thread thread1 = new Thread(() -> {
            threadLocal.set(1);
            System.out.println("스레드 1: " + threadLocal.get());
        });

        // 스레드 2
        Thread thread2 = new Thread(() -> {
            threadLocal.set(2);
            System.out.println("스레드 2: " + threadLocal.get());
        });

        thread1.start();
        thread2.start();
    }
}

class DaemonThreadExample {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("데몬 스레드 실행 중");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        daemonThread.setDaemon(true); // 데몬 스레드로 설정
        daemonThread.start();

        // 메인 스레드
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("메인 스레드 종료");
    }
}


