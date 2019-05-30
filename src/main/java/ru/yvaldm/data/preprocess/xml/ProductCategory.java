package ru.yvaldm.data.preprocess.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class ProductCategory {

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String parentId;

    @XmlAttribute
    private String name;

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }
}