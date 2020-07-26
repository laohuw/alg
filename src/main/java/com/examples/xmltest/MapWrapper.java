package com.examples.xmltest;


import org.w3c.dom.Element;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@XmlType
public class MapWrapper {
    @XmlAnyElement
    private List<Element> entries = new ArrayList<>();
    public MapWrapper(){
    }


    @Override
    public String toString() {
        return "MapWrapper [properties=" + toMap() + "]";
    }

    public Map<String, String> toMap(){
        List<?> props = entries;
        return props.stream().collect(Collectors.toMap(MapWrapper::extractName, MapWrapper::extractValue));
    }

    @SuppressWarnings("unchecked")
    private static String extractName(Object obj){
        String name=((Element)obj).getLocalName();
        return name;
//        strFuncs.put(JAXBElement.class, (jaxb) -> ((JAXBElement<String>)jaxb).getName().getLocalPart());
//        strFuncs.put(Element.class, ele -> ((Element) ele).getLocalName());
//        return extractPart(obj, strFuncs).orElse("");
    }

    @SuppressWarnings("unchecked")
    private static String extractValue(Object obj){
        String value= ((Element)obj).getTextContent();
        return value;
//        Map<Class<?>, Function<? super Object, String>> strFuncs = new HashMap<>();
//        strFuncs.put(JAXBElement.class, (jaxb) -> ((JAXBElement<String>)jaxb).getValue());
//        strFuncs.put(Element.class, ele -> ((Element) ele).getTextContent());
//        return extractPart(obj, strFuncs).orElse("");
    }

//    private static <ObjType, T> Optional<T> extractPart(ObjType obj, Map<Class<?>, Function<? super ObjType, T>> strFuncs){
//        for(Class<?> clazz : strFuncs.keySet()){
//            if(clazz.isInstance(obj)){
//                return Optional.of(strFuncs.get(clazz).apply(obj));
//            }
//        }
//        return Optional.empty();
//    }
}
