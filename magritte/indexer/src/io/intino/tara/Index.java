package io.intino.tara;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public class Index {
    private final List<Triple> triples = new ArrayList<>();
    private final Map<String, Integer> words = new HashMap<>();
    private final transient Map<String, String> members = new HashMap<>();

    public Index() {
    }

    public List<Triple> triples() {
        return triples;
    }

    public Edition edit(String subject) {
        return new Edition(subject.toLowerCase());
    }

    public void remove(String subject) {
        triples.removeIf(t->t.subject.equalsIgnoreCase(subject));
    }

    public int id(String word) {
        return idOf(word);
    }

    private int idOf(String word) {
        return words.containsKey(word) ? words.get(word) : -1;
    }

    private int[] idsOf(List<String> words) {
        return words.stream().mapToInt(this::idOf).toArray();
    }

    private List<String> existing(List<String> words) {
        for (String word : words) {
            if (this.words.containsKey(word)) continue;
            this.words.put(word, this.words.size());
        }
        return words;
    }


    public static Index load(String path) throws IOException {
        try (Input input = new Input(new InflaterInputStream(new FileInputStream(path)))) {
            return kryo().readObject(input, Index.class);
        }
    }

    public void save(String path) throws IOException {
        try (Output output = new Output(new DeflaterOutputStream(new FileOutputStream(path)))) {
            kryo().writeObject(output, this);
        }
    }

    private static Kryo kryo() {
        return new Kryo();
    }

    private void createTriple(String subject, String predicate, Object value) {
        triples.add(new Triple(subject, predicate.toLowerCase(), value));
    }

    public static class Triple {
        public String subject;
        public String attribute;
        public Object value;

        @SuppressWarnings("unused")
        Triple() {

        }

        Triple(String subject, String attribute, Object value) {
            this.subject = subject;
            this.attribute = attribute;
            this.value = value;
        }

        public boolean contains(int value) {
            return intValues().anyMatch(v -> v == value);
        }

        public boolean contains(long value) {
            return longValues().anyMatch(v -> v == value);
        }

        public boolean contains(double value) {
            return doubleValues().anyMatch(v -> v == value);
        }

        public boolean contains(String value) {
            return stringValues().anyMatch(v -> v.equalsIgnoreCase(value));
        }

        public String text() {
            return stringValues().collect(joining());
        }

        private IntStream intValues() {
            return (value instanceof int[]) ? stream((int[])value) : stream(new int[0]);
        }

        private LongStream longValues() {
            return (value instanceof long[]) ? stream((long[])value) : stream(new long[0]);
        }

        private DoubleStream doubleValues() {
            return (value instanceof double[]) ? stream((double[])value) : stream(new double[0]);
        }

        private Stream<String> stringValues() {
            return (value instanceof String[]) ? stream((String[])value) : stream(new String[0]);
        }


    }

    public class Edition {

        private String subject;

        public Edition(String subject) {
            this.subject = subject;
        }

        public Edition link(String attribute, String... subjects) {
            createTriple(member(subject), member(attribute), asList(subjects).stream().map(s -> "@" + s).toArray(String[]::new));
            return this;
        }

        public Edition set(String attribute, Text text) {
            createTriple(member(subject), member(attribute), idsOf(existing(text.words())));
            return this;
        }

        public Edition set(String attribute, LocalDate... data) {
            return set(attribute, asList(data).stream().mapToLong(LocalDate::toEpochDay).toArray());
        }

        public Edition set(String attribute, int... data) {
            createTriple(subject, attribute, data);
            return this;
        }

        public Edition set(String attribute, long... data) {
            createTriple(subject, attribute, data);
            return this;
        }

        public Edition set(String attribute, double... data) {
            createTriple(subject, attribute, data);
            return this;
        }

        public Edition unlink(String attribute) {
            return unset(attribute);
        }

        public Edition unset(String attribute) {
            triples.removeIf(t->t.subject.equals(subject) && t.attribute.equals(attribute));
            return this;
        }

        private String member(String text) {
            if (!members.containsKey(text)) members.put(text,text);
            return members.get(text);
        }

    }


}
