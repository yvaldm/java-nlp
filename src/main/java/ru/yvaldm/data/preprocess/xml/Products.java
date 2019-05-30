package ru.yvaldm.data.preprocess.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType
public class Products {

    @XmlElement
    private List<Product> offer;

    public List<Product> getOffer() {
        return offer;
    }
}

