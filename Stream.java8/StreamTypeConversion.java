package Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTypeConversion {

    /**
     * 참조 정수 배열 -> 리스트
     */
    void ArrayToList() {
        // 정수 배열 생성
        Integer[] numbersArray = {1, 2, 3, 4, 5};

        // 배열을 스트림으로 변환하고, 스트림을 리스트로 변환
        List<Integer> numbersList = Arrays.stream(numbersArray)
                .collect(Collectors.toList());

        // 리스트 출력
        System.out.println("리스트: " + numbersList);
    }

    /**
     * 리스트 -> 참조 정수 배열
     */
    void ListToArray() {
        // 정수 리스트 생성
        List<Integer> numbersList = new ArrayList<>();
        numbersList.add(1);
        numbersList.add(2);
        numbersList.add(3);
        numbersList.add(4);
        numbersList.add(5);

        // 리스트를 스트림으로 변환하고, 스트림을 배열로 변환
        Integer[] numbersArray = numbersList.stream()
                .toArray(Integer[]::new);

        // 배열 출력
        System.out.println("배열: " + Arrays.toString(numbersArray));
    }

    /**
     * 기본 정수 배열 -> 리스트
     */
    void intArrToList() {
        // int 배열 생성
        int[] intArray = {1, 2, 3, 4, 5};

        // 기본 타입(int) 배열을 스트림으로 변환
        IntStream intStream = IntStream.of(intArray);

        // 스트림을 다른 타입(List<Integer>)으로 변환
        List<Integer> integerList = intStream.boxed().collect(Collectors.toList());

        // 리스트 출력
        System.out.println("리스트: " + integerList);
    }

    /**
     * 리스트 -> 기본 정수 배열
     */
    void ListIntegerTointArr() {
        // 정수 리스트 생성
        List<Integer> numbersList = new ArrayList<>();
        numbersList.add(1);
        numbersList.add(2);
        numbersList.add(3);
        numbersList.add(4);
        numbersList.add(5);

        // 리스트를 스트림으로 변환
        IntStream intStream = numbersList.stream()
                .mapToInt(Integer::intValue);

        // 스트림을 다른 타입(int 배열)으로 변환
        int[] intArray = intStream.toArray();

        // 배열 출력
        System.out.println("배열: " + Arrays.toString(intArray));
    }

    /**
     * 문자열 배열 <-> 리스트
     */
    void string() {
        // 문자열 배열 생성
        String[] stringArray = {"apple", "banana", "cherry", "date"};

        // 문자열 배열을 스트림으로 변환
        Stream<String> stringStream = Arrays.stream(stringArray);

        // 스트림을 리스트로 변환
        List<String> stringList = stringStream.collect(Collectors.toList());

        // 리스트를 다시 배열로 변환
        String[] newArray = stringList.toArray(new String[0]);

        // 배열 출력
        System.out.println("다시 배열로 변환된 문자열 배열: " + Arrays.toString(newArray));
    }

    /**
     * Date 객체(자바 제공) <-> 리스트
     */
    void dateObj() {
        // Date 객체 리스트 생성
        List<Date> dateList = Stream.of(new Date(), new Date(), new Date())
                .collect(Collectors.toList());

        // Date 객체 리스트를 스트림으로 변환
        Stream<Date> dateStream = dateList.stream();

        // 스트림을 배열로 변환
        Date[] dateArray = dateStream.toArray(Date[]::new);

        // 배열을 다시 리스트로 변환
        List<Date> newDateList = Arrays.asList(dateArray);

        // 리스트 출력
        System.out.println("다시 리스트로 변환된 Date 객체 리스트: " + newDateList);
    }

    /**
     * 사용자 지정 객체 <-> 리스트
     */
    void customObj() {
        // 사용자 정의 객체(Student) 리스트 생성
        List<Student> studentList = Arrays.asList(
                new Student("Alice", 25),
                new Student("Bob", 30),
                new Student("Charlie", 22)
        );

        // 사용자 정의 객체(Student) 리스트를 스트림으로 변환
        Stream<Student> studentStream = studentList.stream();

        // 스트림을 배열로 변환
        Student[] studentsArray = studentStream.toArray(Student[]::new);

        // 배열을 다시 리스트로 변환
        List<Student> newStudentList = Arrays.asList(studentsArray);

        // 리스트 출력
        System.out.println("다시 리스트로 변환된 사용자 정의 객체(Student) 리스트: " + newStudentList);
    }

    static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', age=" + age + '}';
        }
    }

}
