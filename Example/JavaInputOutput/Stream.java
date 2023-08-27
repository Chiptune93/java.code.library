import java.io.*;

class ByteStreamExampleInputStream {
    public static void main(String[] args) {
        // 자원 자동 해제를 위한 try-with-resoruces
        try (FileInputStream inputStream = new FileInputStream("example.txt")) {
            int data;
            while ((data = inputStream.read()) != -1) {
                System.out.print((char) data); // 바이트를 문자로 변환하여 출력
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class ByteStreamExampleOutputStream {
    public static void main(String[] args) {
        // 자원 자동 해제를 위한 try-with-resoruces
        try (FileOutputStream outputStream = new FileOutputStream("example.txt")) {
            String data = "Hello, Byte Stream!";
            byte[] bytes = data.getBytes(); // 문자열을 바이트 배열로 변환
            outputStream.write(bytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CharacterStreamExampleFileReader {
    public static void main(String[] args) {
        // 자원 자동 해제를 위한 try-with-resoruces
        try (FileReader reader = new FileReader("example.txt")) {
            int data;
            while ((data = reader.read()) != -1) {
                System.out.print((char) data);
            }
            // reader.close(); 자원이 자동으로 닫힘
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CharacterStreamExampleFileWriter {
    public static void main(String[] args) {
        // 자원 자동 해제를 위한 try-with-resoruces
        try (FileWriter writer = new FileWriter("example.txt")) {
            String data = "Hello, Character Stream!";
            writer.write(data);
            // writer.close(); 자원이 자동으로 닫힘
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class FileIOExample {
    public static void main(String[] args) {
        // 자원 자동 해제를 위한 try-with-resoruces, 여러 개도 가능하다.
        try (FileReader reader = new FileReader("input.txt");
             FileWriter writer = new FileWriter("output.txt");
             // 파일에서 데이터를 읽는 스트림 생성
             BufferedReader bufferedReader = new BufferedReader(reader);
             // 데이터를 출력하는 스트림 생성
             BufferedWriter bufferedWriter = new BufferedWriter(writer);) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 데이터를 읽어와서 가공 또는 필터링
                String modifiedLine = line.toUpperCase();

                // 결과를 출력 스트림에 쓰기
                bufferedWriter.write(modifiedLine);
                bufferedWriter.newLine();
            }

            // 스트림 닫기
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
