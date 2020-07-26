package com.examples.xml_map;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Map;

public class MapAdapter extends XmlAdapter<MapWrapper, Map<String, String>> {
    @Override
    public Map<String, String> unmarshal(MapWrapper v) throws Exception {
        return v.toMap();
    }

    @Override
    public MapWrapper marshal(Map<String, String> v) throws Exception {
        MapWrapper mapWrapper=new MapWrapper();
        v.forEach((key, value) -> {mapWrapper.addEntry(key, value);});
        return mapWrapper;
    }
}
