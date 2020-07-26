package com.examples.xmltest;


import org.w3c.dom.Element;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@XmlType
public class MapWrapper2 {
    @XmlAnyElement
    private List<JAXBElement<String>> entries = new ArrayList<>();
    public MapWrapper2(){
    }


    public void addEntry(String key, String value){
        JAXBElement<String> prop = new JAXBElement<String>(new QName(key), String.class, value);
        addEntry(prop);
    }
    public void addEntry(JAXBElement<String> prop){
        entries.add(prop);
    }
    @Override
    public String toString() {
        return "MapWrapper [properties=" + toMap() + "]";
    }

    public Map<String, String> toMap(){
        //Note: Due to type erasure, you cannot use properties.stream() directly when unmashalling is used..
        List<?> props = entries;
        return props.stream().collect(Collectors.toMap(MapWrapper2::extractName, MapWrapper2::extractValue));
    }

    @SuppressWarnings("unchecked")
    private static String extractName(Object obj){
        Map<Class<?>, Function<? super Object, String>> strFuncs = new HashMap<>();
        strFuncs.put(JAXBElement.class, (jaxb) -> ((JAXBElement<String>)jaxb).getName().getLocalPart());
        strFuncs.put(Element.class, ele -> ((Element) ele).getLocalName());
        return extractPart(obj, strFuncs).orElse("");
    }

    @SuppressWarnings("unchecked")
    private static String extractValue(Object obj){
        Map<Class<?>, Function<? super Object, String>> strFuncs = new HashMap<>();
        strFuncs.put(JAXBElement.class, (jaxb) -> ((JAXBElement<String>)jaxb).getValue());
        strFuncs.put(Element.class, ele -> ((Element) ele).getTextContent());
        return extractPart(obj, strFuncs).orElse("");
    }

    private static <ObjType, T> Optional<T> extractPart(ObjType obj, Map<Class<?>, Function<? super ObjType, T>> strFuncs){
        for(Class<?> clazz : strFuncs.keySet()){
            if(clazz.isInstance(obj)){
                return Optional.of(strFuncs.get(clazz).apply(obj));
            }
        }
        return Optional.empty();
    }
}
