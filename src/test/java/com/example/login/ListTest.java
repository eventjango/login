package com.example.login;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@Slf4j
public class ListTest {

    /**
     * List.add(int index, E element) : boolean
     */
    @Test
    public void add(){

        List<? super String> list = new ArrayList<>(Arrays.asList("kevin"));

        list.add(list.size(), "may");
        assertEquals(2, list.size());
        assertTrue(list.containsAll(Arrays.asList("kevin", "may")));
    }

    /**
     * List.addAll(int index, Collection</? extends E> c) : boolean
     */
    @Test
    public void addAll(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(lists.size(), Arrays.asList("may"));

        assertEquals(2, lists.size());
    }

    /**
     * List.remove(int index) : Object
     */
    @Test
    public void remove(){

        List<? super String> list = new ArrayList<>(Arrays.asList("kevin"));

        list.add(list.size(), "may");
        assertEquals(2, list.size());

        assertEquals(list.remove(0), "kevin");
        assertTrue(list.contains("may"));
    }


    /**
     * List.remove(int index) : Object
     */
    @Test
    public void removeAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(lists.size(), Arrays.asList("may"));

        assertEquals(2, lists.size());
        assertEquals(lists.remove(0), Arrays.asList("kevin", "jack"));
        assertTrue(((List<? extends String>)lists.get(lists.size() - 1)).contains("may") );
    }


    /**
     * List.indexOf(Object o) : int
     */
    @Test
    public void indexOf(){

        List<? extends String> list = Arrays.asList("kevin", "may");

        assertEquals(0, list.indexOf("kevin"));
        assertEquals(1, list.indexOf("may"));
    }


    /**
     * List.indexOf(Object o) : int
     */
    @Test
    public void indexOfAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(lists.size(), Arrays.asList("may"));

        assertEquals(1, lists.indexOf(Arrays.asList("may")));
        assertEquals(0, lists.indexOf(Arrays.asList("kevin", "jack")));
    }


    /**
     * List.listIterator() : ListIterator<E>
     */
    @Test
    public void listIterator(){

        List<? extends String> list = Arrays.asList("kevin", "jack");

        ListIterator<? super String> listIterator = (ListIterator<String>) list.listIterator();

        while (listIterator.hasNext()){

            String value = (String) listIterator.next();

            if(value.startsWith("k")){

                listIterator.previous();
                listIterator.set(value.toUpperCase());
                value = (String) listIterator.next();
            }
        }

        /*listIterator.forEachRemaining(n -> log.debug(n.toString()));*/
    }


    /**
     * List.listIterator() : ListIterator<E>
     */
    @Test
    public void listIteratorAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(lists.size(), Arrays.asList("may"));

        assertEquals(2, lists.size());

        lists.forEach(

                list -> {

                    ListIterator<? super String> listIterator = ((List<String>) list).listIterator();

                    while (listIterator.hasNext()){

                        String value = (String) listIterator.next();

                        if(value.startsWith("m")){

                            listIterator.previous();
                            listIterator.set(value.toUpperCase());

                            value = (String) listIterator.next();
                        }

                        log.debug(value);
                    }


                }
        );

    }

    /**
     * List.replaceAll(UnaryOperator<E> operator) : void
     */
    @Test
    public void replaceAll(){

        List<? super String> list = Arrays.asList("kevin", "may");

        list.replaceAll(s -> ((String) s).toUpperCase());

        assertTrue(list.containsAll(Arrays.asList("KEVIN", "MAY")));
    }


    @Test
    public void sort(){

        List<? extends String> list = Arrays.asList("kevin", "jack", "may");

        assertEquals(3, list.size());

        list.sort(Comparator.comparing(String::length, Comparator.naturalOrder()));
        list.forEach(System.out::println);

        list.sort(Comparator.comparing(String::length, Comparator.reverseOrder()));
        list.forEach(System.out::println);
    }


    @Test
    public void sortAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(lists.size(), Arrays.asList("may", "peter"));

        lists
                .forEach(

                        list -> {

                            ((List<String>) list).sort(Comparator.comparing(String::length, Comparator.naturalOrder()));
                        }

                );


        assertEquals(
                "jack",

                ((List<String>) lists.get(0))
                .stream()
                .findFirst()
                .get()
        );

        assertEquals(
                "may",

                ((List<String>) lists.get(1))
                        .stream()
                        .findFirst()
                        .get()
        );


        ListIterator<? super List<? extends String>> listIterator = lists.listIterator();

        while (listIterator.hasNext()){

            List<? extends String> list = (List<String>) listIterator.next();

            if(lists.indexOf(list) == 0)

                list.sort(Comparator.comparing(String::length, Comparator.naturalOrder()));

            else if(lists.indexOf(list) == 1)

                list.sort(Comparator.comparing(String::length, Comparator.reverseOrder()));
        }


        assertEquals(
                "jack",

                ((List<? extends String>) lists.get(0))
                        .stream()
                        .findFirst()
                        .get()
        );

        assertEquals(
                "peter",

                ((List<? extends String>) lists.get(1))
                        .stream()
                        .findFirst()
                        .get()
        );

    }

}
