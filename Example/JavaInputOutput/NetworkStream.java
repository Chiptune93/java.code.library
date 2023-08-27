import java.io.*;
import java.net.*;

class ClientExample {
    public static void main(String[] args) {
        // try-with-resoruces 를 이용한 자원 생성 처리.
        // 처리가 완료되면 자동으로 닫힘.
        try (
                // 서버에 접속
                Socket socket = new Socket("127.0.0.1", 12345);
                // 출력 스트림 생성
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream, true);
                // 입력 스트림 생성
                InputStream inputStream = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {

            // 서버로 메시지 전송
            writer.println("안녕하세요, 서버!");

            // 서버로부터 메시지 수신
            String response = reader.readLine();
            System.out.println("서버로부터의 응답: " + response);

            // 연결 종료
            // socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerExample {
    public static void main(String[] args) {
        // 중간에 로직이 끼어있으면 try-with-resources 를 사용하기 어렵다.
        // fianlly 를 이용한 방법이 낫다.
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            // 서버 소켓 생성
            serverSocket = new ServerSocket(12345);
            System.out.println("서버가 시작되었습니다. 클라이언트를 기다립니다...");

            // 클라이언트 연결 대기
            clientSocket = serverSocket.accept();
            System.out.println("클라이언트가 연결되었습니다.");

            // 입력 스트림 생성
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // 클라이언트로부터 메시지 수신
            String message = reader.readLine();
            System.out.println("클라이언트로부터의 메시지: " + message);

            // 출력 스트림 생성
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            // 클라이언트에 응답 전송
            writer.println("안녕하세요, 클라이언트!");

            // 연결 종료
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 예외 발생 시 소켓 닫음 처리
                if (clientSocket != null) clientSocket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
