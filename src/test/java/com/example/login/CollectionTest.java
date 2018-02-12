package com.example.login;


import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

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

        collection.add("may");
    }


    /**
     * Collection.add(Collection</? extends E> c) : boolean
     */
    @Test
    public void addAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));

        assertNotNull(collection);
        assertTrue(collection.contains("kevin"));
        assertEquals(1, collection.size());

        assertTrue(collection.addAll(Arrays.asList("may", "jack")));
        assertEquals(3, collection.size());
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

        assertTrue(!collection.contains("kevin"));
        assertTrue(collection.isEmpty());
    }


    /**
     * Collection.remove(Collection</?> c) : boolean
     */
    @Test
    public void removeAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("may"));

        collection.addAll(Arrays.asList("kevin", "jack"));
        assertEquals(3, collection.size());

        assertTrue(collection.removeAll(new ArrayDeque<>(Arrays.asList("jack", "may"))));
        assertTrue(collection.contains("kevin"));
        assertEquals(1, collection.size());

        collection.clear();
        assertTrue(collection.isEmpty());
        assertTrue(Objects.nonNull(collection));
    }


    /**
     * Collection.retainAll(Collection</?> c) : boolean
     */
    @Test
    @Synchronized
    public void retainAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin", "may"));

        collection.add("jack");
        assertEquals(3, collection.size());

        collection.retainAll(Arrays.asList("may", "jack"));
        assertEquals(2, collection.size());
        assertTrue(!collection.contains("kevin"));
    }


    /**
     * Collection.removeIf(Predicate</? super E> filter) : boolean
     */

    @Test
    public void removeIf(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));

        collection.addAll(Arrays.asList("may", "jack"));
        assertEquals(3, collection.size());

        collection.removeIf(s -> ((String) s).startsWith("j"));

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

        Iterator<? extends String> iterator = collection.iterator();

        while (iterator.hasNext()){

            String o = iterator.next();
            log.warn(o.toUpperCase());
            log.debug(o);
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

        log.warn(Arrays.toString(resultAndObject));
        log.debug(Arrays.toString(resultAndString));

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
