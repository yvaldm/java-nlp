package ru.mp.feature.extractor.nlpfeatureextractor;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.sequences.SeqClassifierFlags;
import edu.stanford.nlp.util.StringUtils;

import java.util.Properties;

/**
 * @author valery.yakovlev
 */
public class FeatureExtractor {

    public void trainAndWrite(String modelOutPath, String prop, String trainingFilepath) {

        Properties props = StringUtils.propFileToProperties(prop);

        props.setProperty("serializeTo", modelOutPath);

        if (trainingFilepath != null) {
            props.setProperty("trainFile", trainingFilepath);
        }

        SeqClassifierFlags flags = new SeqClassifierFlags(props);
        CRFClassifier<CoreLabel> crf = new CRFClassifier<CoreLabel>(flags);
        crf.train();
        crf.serializeClassifier(modelOutPath);
    }

    public CRFClassifier getModel(String modelPath) {
        return CRFClassifier.getClassifierNoExceptions(modelPath);
    }


    public void doTagging(CRFClassifier model, String input) {
        input = input.trim();
        System.out.println(input + "=>" + model.classifyToString(input));
    }
//
//
//    public static void main(String[] args) {
//
//        long heapSize = Runtime.getRuntime().totalMemory();
//
//        System.out.println("heap size " + heapSize);
//
//        URL systemResource = this.getClass().getClassLoader().getSystemResource("nlp.properties");
//        URL trainingResource = ClassPathLoader.getSystemResource("training.txt");
//
//
//        String propsFile = "nlp.properties";
//        String trainfile = "training.txt";
//        String modelLoc = "stanfordCRF.model";
//
//        FeatureExtractor tagger = new FeatureExtractor();
//
//        System.out.println("started training");
//        tagger.trainAndWrite(modelLoc, systemResource.getPath(), trainingResource.getPath());
//        System.out.println("finished training");
////
////        String[] tests = new String[] {"apple watch", "samsung mobile phones", " lcd 52 inch tv"};
////
////        for (String item : tests) {
////            //doTagging(model, item);
////        }
//
//
//
//    }


}
