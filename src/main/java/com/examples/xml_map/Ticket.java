package com.examples.xml_map;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

@XmlRootElement
public class Ticket {

    @XmlRootElement(name="params")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    static class Params{
        @XmlElement
        private String itemNum;
        @XmlElement
        private Variables variables;
    }

    @XmlRootElement(name="variables")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    static class Variables {
        @XmlAnyElement
//        private List<JAXBElement> var;
        @XmlJavaTypeAdapter(MapAdapter.class)
        private Map<String, String> var;

    }

    @XmlElement
    private String UserID;

    @XmlElement
    private String rtim_id;

    @XmlElement
    private Params params;
    }
