package com.example.login;


import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


@Slf4j
public class ListTest {

    /**
     * List.add(int index, E element) : boolean
     */
    @Test
    public void addAndRemove(){

        List<? super String> list = new ArrayList<>(Arrays.asList("may"));

        list.add(list.size(), "kevin");
        assertEquals(2, list.size());
        assertTrue(list.containsAll(Arrays.asList("kevin", "may")));

        assertEquals(list.remove(list.size()-1), "kevin");
        assertTrue(!list.contains("kevin"));
        assertEquals(1, list.size());
    }

    /**
     * List.addAll(int index, Collection</? extends E> c) : boolean
     */
    @Test
    public void addAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(lists.size(), Arrays.asList("may"));

        assertEquals(2, lists.size());
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
        assertTrue(((List<? extends String>)(lists.get(lists.size() - 1))).contains("may"));
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

        assertEquals(0, lists.indexOf(Arrays.asList("kevin", "jack")));
        assertEquals(1, lists.indexOf(Arrays.asList("may")));
    }

    /**
     * List.listIterator() : ListIterator<E>
     */
    @Test
    public void listIterator(){

        List<? extends String> list = Arrays.asList("kevin", "jack");

        ListIterator<? super String> listIterator = (ListIterator<String>) list.listIterator();

        while (listIterator.hasNext()){

            String o = (String) listIterator.next();

            if(o.startsWith("k")){

                listIterator.previous();
                listIterator.set(o.toUpperCase());

                o = (String) listIterator.next();
            }

            log.warn(o);
        }
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

                    ListIterator<? super String> listIterator = (ListIterator<? super String>) ((List<? extends String>) list).listIterator();

                    while (listIterator.hasNext()){

                        String o = (String) listIterator.next();

                        if(o.startsWith("j")){

                            listIterator.previous();
                            listIterator.set(o.toUpperCase());
                            o = (String) listIterator.next();
                        }

                        log.debug(o);
                    }
                }
        );
    }


    /**
     * List.sort(Comparator</? super E> c) : void
     */
    @Test
    public void sort(){

        List<? extends String> list = Arrays.asList("jack", "may", "kevin");

        assertEquals(3, list.size());

        list.sort(Comparator.comparing(String::length, Comparator.naturalOrder()));

        assertEquals(
                "may",

                list
                .stream()
                .findFirst()
                .get()
        );


        list.sort(Comparator.comparing(String::length, Comparator.reverseOrder()));

        assertEquals(
                "kevin",

                list
                .stream()
                .findFirst()
                .get()
        );
    }

    /**
     * List.sort(Comparator</? super E> c) : void
     */
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

                ((List<? extends String>) lists.get(0))
                .stream()
                .findFirst()
                .get()
        );

        assertEquals(
                "may",

                ((List<? extends String>) lists.get(1))
                        .stream()
                        .findFirst()
                        .get()
        );

        ListIterator<? super List<? extends String>> listsIterator = lists.listIterator();

        while (listsIterator.hasNext()){

            List<String> list = (List<String>) listsIterator.next();

            if(lists.indexOf(list) == 0){

                list.sort(Comparator.comparing(String::length, Comparator.naturalOrder()));
            }

            else if(lists.indexOf(list) == 1){

                list.sort(Comparator.comparing(String::length, Comparator.reverseOrder()));
            }
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


    /**
     * List.replaceAll(UnaryOperator<E> operator> : void
     */
    @Test
    public void replaceAll(){

        List<? super String> list = Arrays.asList("kevin", "jack");

        list
        .replaceAll(s -> ((String) s).toUpperCase());

        assertTrue(list.containsAll(Arrays.asList("KEVIN", "JACK")));
    }


}
