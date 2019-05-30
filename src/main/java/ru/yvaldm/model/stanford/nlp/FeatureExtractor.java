package ru.yvaldm.model.stanford.nlp;

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
}
