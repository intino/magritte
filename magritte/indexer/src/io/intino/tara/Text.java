package io.intino.tara;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Text {

    private String value;

    public Text(String value) {
        this.value = value;
    }

    public List<String> words() {
        return Patcher.wordsOf(this);
    }

    public static String normalize(String word) {
        return Patcher.normalize(word);
    }

    public static Text of(String value) {
        return new Text(value);
    }

    private static class Patcher {
        public static List<String> wordsOf(Text text) {
            return extract(normalize(text.value));
        }

        private static List<String> extract(String text) {
            List<String> words = asList(text.split("\\s"));
            return words.stream().filter(w -> w.length() > 2).collect(toList());
        }

        public static String normalize(String input) {
            return normalize(input.toLowerCase().toCharArray());
        }

        private static String normalize(char[] input) {
            final String chars = "aáàäbcdeéèëfghiíìïjklmnñoóòöpqrstuúùuvwxyz0123456789";
            final char[] ascii = "aaaabcdeeeefghiiiijklmnñoooopqrstuuuuvwxyz0123456789".toCharArray();
            String output = "";
            for (char c : input) {
                int index = chars.indexOf(c);
                output += index < 0 ? " " : ascii[index];
            }
            return output;
        }

    }

}
