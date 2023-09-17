package ProcessAndThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessControlExample {
    public static void main(String[] args) {
        try {
            // 외부 프로그램 "ping"을 실행
            ProcessBuilder processBuilder = new ProcessBuilder("ping", "www.example.com");

            // 프로세스 실행
            Process process = processBuilder.start();

            // 프로세스의 출력 스트림 읽기
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // 프로세스가 완료될 때까지 대기
            int exitCode = process.waitFor();
            System.out.println("프로세스 종료 코드: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
