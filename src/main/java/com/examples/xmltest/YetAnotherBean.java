package com.examples.xmltest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement(name="root")
//@XmlType(propOrder={"map", "other"})
public class YetAnotherBean{
    private Map<String, String> map = new HashMap<>();
    private String other;
    public YetAnotherBean(){
    }
    public void putEntry(String key, String value){
        map.put(key, value);
    }
    @XmlElement(name="map")
    @XmlJavaTypeAdapter(MapAdapter.class)
    public Map<String, String> getMap(){
        return map;
    }
    public void setMap(Map<String, String> map){
        this.map = map;
    }
    @XmlElement(name="other")
    public String getOther(){
        return other;
    }
    public void setOther(String other){
        this.other = other;
    }

    public  static void main1(String[] args) throws JAXBException {
        YetAnotherBean yab = new YetAnotherBean();
        yab.putEntry("key1", "value1");
        yab.putEntry("key2", "value2");
        yab.setOther("other content");
        StringWriter sb = new StringWriter();
        JAXBContext.newInstance(YetAnotherBean.class).createMarshaller().marshal(yab, sb);
        System.out.println(sb.toString());
    }
    public  static void main(String[] args) throws JAXBException {
        String xml="<root><map><key1>value1</key1><key2>value2</key2></map><other>other content</other></root>";
        YetAnotherBean bean= (YetAnotherBean) JAXBContext.newInstance(YetAnotherBean.class).createUnmarshaller().unmarshal(new StringReader(xml));
        System.out.println(bean);
    }
}