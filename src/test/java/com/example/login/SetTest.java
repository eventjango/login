package com.example.login;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class SetTest {

    @Test
    public void add(){

        Set<? super String> set = new HashSet<>(Arrays.asList("kevin"));

        set.add("jack");

        assertEquals(2, set.size());
    }

    @Test
    public void addAll(){

        Set<? super String> set = new HashSet<>(Arrays.asList("may"));

        set.addAll(Arrays.asList("kevin", "peter"));
        assertTrue(set.containsAll(Arrays.asList("peter", "may", "kevin")));
    }

    @Test
    public void addTreeSet(){
        TreeSet<? super String> treeSet = new TreeSet<>(Comparator.comparing(String::length, Comparator.naturalOrder()));

        treeSet.addAll(Arrays.asList("jack", "kevin"));
        assertEquals(2, treeSet.size());

        assertEquals("jack", treeSet.pollFirst());
        assertEquals(1, treeSet.size());
        assertTrue(!treeSet.contains("jack"));
    }

    @Test
    public void remove(){

        HashSet<? super String> hashSet = new HashSet<>(Arrays.asList("kevin"));

        hashSet.addAll(Arrays.asList("jack", "may"));
        assertEquals(3, hashSet.size());

        hashSet.removeAll(Arrays.asList("jack", "kevin"));

        assertEquals(1, hashSet.size());
        assertTrue(hashSet.contains("may"));
    }

    @Test
    public void removeTreeSet(){

        TreeSet<? super String> treeSet = new TreeSet<>(Arrays.asList("may"));

        treeSet.add("kevin");
        treeSet.removeIf(s -> ((String)s).equals("may"));

        assertEquals(1, treeSet.size());
        assertTrue(treeSet.contains("kevin"));
    }


    @Test
    public void headAndTailAndSubset(){

        TreeSet<? super String> treeSet = new TreeSet<>(Comparator.comparing(String::length, Comparator.naturalOrder()));

        //----------- may, jack, kevin (decending)

        treeSet.addAll(Arrays.asList("may", "kevin", "jack"));
        assertEquals(3, treeSet.size());


        //----------- headSet 초과의 개념.

        assertEquals(2, treeSet.headSet("kevin").size());
        assertEquals(1, treeSet.headSet("jack").size());
        assertTrue(treeSet.headSet("may").isEmpty());


        //----------- tailSet 이하의 개념.
        assertEquals(3, treeSet.tailSet("may").size());
        assertEquals(2, treeSet.tailSet("jack").size());
        assertEquals(1, treeSet.tailSet("kevin").size());


        //----------- subSet(a, b) a이상 b미만의 개념.
        assertEquals(1, treeSet.subSet("jack", "kevin").size());
    }

    @Test
    public void quadraticSet(){

        List<? super TreeSet<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), new TreeSet<>(Arrays.asList("may")));
        lists.add(lists.size(), new TreeSet<>(Arrays.asList("kevin")));

        assertEquals(2, lists.size());
        assertEquals(1, ((TreeSet<String>)lists.get(0)).size());
        assertEquals(1, ((TreeSet<String>)lists.get(lists.size() - 1)).size());

        ListIterator<TreeSet<? extends String>> listIterator = (ListIterator<TreeSet<? extends String>>) lists.listIterator();

        while (listIterator.hasNext()){

            TreeSet<String> value = (TreeSet<String>) listIterator.next();
            value.forEach(System.out::println);
        }
    }


    @Test
    public void decending(){

        List<? super TreeSet<? super String>> lists = new ArrayList<>();

        lists.add(lists.size(), new TreeSet<>(Arrays.asList("jack", "may")));
        lists.add(lists.size(), new TreeSet<>(Arrays.asList("kevin", "peter")));

        lists
        .stream()
        .forEach(

                set -> {

                    ((TreeSet<String>) set)
                            .descendingSet()
                            .forEach(System.out::println);
                }
        );


        lists
        .stream()
        .forEach(

                set -> {

                    Iterator<? super String> iterator = ((TreeSet<String>) set).descendingIterator();

                    iterator.forEachRemaining(n -> log.debug(n.toString().toUpperCase()));
                }
        );
    }

}
