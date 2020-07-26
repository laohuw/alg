package com.examples.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class jacksonTest {
    public static void main(String[] args) throws JsonProcessingException {
        User user=new User(1l, "lastname", "firstname", LocalDate.now(), 3);
        System.out.println(user);

        ObjectMapper objectMapper=new ObjectMapper();
        FilterProvider filters=new SimpleFilterProvider().addFilter("siblingFilter",
                SimpleBeanPropertyFilter.serializeAll());
//        objectMapper.setFilterProvider(filters);
//        String userWithSiblings=objectMapper.writeValueAsString(user);
        String userWithSiblings= objectMapper.writer(filters).writeValueAsString(user);
        System.out.println(userWithSiblings);


//        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("siblings")
        ObjectMapper objectMapper2=new ObjectMapper();
        FilterProvider filters2=new SimpleFilterProvider().addFilter("siblingFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("siblings"));
        objectMapper2.setFilterProvider(filters2);
//        String userWithoutSiblings=objectMapper.writeValueAsString(user);
        String userWithoutSiblings=objectMapper2.writeValueAsString(user);
        System.out.println(userWithoutSiblings);

        userWithoutSiblings=objectMapper.writer(filters2).writeValueAsString(user);
        System.out.println(userWithoutSiblings);
        userWithoutSiblings=objectMapper.writer(filters).writeValueAsString(user);
        System.out.println(userWithoutSiblings);

    }
}
