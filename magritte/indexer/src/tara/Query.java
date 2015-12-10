package tara;

import tara.Index.Triple;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static tara.Query.SortCriteria.Ascending;

public class Query {
    private Index index;
    private List<Triple> selection;

    public Query(Index index) {
        this.index = index;
        this.selection = index.triples();
    }

    private Query(Query query, Set<String> subjects) {
        this.index = query.index;
        this.selection = query.triplesWith(t -> subjects.contains(t.subject)).collect(toList());
    }

    public List<String> subjects() {
        Set<String> subjects = selection.stream().map(t -> t.subject).collect(toSet());
        return subjects.stream().collect(toList());
    }

    public Query search(String word) {
        return new Query(this, subjects(with(idOf(word))));
    }

    public Query select(String value) {
        return new Query(this, subjects(with("@"+value)));
    }

    public Query select(String attribute, String value) {
        return new Query(this, subjects(withAttribute(attribute, "@"+value)));
    }

    public List<String> sortBy(String attribute) {
        return sortBy(attribute, Ascending);
    }

    public List<String> sortBy(String attribute, SortCriteria criteria) {
        return Sorting.of(subjects()).with(triplesWith(withAttribute(attribute))).by(criteria);
    }

    public QueryGroup groupBy(String attribute) {
        return new QueryGroup(triplesWith(withAttribute(attribute)));
    }

    private Set<String> subjects(Predicate<Triple> predicate) {
        return triplesWith(predicate).map(t -> t.subject).collect(toSet());
    }

    private Stream<Triple> triplesWith(Predicate<Triple> predicate) {
        return selection.stream().filter(predicate);
    }

    private Predicate<Triple> with(String value) {
        return t -> t.contains(value);
    }

    private Predicate<Triple> with(int id) {
        return t -> id >= 0 && t.contains(id);
    }

    private Predicate<Triple> withAttribute(String attribute) {
        return t -> t.attribute.equals(attribute);
    }

    private Predicate<Triple> withAttribute(String attribute, String value) {
        return t -> t.attribute.equals(attribute) && t.contains(value);
    }

    private int idOf(String word) {
        return index.id(Text.normalize(word));
    }

    public class QueryGroup {
        private final Map<Object, Set<String>> data;

        public QueryGroup(Stream<Triple> triples) {
            data = triples.collect(groupingBy(this::labelOf, mapping(t -> t.subject, toSet())));
        }

        public List<Object> groups() {
            return data.keySet().stream().collect(toList());
        }

        public Query group(Object value) {
            return new Query(Query.this, data.get(value));
        }

        private String labelOf(Triple triple) {
            return triple.text();
        }
    }

    private static class Sorting {
        private final List<String> subjects;
        private Map<String,Object> triples;
        private int criteria;

        private Sorting(List<String> subjects) {
            this.subjects = subjects;
        }

        public static Sorting of(List<String> subjects) {
            return new Sorting(subjects);
        }

        public Sorting with(Stream<Triple> triples) {
            this.triples = triples.collect(toMap(t -> t.subject, t -> t.value));
            return this;
        }

        public List<String> by(SortCriteria criteria) {
            this.criteria = criteria == Ascending ? 1 : -1;
            subjects.sort(this::comparator);
            return subjects;
        }

        private int comparator(String s1, String s2) {
            return Long.compare(get(s1), get(s2)) * criteria;
        }

        private long get(String subject) {
            Object value = triples.get(subject);
            return (value instanceof long[]) ? ((long[]) value)[0] : defaultValue();
        }

        private long defaultValue() {
            return Long.MAX_VALUE * criteria;
        }

    }

    public enum SortCriteria {
        Ascending, Descending
    }
}
