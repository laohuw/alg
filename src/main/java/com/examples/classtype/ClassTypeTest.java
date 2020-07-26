package com.examples.classtype;

import com.fasterxml.jackson.core.type.TypeReference;

import javax.ws.rs.core.GenericType;
import java.util.ArrayList;
import java.util.List;

import static com.examples.classtype.ClassTypeTest.E.A;

public class ClassTypeTest {
    public static void main(String[] args){
        Class c="foo".getClass();
        System.out.println( c);
        Class c2=String.class;
        assert(c!=c2);

        Class ec = A.getClass();
        System.out.println(ec);

        List<String> lsc=new ArrayList<>();
        System.out.println(lsc.getClass());
        TypeReference<List<String>> tr=new TypeReference<List<String>>(){};
        System.out.println(tr.getClass());

        GenericType<List<String>> gt=new GenericType<List<String>>(){};
        System.out.println(gt);

    }

    public enum E{ A , B};

}
