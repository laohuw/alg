package com.examples.xmltest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static  void main(String[] args) throws JAXBException {
//        testMarshal();
        testUnmarshal();
    }

    private static void testUnmarshal() throws JAXBException {
        String xml="<root>\n" +
                "    <key1>value1</key1>\n" +
                "    <key2>value2</key2>\n" +
                "</root>";


        CustomMap customMap= (CustomMap) JAXBContext.newInstance(CustomMap.class).createUnmarshaller().unmarshal(new StringReader(xml));
        System.out.println(customMap);
    }

    private static void testMarshal() throws JAXBException {
        CustomMap map=new CustomMap();
//        map.addEntry("key1", "value1");
//        map.addEntry("key2", "value2");
//        map.addEntry("key3", "value3");

        StringWriter sw=new StringWriter();
        JAXBContext.newInstance(CustomMap.class).createMarshaller().marshal(map, sw);
        System.out.println(sw.toString());
    }
}
