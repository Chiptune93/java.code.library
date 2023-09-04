package Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamIntermediateOperation {
    public static void main(String[] args) {
        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 1. map() : 해당 스트림의 요소를 전달하여 반환되는 값으로 이루어진 새로운 스트림을 리턴한다.
        Stream<String> stream = Stream.of("HTML", "CSS", "JAVA", "JAVASCRIPT");
        stream.map(s -> s.length()).forEach(System.out::println);

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 2. flatMap() : 메서드는 각 요소를 다른 스트림으로 매핑한 후 이러한 스트림들을 하나로 평면화(병합)하는 데 사용됩니다.
        // 주로 중첩된 컬렉션을 펼치거나 다른 작업을 수행할 때 유용합니다. flatMap은 각 요소를 변환하고, 변환된 요소를 모두 하나의 스트림으로 합칩니다.

        // 정수 리스트 3개를 하나의 리스트로 합치는 작업.
        List<Integer> integerList1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> integerList2 = new ArrayList<>(Arrays.asList(10, 20, 30));
        List<Integer> integerList3 = new ArrayList<>(Arrays.asList(100, 200, 300));
        Stream<List<Integer>> stream3 = Stream.of(integerList1, integerList2, integerList3);
        List<Integer> integerList = stream3.flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(integerList);

        // 정수 리스트로 이루어진 스트림에서 각 리스트의 1번째 인수로 이루어진 리스트를 가공하는 과정.
        Stream<List<Integer>> stream4 = Stream.of(integerList1, integerList2, integerList3);
        List<Integer> integerList4 = stream4.mapToInt(i -> i.get(0)).boxed().collect(Collectors.toList());
        System.out.println(integerList4);

        // toDouble
        Stream<List<Integer>> stream5 = Stream.of(integerList1, integerList2, integerList3);
        List<Double> integerList5 = stream4.mapToDouble(i -> i.get(0)).boxed().collect(Collectors.toList());

        // toLong
        Stream<List<Integer>> stream6 = Stream.of(integerList1, integerList2, integerList3);
        List<Long> integerList6 = stream4.mapToLong(i -> i.get(0)).boxed().collect(Collectors.toList());

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 3. sorted
        // 스트림 내부 값을 정렬하는 기능.
        Stream<Integer> integerStream = Stream.of(10,4,5,6,7,1,3);
        integerStream.sorted().peek(System.out::println).collect(Collectors.toList()); // 1,3,4,5,6,7,10
        System.out.println();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 4. limits
        // 스트림의 반복 제한 횟수를 지정할 수 있다.
        Stream<Integer> integerStream1 = Stream.of(1,2,3,4,5,6,7,8);
        integerStream1.limit(5).peek(System.out::println).collect(Collectors.toList()); // 1,2,3,4,5
        System.out.println();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 5. skips
        // 스트림에서 지정한 횟수 만큼의 인덱스를 건너 뛰고 이후 부터 반복한다.
        Stream<Integer> integerStream2 = Stream.of(1,2,3,4,5);
        integerStream2.skip(3).peek(System.out::println).collect(Collectors.toList());
        System.out.println();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 6. peek
        // 위에서 사용한 것과 같이, 스트림 중간에 내용을 출력할 수 있다.
        Stream<String> stringStream = Stream.of("이건","뭘까","요?");
        stringStream.peek(System.out::println).collect(Collectors.toList());
        System.out.println();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 7. Stream Filtering
        // 스트림을 원하는 조건에 맞게 필터링 할 수 있다.
        IntStream stream1 = IntStream.of(7, 5, 5, 2, 1, 2, 3, 5, 4, 6);
        IntStream stream2 = IntStream.of(7, 5, 5, 2, 1, 2, 3, 5, 4, 6);
        // 스트림에서 중복된 요소를 제거함.
        stream1.distinct().forEach(e -> System.out.print(e + " "));
        System.out.println();
        // 스트림에서 홀수만을 골라냄.
        stream2.filter(n -> n % 2 != 0).forEach(e -> System.out.print(e + " "));

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
    }
}
