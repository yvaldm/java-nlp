package ru.yvaldm.data.preprocess.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.util.Objects;

@XmlType
public class ProductParams {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String unit;

    @XmlValue
    private String value;

    public ProductParams() {
    }

    public ProductParams(String name, String unit, String value) {
        this.name = name;
        this.unit = unit;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductParams param = (ProductParams) o;
        return Objects.equals(name, param.name) &&
            Objects.equals(unit, param.unit) &&
            Objects.equals(value, param.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit, value);
    }
}
