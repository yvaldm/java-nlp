package ru.yvaldm.data.preprocess;

import ru.yvaldm.data.preprocess.entity.ProductEntity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Mark entities in XML products and convert to text
 *
 * @author valery.yakovlev
 */
public class ProcessProducts {

    private static final String PATH = "/Users/valery.yakovlev/ml/electroncs";

    public static void main(String[] args) throws Exception {

        new File(PATH + "/.DS_Store").delete();

        ProductLoader offerLoader = new ProductLoader();
        System.out.println("Reading products dataset....");

        List<ProductEntity> entries = offerLoader.load(PATH);

        File outputFile = new File("output.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        for (ProductEntity entry : entries) {

            String description = entry.getDescription();
            Map<String, String> params = entry.getParams();
            Set<String> strings = params.keySet();

            System.out.println("Description: " + description);

            for (String key : strings) {
                String rawValue = params.get(key);
                String keyValue = String.format("%s -> %s", key, rawValue);
                System.out.println(keyValue);
                writer.write(keyValue);
            }

            writer.newLine();
        }

        writer.close();
    }
}
