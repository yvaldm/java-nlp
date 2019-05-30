package ru.yvaldm.data.preprocess.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xmlCatalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductCatalog {

    private ProductCategory category;
    private String shop;
    private Products offers;

    public ProductCatalog() {
    }

    public ProductCatalog(ProductCategory category, String shop, Products offers) {
        this.category = category;
        this.shop = shop;
        this.offers = offers;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public String getShop() {
        return shop;
    }

    public Products getOffers() {
        return offers;
    }
}
