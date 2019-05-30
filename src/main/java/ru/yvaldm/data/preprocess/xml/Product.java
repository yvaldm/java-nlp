package ru.yvaldm.data.preprocess.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType
public class Product {

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String categoryId;

    @XmlElement
    private List<ProductParams> param;

    @XmlElement(name = "breadcrumbs")
    private String breadcrumbs;

    @XmlElement(name = "description")
    private String description;

    public Product() {
    }

    public Product(String id, String categoryId, List<ProductParams> param, String breadcrumbs, String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.param = param;
        this.breadcrumbs = breadcrumbs;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public List<ProductParams> getParam() {
        return param;
    }

    public String getBreadcrumbs() {
        return breadcrumbs;
    }

    public String getDescription() {
        return description;
    }
}


