package com.dooliesoft;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Xml2CsvMapper {

    HashMap<String, String> xmlMap = new HashMap<>();
    LinkedHashMap<String, Integer> csvMap = new LinkedHashMap<>();

    public Xml2CsvMapper() {

    }

    public void map(String key, String xmlPath, int csvColumn) {
        xmlMap.put(key, xmlPath);
        csvMap.put(key, csvColumn);
    }

    public String getXMLPath(String key) {
        return (String)xmlMap.get(key);
    }

    public int getCSVColumn(String key) {
        return (int)csvMap.get(key);
    }

    public LinkedHashMap<String, Integer> getCsvMap() {
        return csvMap;
    }
}
