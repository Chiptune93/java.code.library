package Stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 1. 스트림 생성
 * 스트림을 만드는 방법
 */
public class StreamMaking {
    static class objects {

        private int id;
        private String name;

        public objects(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {

        String[] string_arr = {"1", "2", "3"};
        int[] int_arr = {1, 2, 3};
        long[] long_arr = {1L, 2L, 3L};
        objects[] objects = {new objects(1, "1"), new objects(2, "2"), new objects(3, "3")};

        List<String> string_list = List.of(new String[]{"1", "2", "3"});
        List<Integer> int_list = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Long> long_list = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        List<objects> obj_list = new ArrayList<>(Arrays.asList(new objects(1, "1"), new objects(2, "2"), new objects(3, "3")));

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //

        // TODO 1. 다양한 타입의 스트림 생성하기
        // Array -> Stream
        // int와 long은 왜 전용 스트림으로 생성해야 하는가?
        /*
            Java 스트림(Stream)에서 int 배열과 int 리스트(List<Integer>)가 다른 형태로 다뤄지는 이유는 Java의 기본 타입(primitive type)과 참조 타입(reference type)의 차이 때문입니다.
            int 배열 (int[]):
                int 배열은 기본 타입인 int로 구성되어 있습니다.
                    기본 타입은 객체가 아니며, 제네릭(Generic)을 사용할 수 없습니다. 따라서 int 배열을 스트림으로 다룰 때에는 IntStream을 사용합니다.
                    IntStream은 기본 타입의 스트림으로, int 배열의 요소를 직접 다룰 수 있도록 도와줍니다.
            int 리스트 (List<Integer>):
                int 리스트는 참조 타입인 Integer로 구성되어 있습니다.
                    참조 타입은 객체이며, 제네릭을 사용할 수 있습니다. 따라서 int 리스트를 스트림으로 다룰 때에는 Stream<Integer>를 사용합니다.
                    Stream<Integer>은 객체 타입(Integer)의 스트림으로, Integer 객체를 요소로 가지는 int 리스트를 스트림으로 다룰 수 있도록 도와줍니다.
                    따라서 Java 스트림은 기본 타입과 참조 타입을 구분하여 다루기 때문에 int 배열과 int 리스트가 서로 다른 형태의 스트림으로 다뤄집니다. 이렇게 구분함으로써 성능 및 타입 안정성을 유지할 수 있습니다.
         */
        Stream<String> stringStream = Arrays.stream(string_arr);
        IntStream intStream = Arrays.stream(int_arr); // 전용 스트림
        LongStream longStream = Arrays.stream(long_arr); // 전용 스트림
        Stream<objects> objectsStream = Arrays.stream(objects);

        // List -> Stream
        Stream<String> stringStream1 = string_list.stream();
        Stream<Integer> intStream1 = int_list.stream();
        Stream<Long> longStream1 = long_list.stream();
        Stream<objects> objectsStream1 = obj_list.stream();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //

        // TODO 2. 가변 범위 스트림 생성(IntStream)
        // 1 ~ 10 범위의 스트림 생성
        IntStream stream1 = IntStream.range(1, 10);
        stream1.forEach(e -> System.out.print(e + " "));
        System.out.println();
        // 1 ~ 20 범위의 스트림 생성
        IntStream stream2 = IntStream.rangeClosed(1, 20);
        stream2.forEach(e -> System.out.print(e + " "));

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //

        // TODO 3. 난수 스트림 생성(IntSteream)
        // 특정 타입의 난수로 이루어진 스트림 생성
        IntStream stream = new Random().ints(10);
        stream.forEach(System.out::println);

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //

        // TODO 4. Iterate()를 사용한 무한 스트림 생성, 람다표현식 사용.
        Stream<Integer> stream3 = Stream.iterate(2, n -> n + 2); // 2, 4, 6, 8, 10, ...

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //

        // TODO 5. 파일 라인을 읽는 스트림 생성
        // 파일의 한 행(line)을 요소로 하는 스트림을 생성하기 위해 java.nio.file.Files 클래스에는 lines() 메소드가 정의되어 있습니다.
        // 또한, java.io.BufferedReader 클래스의 lines() 메소드를 사용하면 파일뿐만 아니라 다른 입력으로부터도 데이터를 행(line) 단위로 읽어 올 수 있습니다.
        Path path = Path.of("/file");
        try {
            Stream<String> fileStream = Files.lines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //

        // TODO 6. 빈 스트림 생성
        Stream<Integer> integerStream = Stream.empty();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
    }
}
