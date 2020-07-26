package com.examples.xml_map;

//import javax.xml.bind.Element;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@XmlType
public class MapWrapper {
    @XmlAnyElement
    private List<JAXBElement<String>> entryList=new ArrayList<>();

    public void addEntry(String key, String value) {
        JAXBElement<String> entry=new JAXBElement<>(new QName(key), String.class, value);
        entryList.add(entry);
    }
    public void addEntry(JAXBElement<String> element){
        entryList.add(element);
    }

    public Map<String, String> toMap() {
        return entryList.stream().collect(Collectors.toMap(MapWrapper::extractName, MapWrapper::extractValue));
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
