package ru.mp.feature.extractor.nlpfeatureextractor;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;

@RestController
public class MyController {
    String modelLoc = "stanfordCRF.model";
    FeatureExtractor featureExtractor = new FeatureExtractor();

    @GetMapping("/train")
    public void jdaksdh() throws FileNotFoundException {

        System.out.println("total size " + Runtime.getRuntime().totalMemory() / 1024);
        System.out.println("heap size " + Runtime.getRuntime().freeMemory() / 1024);

        File file = ResourceUtils.getFile("classpath:nlp.properties");
        File file1 = ResourceUtils.getFile("classpath:training.txt");

        System.out.println("started training");
        featureExtractor.trainAndWrite(modelLoc, file.getAbsolutePath(), file1.getAbsolutePath());
        System.out.println("finished training");
    }


    @GetMapping("/eval")
    public void eval() throws Exception {

        CRFClassifier<CoreMap> classifierNoExceptions = CRFClassifier.getClassifierNoExceptions(modelLoc);

        String str = "Монитор Samsung C24F390FHI диагональю 24 дюйма станет настоящей находкой как для геймеров, так и " +
            "для киноманов. За счёт главной особенности — изогнутого монитора — обеспечивается широкий угол обзора, " +
            "изображение становится более объёмным и красочным. Экран поддерживает 16,7 млн цветов, разрешение — 1920x1080 " +
            "Full HD, яркость составляет 250 кд/кв. м, контрастность — 3000:1. Время отклика — всего 4 мс. Благодаря матовому " +
            "покрытию изображение получается ровным, без бликов.";

        String[] s = str.split(" ");
        for (String mystr : s) {
            doTagging(classifierNoExceptions, mystr);
        }
    }

    public void doTagging(CRFClassifier model, String input) {
        input = input.trim();
        System.out.println(" " + input + " => " + model.classifyToString(input));
    }
}
