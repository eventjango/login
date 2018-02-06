package com.example.login;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.util.*;

public class GsonTest {

    @Test
    public void apply(){

        Gson gson = new Gson();

        List<Student> students = new ArrayList<>(
                Arrays.asList(
                        Student.builder().name("kevin").code("A").build(),
                        Student.builder().name("jack").code("B").build()
                )
        );


        JsonObject jsonObject = gson.fromJson(gson.toJson(students.get(0)), JsonObject.class);

        System.out.println(jsonObject.get("name"));
    }
}

@Data
class Student{

    String name;
    String code;

    @Builder
    Student(String name, String code){
        this.name = name;
        this.code = code;
    }
}

