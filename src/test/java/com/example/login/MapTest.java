package com.example.login;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

@Slf4j
public class MapTest {

    @Test
    public void putIf(){

        Map<String, ? super Integer> map = new HashMap<>();

        map.putIfAbsent("code", 0);
        map.putIfAbsent("level", (Integer) map.getOrDefault("code", -1));

        map.computeIfPresent("level", (s, v) -> {

            assertEquals(0, v);
            return ((Integer) v) + 1;
        });

        map.entrySet().forEach(System.out::println);
    }


    @Test
    public void merge(){

        Map<String, Integer> map = new HashMap<>();

        map
        .putAll(

                new HashMap<String, Integer>()
                {
                    {
                        this.putIfAbsent("code", 0);
                        this.putIfAbsent("level", 1);
                    }
                }
        );

        assertEquals(2, map.size());

        map.merge("code", 0, Integer::sum);

        System.out.println("code : " + map.getOrDefault("code", -1));
    }


    @Test
    public void compute(){

        Map<String, Object> map = new HashMap<>();

        map
        .putAll(

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
        map.computeIfAbsent("member", s -> "jackson".toUpperCase());
        map.compute("leader", (s, o) -> o.toString().toUpperCase());

        map.entrySet().forEach(System.out::println);
    }


    @Test
    public void replaceAll(){

        Map<String, Object> map = new HashMap<>();

        map
                .putAll(

                        new HashMap<String, Object>()
                        {
                            {
                                this.putIfAbsent("manager", "kevin");
                                this.putIfAbsent("leader", "may");
                            }
                        }
                );

        map.replaceAll
                (
                (s, o) -> o.toString().toUpperCase()
        );

        map.entrySet().forEach(System.out::println);
    }


    @Test
    public void treeMap(){

        TreeMap<String, Object> treeMap = new TreeMap<>(Comparator.comparing(String::length, Comparator.reverseOrder()));

        treeMap
                .putAll(

                new TreeMap<String, Object>()
                {
                    {
                        this.put("manager", "kevin");
                        this.put("leader", "may");
                        this.put("cap", "jack");
                    }
                }
        );

        treeMap.entrySet().forEach(System.out::println);
    }

}
