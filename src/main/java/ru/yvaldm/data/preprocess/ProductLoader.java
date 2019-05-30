package ru.yvaldm.data.preprocess;

import ru.yvaldm.data.preprocess.entity.ProductEntity;
import ru.yvaldm.data.preprocess.xml.Product;
import ru.yvaldm.data.preprocess.xml.ProductCatalog;
import ru.yvaldm.data.preprocess.xml.ProductParams;
import ru.yvaldm.data.preprocess.xml.Products;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Reads product files from folder and loads it into memory
 *
 * @author valery.yakovlev
 */
public class ProductLoader {

    public Map<String, ProductCatalog> collect(String dataRoot) {
        Map<String, ProductCatalog> globalXmlCatalog = new HashMap<>();
        CatalogLoader catalogLoader = new CatalogLoader();
        Path dir = Paths.get(dataRoot);

        for (final File fileEntry : dir.toFile().listFiles()) {
            if (!fileEntry.isDirectory()) {
                System.out.println(fileEntry.getName());
                ProductCatalog xmlCatalog = catalogLoader.loadCatalog(fileEntry.getAbsolutePath());
                globalXmlCatalog.put(xmlCatalog.getCategory().getName(), xmlCatalog);
            }
        }

        return globalXmlCatalog;
    }

    private List<ProductEntity> convert(Map<String, ProductCatalog> catList) {
        List<ProductEntity> result = new LinkedList<>();

        for (ProductCatalog catalog : catList.values()) {

            if (catalog == null) {
                continue;
            }

            Products offers = catalog.getOffers();
            List<Product> products = offers.getOffer();

            for (Product product : products) {

                Map<String, String> params = new HashMap();
                List<ProductParams> srcParams = product.getParam();

                for (ProductParams p : srcParams) {
                    params.put(p.getName(), p.getValue());
                }

                result.add(new ProductEntity(catalog.getCategory().getName(), params, product.getDescription()));
            }
        }

        return result;
    }

    public List<ProductEntity> load(String path) {
        return convert(collect(path));
    }
}
