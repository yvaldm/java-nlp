package ru.yvaldm.data.preprocess.service;

import com.sun.tools.javac.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yvaldm.data.preprocess.utils.RussianStopWords;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for tagging description entities with values from param
 *
 * @author valery.yakovlev
 */
public class TaggingService {

    private static final Logger log = LoggerFactory.getLogger(TaggingService.class);

    public void mark(String description, Map<String, String> params) {

        // reverse map value -> key (label)
        Map<String, String> reversedMap = new HashMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            reversedMap.put(normalize(entry.getValue()), entry.getKey());
        }

        Collection<String> valuesSet = reversedMap.keySet();
        String[] sentence = description.split(" ");

        for (String word : sentence) {
            String normalizedWord = normalize(word);

            if (RussianStopWords.STOP_WORDS.contains(normalizedWord)) {
                continue;
            }

            if (valuesSet.contains(normalizedWord)) {
                log.info(normalizedWord + " -> " + reversedMap.get(normalizedWord));
            }
        }
    }

    private String normalize(String str) {
        String result = str.replaceAll("[-+.^:,]", "");
        return StringUtils.toLowerCase(result);
    }
}
