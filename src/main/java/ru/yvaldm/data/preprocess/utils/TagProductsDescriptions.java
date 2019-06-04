package ru.yvaldm.data.preprocess.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yvaldm.data.preprocess.CatalogLoader;
import ru.yvaldm.data.preprocess.service.TaggingService;
import ru.yvaldm.data.preprocess.xml.Product;
import ru.yvaldm.data.preprocess.xml.ProductCatalog;
import ru.yvaldm.data.preprocess.xml.ProductParams;
import ru.yvaldm.data.preprocess.xml.Products;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Class that just reads some products
 * and prints their params and description to the standard output
 *
 * @author valery.yakovlev
 */
public class TagProductsDescriptions {

    private static final Logger log = LoggerFactory.getLogger(PreProcessFiles.class);
    private static final String PATH = "/Users/valery.yakovlev/ml/electroncs";

    public static void main(String[] args) throws IOException {

        TaggingService taggingService = new TaggingService();
        CatalogLoader loader = new CatalogLoader();

        final Path dir = Paths.get(PATH);
        Path path = Paths.get("electronics-tagged.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            for (final File fileEntry : dir.toFile().listFiles()) {
                if (!fileEntry.isDirectory()) {

                    log.info(fileEntry.getAbsolutePath());
                    ProductCatalog productCatalog = loader.loadCatalog(fileEntry.getAbsolutePath());
                    Products offers = productCatalog.getOffers();

                    for (Product p : offers.getOffer()) {

                        String description = p.getDescription();
                        List<ProductParams> params = p.getParam();

                        Map<String, String> paramsMap = params.stream().collect(toMap(ProductParams::getName,
                                                                                      ProductParams::getValue,
                                                                                      (p1, p2) -> p1));
                        taggingService.mark(description, paramsMap, writer);
                    }
                }
            }
        }
    }
}
