package com.example.login;


import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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

        Set<? super String> set = new HashSet<>(Arrays.asList("kevin"));

        set.addAll(Arrays.asList("jack", "may"));
        assertTrue(set.containsAll(Arrays.asList("may", "jack", "kevin")));
    }

    @Test
    public void addTreeSet(){

        TreeSet<? super String> treeSet = new TreeSet<>(Comparator.comparing(String::length, Comparator.naturalOrder()));
        treeSet.addAll(Arrays.asList("kevin", "jack"));

        assertEquals(2, treeSet.size());
        assertEquals(
                "jack",
                treeSet.pollFirst()
        );

        assertTrue(!treeSet.contains("jack"));
        assertEquals(1, treeSet.size());
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

        TreeSet<? super String> treeSet = new TreeSet<>(Arrays.asList("kevin"));

        treeSet.add("jack");

        treeSet =
        treeSet
        .stream().map(o -> o.toString())
        .sorted(Comparator.comparing(String::length, Comparator.naturalOrder()))
        .collect(Collectors.toCollection(TreeSet::new));

        treeSet.removeIf(s -> s.equals("jack"));

        assertEquals(1, treeSet.size());
        assertTrue(treeSet.contains("kevin"));
    }

    @Test
    public void headAndTailAndSubset(){

        TreeSet<? super String> treeSet = new TreeSet<>(Comparator.comparing(String::length, Comparator.naturalOrder()));
        treeSet.addAll(Arrays.asList("may", "kevin", "jack"));

        assertEquals(3, treeSet.size());

        assertEquals(2, treeSet.headSet("kevin").size());
        assertEquals(1, treeSet.headSet("jack").size());
        assertTrue(treeSet.headSet("may").isEmpty());

        assertEquals(3, treeSet.tailSet("may").size());
        assertEquals(2, treeSet.tailSet("jack").size());
        assertEquals(1, treeSet.tailSet("kevin").size());

        assertEquals(2, treeSet.subSet("may", "kevin").size());
    }


    @Test
    public void quadraticSet(){

        List<? super TreeSet<? extends String>> treeSetList = new ArrayList<>();

        treeSetList.add(treeSetList.size(), new TreeSet<>(Arrays.asList("kevin")));
        treeSetList.add(treeSetList.size(), new TreeSet<>(Arrays.asList("jack")));

        assertEquals(2, treeSetList.size());
        assertEquals(1, ((TreeSet<? extends String>) treeSetList.get(0)).size());
        assertEquals(1, ((TreeSet<? extends String>) treeSetList.get(treeSetList.size() - 1)).size());

        treeSetList
                .sort(Comparator.comparing(
                        o -> ((TreeSet<String>) o).first().length(),
                        Comparator.naturalOrder()
                ));

        Iterator<TreeSet<? extends String>> treeSetIterator = (Iterator<TreeSet<? extends String>>) treeSetList.listIterator();

        while (treeSetIterator.hasNext()){

            TreeSet<String> treeSet = (TreeSet<String>) treeSetIterator.next();
            treeSet.forEach(System.out::println);
        }
    }

    @Test
    public void decending(){

        List<? super TreeSet<? super String>> treeSetList = new ArrayList<>();

        treeSetList.add(treeSetList.size(), new TreeSet<>(Arrays.asList("may", "jack")));
        treeSetList.add(treeSetList.size(), new TreeSet<>(Arrays.asList("kevin", "peter")));

        treeSetList
                .stream()
                .forEach(
                        treeSet -> {

                            ((TreeSet<? super String>) treeSet)
                                    .descendingSet()
                                    .forEach(System.out::println);
                        }
                );

        treeSetList
                .stream()
                .forEach(
                        treeSet -> {

                            Iterator<? super String> iterator = ((TreeSet<String>) treeSet).descendingIterator();

                            iterator.forEachRemaining(System.out::println);
                        }
                );
    }
}
