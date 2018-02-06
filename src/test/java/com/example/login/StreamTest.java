package com.example.login;

import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * 1. 생성
 * 2. 필터링
 * 3. 변환
 * 4. 매핑
 * 5. 수집
 * 6. 리덕션(리듀스, 리듀싱) - 환산
 */
@Slf4j
public class StreamTest {


    /**
     *  Stream.of(T... values) : Stream<T> (T에 대한 pool)
     *  Arrays.stream(T[] array) : Stream<T> (배열과 1:1 대응)
     *  Stream.empty() : Stream<T>
     */
    @Test
    public void create(){

        assertEquals(2, Stream.of("kevin", "may").count());

        Stream.of(Arrays.asList("may", "jack"))
                .forEach(
                        list -> {
                            list.stream().forEach(System.out::println);
                        }
                );


        assertEquals(
                2,
                Arrays.stream(Arrays.asList("kevin", "may").toArray()).count()
        );

        Stream<String> empty = Stream.empty();

        List<? super String> result = empty.collect(Collectors.toList());
        result.addAll(Arrays.asList("may", "peter"));

        result
        .stream()
        .map(n -> n.toString().toUpperCase())
        .forEach(System.out::println);

        IntStream
        .range(0, 9999)
                .sequential()
                .limit(10)
        .forEach(System.out::println);
    }

    @Test
    public void optional(){

        Optional<String> result =

        Arrays.stream(Arrays.asList("kevin", "may").toArray())
                .map(Object::toString)
                .findFirst();

        Optional<List<String>> result2 =

        result
        .flatMap(s -> Optional.of(new ArrayList<>(Arrays.asList(s))))
                .map(Function.identity());


        if(result2.isPresent()){
            assertEquals(1, result2.orElseGet(() -> Arrays.asList()).size());
        }

        Optional<String> empty =
                Optional
                        .empty()
                        .flatMap(o -> Optional.ofNullable("value"));

    }


    @Test
    public void fromTo(){

        long count = Arrays.stream(Arrays.asList("kevin", "may").toArray(), 0, 2).count();

        System.out.println(count);
    }


    @Test
    public void generate(){

        Stream<String> stringStream = Stream.generate(() -> "may").limit(10);
        assertEquals(1, stringStream.distinct().count());
    }

    @Test
    public void iterate(){

        Stream<Integer> integerStream = Stream.iterate(1, n -> n + 1).limit(10);
        integerStream.forEach(o -> log.debug(o.toString()));
    }


    @Test
    public void filter(){

        boolean result =

        Stream
        .empty()
        .collect(Collectors.toCollection(() -> Arrays.asList("kevin", "may")))
        .stream()
        .filter(o -> o.toString().startsWith("m"))
        .anyMatch(o -> o.toString().equals("may"));

        assertTrue(result);
    }


    @Test
    public void sort(){

        Optional<String> result =

        Stream
        .empty()
        .collect(Collectors.toCollection(() -> Arrays.asList("jack", "may")))
        .stream()
        .sorted(Comparator.comparing(s -> s.toString().length(), Comparator.reverseOrder())).map(n -> n.toString())
        .findFirst();

        assertTrue("jack".equals(result.get()));
    }


    @Test
    public void map(){

        boolean result =

        Stream
        .empty()
        .collect(Collectors.toCollection(() -> Arrays.asList("may", "tommy")))
        .stream()
        .map(o -> o.toString().toUpperCase())
        .allMatch(((Predicate)n -> "MAY".equals(n)).or(n -> "TOMMY".equals(n)));

        assertTrue(result);
    }


    @Test
    public void flatMap(){

        boolean result =

        Stream
        .empty()
        .collect(Collectors.toCollection(() -> Arrays.asList("kevin", "may")))
        .stream()
        .flatMap(o -> Stream.of(o.toString().toUpperCase()))
        .allMatch(((Predicate)o -> o.toString().equals("KEVIN")).or(o -> o.toString().equals("MAY")));

        assertTrue(result);
    }

    @Test
    public void concat(){

        Stream
                .concat(
                        Stream
                        .empty()
                        .collect(Collectors.toCollection(() -> Arrays.asList("may", "kevin")))
                        .stream()
                        .flatMap(o -> Stream.of(o)),

                        Stream
                        .empty()
                        .collect(Collectors.toCollection(() -> Arrays.asList("jack", "peter")))
                        .stream()
                        .flatMap(o -> Stream.of(o.toString().toUpperCase()))
                )

                .forEach(System.out::println);
    }


    @Test
    public void maxAndMin(){

        assertEquals(0, IntStream.range(0, 5).min().getAsInt());
        assertEquals(4, IntStream.range(0, 5).max().getAsInt());


        Supplier<Stream<String>> streamSupplier = () ->
                Stream
                .empty()
                .collect(Collectors.toCollection(() -> Arrays.asList("kevin", "jack")))
                .stream()
                .flatMap(o -> Stream.of(o.toString()));


        Optional<String> max = streamSupplier.get().max(Comparator.comparing(String::length));

        assertEquals("kevin", max.get());


        Optional<String> min = streamSupplier.get().min(Comparator.comparing(String::length));

        assertEquals("jack", min.get());
    }


    @Test
    public void sum(){

        Stream<String> values =

        Stream
        .empty()
        .collect(Collectors.toCollection(() -> Arrays.asList("may", "kevin")))
        .stream()
        .flatMap(o -> Stream.of(o.toString()));

        long sum = values.collect(Collectors.summarizingInt(String::length)).getSum();

        String value =
        Stream
        .empty()
        .collect(Collectors.toCollection(() -> Arrays.asList("may", "kevin")))
        .stream()
        .flatMap(o -> Stream.of(o.toString()))
        .collect(Collectors.joining());

        assertEquals(value.length(), sum);
    }


    @Test
    public void findFirstAndFindAny(){

        Supplier<Stream<String>> streamSupplier = () ->
                Stream
                .empty()
                .collect(Collectors.toCollection(() -> Arrays.asList("jack", "may", "kevin")))
                .stream()
                .flatMap(o -> Stream.of(o.toString()));

        Optional<String> first =

        streamSupplier
                .get()
                .filter(s -> s.substring(1, 2).equals("a"))
                .findFirst();

        System.out.println("first : " + first.orElse(""));


        Optional<String> any =
        streamSupplier
                .get()
                .filter(s -> s.substring(1, 2).equals("a"))
                .parallel()
                .findAny();

        System.out.println("any : " + any.orElse(""));
    }

    @Test
    public void anyMatch(){

        boolean result =

        Stream
        .empty()
        .collect(Collectors.toCollection(() -> Arrays.asList("kevin", "jack")))
        .parallelStream()
        .flatMap(o -> Stream.of(o.toString()))
        .anyMatch(o -> o.contains("jack"));

        assertTrue(result);
    }

    @Test
    public void noneMatch(){

        boolean result =

        Stream
        .empty()
        .collect(Collectors.toCollection(() -> Arrays.asList("may", "peter")))
        .parallelStream()
        .flatMap(o -> Stream.of(o.toString()))
        .noneMatch(o -> o.contains("kevin"));

        assertTrue(result);

    }


    @Test
    public void toArray(){

        String[] result =

        Stream
        .empty()
        .collect(Collectors.toCollection(() -> Arrays.asList("kevin", "jack")))
        .parallelStream()
        .flatMap(o -> Stream.of(o.toString()))
        .toArray(n -> new String [n]);

        System.out.println(Arrays.toString(result));
    }

    @Test
    public void joining(){

        String value =

        Stream
        .empty()
        .collect(Collectors.toCollection(() -> Arrays.asList("jack", "may")))
        .parallelStream()
        .map(o -> o.toString())
                .sorted(Comparator.comparing(String::length, Comparator.naturalOrder()))
        .collect(Collectors.joining());

        System.out.println(value);
    }


    /**
     * 집계로 리듀싱
     */
    @Test
    public void summarizing(){

        IntSummaryStatistics intSummaryStatistics =

        Stream
        .empty()
        .collect(Collectors.toCollection(() -> Arrays.asList("kevin", "may")))
        .parallelStream()
        .map(o -> o.toString())
        .collect(Collectors.summarizingInt(String::length));

        System.out.println("sum : " + intSummaryStatistics.getSum());
        System.out.println("max : " + intSummaryStatistics.getMax());
        System.out.println("min : " + intSummaryStatistics.getMin());
        System.out.println("average : " + intSummaryStatistics.getAverage());
    }


}
