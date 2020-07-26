package com.examples.xml_map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class App {
    public static void main(String[] args) throws JAXBException {
        testXml();
    }


    public static void testXml() throws JAXBException {
        String xml="<ticket>\n" +
                "\t<rtim_id>RTIM</rtim_id>\n" +
                "\t<UserID>xxx</UserID>\n" +
                "\t<params>\n" +
                "\t\t<itemNum>1</itemNum>" +
                "\t\t<variables>\n" +
                "\t\t\t<var1>Test</var1>\n" +
                "\t\t\t<var2>test2</var2>\n" +
                "\t\t</variables>\n" +
                "\t</params>\n" +
                "</ticket>";
        JAXBContext context=JAXBContext.newInstance(com.examples.xml_map.Ticket.class);
        Unmarshaller unmarshaller=context.createUnmarshaller();
        Ticket ticket= (Ticket) unmarshaller.unmarshal(new StringReader(xml));
        System.out.println(ticket);

    }
    public static void test(){
        List<RiseAttribute> riseAttributeList=new ArrayList<>();
        for(int i=0; i<100;i++){
            RiseAttribute ra=new RiseAttribute();
            ra.setRiseId((long) i%7);
            ra.setName("name"+i%5);
            ra.setValue("value"+i%10);
            riseAttributeList.add(ra);
        }

        Map<Long, Map<String, Set<String>>> map= riseAttributeList.stream()
                .collect(groupingBy(RiseAttribute::getRiseId, groupingBy(RiseAttribute::getName, mapping(RiseAttribute::getValue, toSet()))));
        System.out.println(map);
    }
}
