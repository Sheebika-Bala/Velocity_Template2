package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.util.*;

public class XmlFormLoader {
    public List<Map<String, Object>> loadFields(String resourcePath) throws Exception {
        InputStream xml = getClass().getResourceAsStream(resourcePath);
        if (xml == null) throw new RuntimeException("Could not find " + resourcePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xml);

        List<Map<String, Object>> fields = new ArrayList<>();
        NodeList fieldNodes = doc.getElementsByTagName("field");
        for (int i = 0; i < fieldNodes.getLength(); i++) {
            Element fieldElem = (Element) fieldNodes.item(i);
            Map<String, Object> field = new HashMap<>();
            field.put("name", fieldElem.getElementsByTagName("name").item(0).getTextContent());
            field.put("type", fieldElem.getElementsByTagName("type").item(0).getTextContent());
            field.put("label", fieldElem.getElementsByTagName("label").item(0).getTextContent());
            if ("radio".equals(field.get("type"))) {
                NodeList options = fieldElem.getElementsByTagName("option");
                List<String> opts = new ArrayList<>();
                for (int j = 0; j < options.getLength(); j++) {
                    opts.add(options.item(j).getTextContent());
                }
                field.put("options", opts);
            }
            fields.add(field);
        }
        return fields;
    }
}

