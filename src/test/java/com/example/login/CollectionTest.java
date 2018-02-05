package com.example.login;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

@Slf4j
public class CollectionTest {


    /**
     * Collection.add(E element) : boolean
     */
    @Test(expected = UnsupportedOperationException.class)
    public void add(){

        Collection<? super String> collection = Collections.singletonList("kevin");

        assertNotNull(collection);
        assertTrue(collection.contains("kevin"));
        assertEquals(1, collection.size());

        collection.add("jack");
    }

    /**
     * Collection.addAll(Collection</? extends E> c) : boolean
     */
    @Test
    public void addAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));

        assertTrue(collection.contains("kevin"));
        assertEquals(1, collection.size());

        collection.addAll(Arrays.asList("jack", "may"));

        assertEquals(3, collection.size());
        assertTrue(collection.containsAll(Arrays.asList("may", "kevin", "jack")));
    }

    /**
     * Collection.remove(Object o) : boolean
     */
    @Test
    public void remove(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));

        assertEquals(true, collection.contains("kevin"));
        assertEquals(1, collection.size());

        assertTrue(collection.remove("kevin"));
        assertTrue(collection.isEmpty());
    }

    /**
     * Collection.removeAll(Collection</?> c) : boolean
     */
    @Test
    public void removeAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("may"));

        collection.addAll(Arrays.asList("kevin", "jack"));
        assertEquals(3, collection.size());

        assertTrue(collection.removeAll(new ArrayDeque<>(Arrays.asList("may", "kevin"))));
        assertTrue(collection.contains("jack"));
        assertEquals(1, collection.size());

        collection.clear();

        assertTrue(Objects.nonNull(collection));
        assertTrue(collection.isEmpty());
    }

    /**
     * Collection.retainAll(Collection</?> c) : boolean
     */
    @Test
    public void retainAll(){

        Collection<? super String> collection = new ArrayDeque<>(Arrays.asList("kevin", "may"));

        collection.add("jack");
        assertEquals(3, collection.size());

        collection.retainAll(Arrays.asList("may", "kevin"));
        assertEquals(2, collection.size());

        assertTrue(!collection.contains("jack"));
    }


    /**
     * Collection.removeIf(Predicate</? super E> filter) : boolean
     */
    @Test
    public void removeIf(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));
        collection.addAll(Arrays.asList("jack", "may"));

        assertTrue(collection.removeIf(s -> ((String)s).startsWith("j")));
        assertTrue(!collection.contains("jack"));
        assertTrue(collection.containsAll(Arrays.asList("kevin", "may")));
        assertEquals(2, collection.size());
    }


    /**
     * Collection.iterator() : Iterator<E>
     */
    @Test
    public void iterator(){

        Collection<? extends String> collection = Arrays.asList("kevin", "jack", "may");
        collection = Collections.unmodifiableCollection(collection);

        Iterator<String> iterator = (Iterator<String>) collection.iterator();

        while (iterator.hasNext()){
            String element = iterator.next();
            log.debug(element.toUpperCase());
        }
    }


    /**
     * Collection.toArray() : Object[], Collection.toArray(T[] t) : T[]
     */
    @Test
    public void toArray(){

        Collection<? extends String> collection = Arrays.asList("kevin", "jack");

        Object[] resultAndObject = collection.toArray();
        String[] resultAndString = collection.toArray(new String[]{});

        assertTrue(
                Arrays.stream(resultAndObject)
                .findAny()
                .get() instanceof Object
        );

        assertTrue(
                Arrays.stream(resultAndString)
                .findAny()
                .get() instanceof String
        );
    }


}
