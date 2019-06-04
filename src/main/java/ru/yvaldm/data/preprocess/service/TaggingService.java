package ru.yvaldm.data.preprocess.service;

import com.sun.tools.javac.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yvaldm.data.preprocess.utils.RussianStopWords;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for tagging description entities with values from param
 *
 * @author valery.yakovlev
 */
public class TaggingService {

    private static final Logger log = LoggerFactory.getLogger(TaggingService.class);

    public void mark(String description, Map<String, String> params, BufferedWriter writer) throws IOException {

        // reverse map value -> key (label)
        Map<String, String> reversedMap = new HashMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            reversedMap.put(normalize(entry.getValue()), entry.getKey());
        }

        String[] sentences = description.split("\\."); // this is my description. I have to break it to sentences;

        for (String sent : sentences) {
            processSentence(sent, reversedMap, writer);
        }
    }

    private void processSentence(String sentence, Map<String, String> reversedMap, BufferedWriter writer) throws IOException {

        sentence = sentence.trim();

        String[] words = sentence.split(" "); // this is my sentence. break it to words.
        Collection<String> valuesSet = reversedMap.keySet();

        List<TagInfo> infoList = new ArrayList<>();

        for (String word : words) {
            String normalizedWord = normalize(word);

            if (RussianStopWords.STOP_WORDS.contains(normalizedWord)) {
                continue;
            }

            if (valuesSet.contains(normalizedWord)) {

                // find where this word in sentence
                String s = word.replaceAll("\\+|\\\\", "");
                Pattern pattern = Pattern.compile(s);
                Matcher match = pattern.matcher(sentence);

                while (match.find()) {

                    infoList.add(new TagInfo(normalizedWord, reversedMap.get(normalizedWord), match.start(), (match.end() - 1)));
                }
            }
        }

        if (!infoList.isEmpty()) {
            writer.write(sentence + "\n");

            for (TagInfo tagInfo : infoList) {
                writer.write("Tag," + tagInfo.getNormalizedWord() + "," + tagInfo.getTag() + "," + tagInfo.getStartIdx() + "," + tagInfo.getEndIdx() + "\n");
            }
        }
    }

    private String normalize(String str) {
        String result = str.replaceAll("[-+.^:,]", "");
        return StringUtils.toLowerCase(result);
    }

    private class TagInfo {

        private String normalizedWord;
        private String tag;
        private int startIdx;
        private int endIdx;

        public TagInfo(String normalizedWord, String tag, int startIdx, int endIdx) {
            this.normalizedWord = normalizedWord;
            this.tag = tag;
            this.startIdx = startIdx;
            this.endIdx = endIdx;
        }

        public String getNormalizedWord() {
            return normalizedWord;
        }

        public String getTag() {
            return tag;
        }

        public int getStartIdx() {
            return startIdx;
        }

        public int getEndIdx() {
            return endIdx;
        }
    }
}
