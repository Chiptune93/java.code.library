package Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamFinalOperation {
    public static void main(String[] args) {
        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 1. forEach()
        // 스트림 내 요소를 반복한다. 반환 타입이 void 라서 주로 출력 하는 용도로 많이 사용한다.
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        integerStream.forEach(System.out::println);
        System.out.println();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 2. reduce()
        // 스트림 내 요소를 소모하는 기능, 인수를 통해 첫번째 요소와 초기값을 사용할지, 첫번째와 두번째 요소를 사용할지 조정가능하다.
        Stream<Integer> integerStream1 = Stream.of(10, 20, 30, 40, 50);
        // 첫번째 요소 부터 시작하여 다음 요소와 계속 합하는 것을 의미.
        integerStream1.reduce((i1, i2) -> i1 + i2).stream().peek(System.out::println).collect(Collectors.toList());
        System.out.println();

        Stream<Integer> integerStream2 = Stream.of(1, 2, 3, 4, 5);
        // 초기값 100부터 시작하여 다음 요소를 계속 합하는 것을 의미.
        int result = integerStream2.reduce(100, (integer, integer2) -> integer + integer2).intValue();
        System.out.println(result);
        System.out.println();


        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 3. findFirst(), findAny()
        // 해당 스트림에서 특정 값을 찾는다. 찾은 첫번째 요소를 반환한다.
        // findFirst는 스트림 반복 시, 처음으로 만나는 요소, findAny는 위치 상관없이 첫 발견하는 요소를 반환한다.
        Stream<Integer> integerStream3 = Stream.of(1, 2, 3, 4, 99, 40);
        // 스트림에서 처음 만나는 짝수 값을 리턴한다.
        int find1 = integerStream3.filter(integer -> integer % 2 == 0).findFirst().get();
        System.out.println(find1);
        System.out.println();

        Stream<Integer> integerStream4 = Stream.of(1, 4, 3, 2, 99, 40);
        // 스트림에서 처음 만나는 짝수 값을 리턴한다.
        int find2 = integerStream4.filter(integer -> integer % 2 == 0).findAny().get();
        System.out.println(find2);
        System.out.println();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 4. anyMatch(), allMatch(), noneMatch()
        // 조건에 만족하는 값이 있는지, 없는지를 boolean 타입으로 반환한다.
        Stream<Integer> integerStream5 = Stream.of(10, 11, 5, 12, 13, 14, 15);
        // 원래 스트림에서 5보다 큰 값을 필터링 후, 5인 값이 있는지 검사한다.
        System.out.println(integerStream5.filter(integer -> integer > 5).anyMatch(integer -> integer == 5));
        System.out.println();

        Stream<Integer> integerStream6 = Stream.of(10, 10, 10, 10, 1, 10, 10);
        // 원래 스트림에서 1보다 큰 값만 필터링 한 후, 모든 값이 10인지 체크한다.
        System.out.println(integerStream6.filter(integer -> integer > 1).allMatch(integer -> integer == 10));
        System.out.println();

        Stream<Integer> integerStream7 = Stream.of(10, 10, 10, 10, 1, 10, 10);
        // 원래 스트림에서 1보다 큰 값만 필터링 한 후, 1인 값이 없는지 확인한다.
        System.out.println(integerStream7.filter(integer -> integer > 1).noneMatch(integer -> integer == 1));
        System.out.println();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 5. count(), min(), max()
        // 스트림 요소에 대한 카운팅, 최소, 최대 값을 구할 수 있다.
        Stream<String> stringStream1 = Stream.of("123", "23", "1");
        // 스트림 내 요소를 해당 스트링의 길이로 바꾼 후, 카운팅
        int cnt = (int) stringStream1.mapToInt(s -> s.length()).count();
        System.out.println(cnt);
        System.out.println();

        Stream<String> stringStream2 = Stream.of("123", "23", "1");
        // 스트림 내 요소를 해당 스트링의 길이로 바꾼 후, 최소 값을 구한다.
        int min = stringStream2.mapToInt(s -> s.length()).min().getAsInt();
        System.out.println(min);
        System.out.println();

        Stream<String> stringStream3 = Stream.of("123", "23", "1");
        // 스트림 내 요소를 해당 스트링의 길이로 바꾼 후, 최대 값을 구한다.
        int max = stringStream3.mapToInt(s -> s.length()).max().getAsInt();
        System.out.println(max);
        System.out.println();


        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 6. sum(), average()
        // 합계와 평균을 구할 수 있다.
        // 참조 타입은 계산할 수 없음.
        Stream<Integer> integerStream8 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum1 = integerStream8.mapToInt(i -> i).sum();
        // intStream 으로 계산해야 가능함. 참조타입인 경우 intStream으로 변환 후 수행.
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum2 = intStream.sum();
        System.out.println("sum1 -> " + sum1);
        System.out.println("sum2 -> " + sum2);
        System.out.println();

        IntStream intStream2 = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        double avg = intStream2.average().getAsDouble();
        System.out.println("avg -> " + avg);
        System.out.println();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 7. collect()
        // 스트림의 연산 등의 기타 작업 후, 최종으로 해당 요소들을 모아서 특정 타입으로 리턴
        // TODO 7-1. 스트림을 배열이나 컬렉션으로 변환 : toArray(), toCollection(), toList(), toSet(), toMap()
        // 스트림을 List로 변환.
        Stream<Integer> integerStream9 = Stream.of(1, 2, 3, 4, 5);
        List<Integer> integerList = integerStream9.collect(Collectors.toList());
        System.out.println("integerList -> " + integerList);
        System.out.println();

        Stream<Integer> integerStream10 = Stream.of(1, 2, 3, 4, 5);
        // 스트림을 List로 반환, LinkedList 콜렉션 객체로 변환하여 반환한다.
        List<Integer> integerList1 = integerStream10.collect(Collectors.toCollection(LinkedList::new));
        System.out.println("integerList1 -> " + integerList1);
        System.out.println();

        Stream<Integer> integerStream11 = Stream.of(1, 2, 3, 4, 5);
        // 스트림을 맵으로 변환, 변환 시에 키와 밸류 매퍼를 제공하면 그에 맞게 반환 한다.
        Map<String, Integer> map = integerStream11.collect(Collectors.toMap(integer -> integer.toString(), integer -> integer));
        System.out.println("map -> " + map);
        System.out.println();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 7-2. 요소의 통계와 연산 메소드와 같은 동작을 수행 : counting(), maxBy(), minBy(), summingInt(), averagingInt() 등
        // counting() : 요소의 개수 세기
        Stream<String> stringStream4 = Stream.of("A", "B", "C", "D", "E");
        long count = stringStream4.collect(Collectors.counting());
        System.out.println("요소 개수: " + count);
        System.out.println();

        // maxBy() : 비교를 통한 최대 값 찾기 <-> minBy()
        Stream<String> stringStream5 = Stream.of("Apple", "Banana", "Cherry", "Date", "Fig");
        // 문자열 길이를 기준으로 최대 값을 찾는 Comparator
        Comparator<String> lengthComparator = Comparator.comparing(String::length);
        Optional<String> maxLengthString = stringStream5.max(lengthComparator);
        if (maxLengthString.isPresent()) {
            System.out.println("가장 긴 문자열: " + maxLengthString.get());
        } else {
            System.out.println("스트림이 비어 있습니다.");
        }
        System.out.println();

        // summingInt() : 스트림 요소의 정수 필드 합계를 계산할 때 사용한다.
        List<Employee> employees = List.of(
                new Employee("Alice", 1000),
                new Employee("Bob", 1500),
                new Employee("Charlie", 1200),
                new Employee("David", 2000)
        );
        int totalSalary = employees.stream().collect(Collectors.summingInt(Employee::getSalary));
        System.out.println("총 급여 합계: " + totalSalary);
        System.out.println();

        // averagingInt() : 스트림 요소의 정수 필드의 평균 값을 구할 때 사용한다.
        List<Employee> employees2 = List.of(
                new Employee("Alice", 1000),
                new Employee("Bob", 1500),
                new Employee("Charlie", 1200),
                new Employee("David", 2000)
        );

        double averageSalary = employees2.stream()
                .collect(Collectors.averagingInt(Employee::getSalary));

        System.out.println("평균 급여: " + averageSalary);
        System.out.println();


        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 7-3. 요소의 소모와 같은 동작을 수행 : reducing(), joining()
        // reducing() : reduce() 와 동일하나 최종 연산 단계에서 사용함.
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
        // 초기값 없이 요소를 합산
        Optional<Integer> sum = numbers.collect(Collectors.reducing((a, b) -> a + b));
        sum.ifPresent(r -> System.out.println("합계: " + r));
        System.out.println();

        // joining() : 최종 연산 단계에서 문자열을 결합할 때 사용.
        Stream<String> words = Stream.of("Hello", "World", "Java");
        // 스트림의 모든 문자열을 공백으로 구분하여 연결
        String w = words.collect(Collectors.joining(" "));
        System.out.println("결합된 문자열: " + w);


        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
        // TODO 7-4. 요소의 그룹화와 분할 : groupingBy(), partitioningBy()
        // groupingBy() : 특정 그룹으로 그룹화 할 때 사용.
        List<Student> students = Arrays.asList(
                new Student("Alice", "Math", 90),
                new Student("Bob", "Math", 85),
                new Student("Charlie", "History", 75),
                new Student("David", "Math", 88),
                new Student("Eve", "History", 82)
        );

        // 과목별로 학생들을 그룹화합니다.
        Map<String, List<Student>> studentsBySubject = students.stream()
                .collect(Collectors.groupingBy(Student::getSubject));

        // 결과를 출력합니다.
        studentsBySubject.forEach((subject, subjectStudents) -> {
            System.out.println("과목: " + subject);
            subjectStudents.forEach(student -> {
                System.out.println("  학생 이름: " + student.getName() + ", 점수: " + student.getScore());
            });
        });
        System.out.println();

        // partitioningBy() : 특정 그룹으로 분할할 때 사용.
        Stream<String> stream = Stream.of("HTML", "CSS", "JAVA", "PHP");
        Map<Boolean, List<String>> patition = stream.collect(Collectors.partitioningBy(s -> (s.length() % 2) == 0));
        List<String> oddLengthList = patition.get(false);
        System.out.println(oddLengthList);
        List<String> evenLengthList = patition.get(true);
        System.out.println(evenLengthList);
        System.out.println();

        // ---------------------------------------------------------------------------------------------------------- //
        // ---------------------------------------------------------------------------------------------------------- //
    }

    static class Employee {
        private String name;
        private int salary;

        public Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public int getSalary() {
            return salary;
        }
    }

    static class Student {
        private String name;
        private String subject;
        private int score;

        public Student(String name, String subject, int score) {
            this.name = name;
            this.subject = subject;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public String getSubject() {
            return subject;
        }

        public int getScore() {
            return score;
        }
    }

}
