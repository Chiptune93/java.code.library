package FolkAndIPC;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


class IPCExample {
    public static void main(String[] args) {
        try {
            PipedInputStream input = new PipedInputStream();
            PipedOutputStream output = new PipedOutputStream();

            // input과 output을 연결
            input.connect(output);

            // 데이터 쓰기 스레드
            Thread writerThread = new Thread(() -> {
                try {
                    String message = "Hello, IPC!";
                    output.write(message.getBytes());
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // 데이터 읽기 스레드
            Thread readerThread = new Thread(() -> {
                try {
                    int data;
                    while ((data = input.read()) != -1) {
                        System.out.print((char) data);
                    }
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writerThread.start();
            readerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

