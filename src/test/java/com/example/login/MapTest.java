package com.example.login;

import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class MapTest {

    @Test
    public void putIf() {

        Map<String, ? super Integer> map = new HashMap<>();

        map.putIfAbsent("code", 0);
        map.putIfAbsent("level", (Integer) map.getOrDefault("code", -1) + 1);

        map.computeIfPresent
                (
                        "level",
                        (s, o) -> {
                            assertEquals(1, o);
                            return o;
                        }
                );
    }

    @Test
    public void merge() {

        Map<String, Integer> map = new HashMap<>();

        map.putAll
                (
                        new HashMap<String, Integer>() {
                            {
                                this.put("code", 0);
                                this.put("level", 1);
                            }
                        }
                );

        assertEquals(2, map.size());

        map.merge("code", 0, Integer::sum);

        map.computeIfPresent
                (
                "code",
                (s, v) -> {
                    System.out.println(v);

                    return v;
                }
        );
    }

    @Test
    public void compute(){

        Map<String, Object> map = new HashMap<>();

        map.putAll
                (
                new HashMap<String, Object>()
                {
                    {
                        this.putIfAbsent("manager", "kevin");
                        this.putIfAbsent("leader", "may");
                    }
                }
        );

        assertEquals(2, map.size());

        map.computeIfPresent("manager", (s, o) -> o.toString().toUpperCase());
        map.compute("leader", (s, o) -> o.toString().concat("/"));

        map.entrySet().forEach(System.out::println);
    }

    @Test
    public void replaceAll(){

        Map<String, Object> map = new HashMap<>();

        map.putAll(
                new HashMap<String, Object>()
                {
                    {
                        this.putIfAbsent("manager", "kevin");
                        this.putIfAbsent("leader", "may");
                    }
                }
        );

        assertEquals(2, map.size());

        map.replaceAll
                (
                (s, o) -> ("manager".equals(s))? o.toString().toUpperCase() : o

        );

        map.entrySet().forEach(System.out::println);
    }

    @Test
    public void treeMap(){

        TreeMap<String, Object> treeMap = new TreeMap<>(Comparator.comparing(String::length, Comparator.reverseOrder()));

        treeMap.putAll(
                new HashMap<String, Object>()
                {
                    {
                        this.put("manager", "kevin");
                        this.putIfAbsent("leader", "may");
                        this.put("cap", "jack");
                    }
                }
        );

        treeMap.entrySet().forEach(System.out::println);

        assertEquals("manager", treeMap.headMap("leader").firstKey());
        assertEquals(2, treeMap.headMap("cap").size());

        assertTrue(treeMap.tailMap("manager").containsKey("manager"));
        assertTrue(treeMap.tailMap("manager").containsKey("cap"));
    }






}