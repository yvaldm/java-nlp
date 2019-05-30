package ru.yvaldm.data.preprocess.entity;

import java.util.Map;

/**
 * Entity holding information about product
 *
 * @author valery.yakovlev
 */
public class ProductEntity {

    private String category;
    private Map<String, String> params;
    private String description;

    public ProductEntity(String category, Map<String, String> params, String description) {
        this.category = category;
        this.params = params;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getDescription() {
        return description;
    }
}
