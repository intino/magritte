package magritte.schema;

import magritte.Node;
import magritte.Set;

import java.util.*;

@SuppressWarnings("Convert2streamapi")
public class ListSet<T> implements Set<T> {

    public static <T> Set<T> cast(T[] items) {
        return new ListSet<>(new ArrayList<>(Arrays.asList(items)));
    }

    public static <T> Set<T> cast(List<T> items) {
        return new ListSet<>(items);
    }

    public static <T> Set<T> empty() {
        return cast(new ArrayList<>());
    }

    private final List<T> items;

    private ListSet(List<T> items) {
        this.items = items;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public T get(int index) {
        return index < size() ? items.get(index) : null;
    }

    @Override
    public boolean contains(T item) {
        return items.contains(item);
    }

    @Override
    public Set<T> filter(Filter<T> filter) {
        List<T> result = new ArrayList<>();
        for (T item : items) if (filter.filter(item)) result.add(item);
        return new ListSet<>(result);
    }

    @Override
    public <M> Set<M> map(Map<T, M> map) {
        List<M> result = new ArrayList<>();
        for (T item : items) result.add(map.map(item));
        return new ListSet<>(result);
    }

    @Override
    public List<T> asList() {
        return items;
    }

    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }

}
