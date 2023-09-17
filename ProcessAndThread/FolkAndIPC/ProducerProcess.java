package FolkAndIPC;

import java.io.IOException;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

/**
 * java.nio 패키지를 사용하여 공유 메모리를 구현합니다. 아래의 코드는 두 개의 프로세스 간에 정수 데이터를 공유하고 업데이트하는 간단한 IPC 예제입니다.
 * 이 예제에서는 두 개의 프로세스가 shared_memory.bin 파일을 통해 공유 메모리를 사용하여 데이터를 주고 받습니다.
 * Producer 프로세스는 1부터 10까지의 정수를 생성하고, Consumer 프로세스는 이 값을 소비하여 출력합니다.
 */
public class ProducerProcess {
    public static void main(String[] args) {
        try {
            // 공유 메모리 파일 생성
            Path sharedMemoryFile = Path.of("shared_memory.bin");
            FileChannel fileChannel = FileChannel.open(sharedMemoryFile, StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            // 공유 메모리 버퍼 생성
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 4);
            IntBuffer intBuffer = buffer.asIntBuffer();

            for (int i = 1; i <= 10; i++) {
                intBuffer.clear();
                intBuffer.put(i);
                System.out.println("Producer: Produced " + i);
                TimeUnit.SECONDS.sleep(1);
            }

            fileChannel.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
