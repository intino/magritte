package siani.tara.magritte;

import java.util.List;

public interface Set<T> extends Iterable<T> {
    int size();
    T get(int index);
    boolean contains(T item);
    List<T> asList();

    <M> Set<M> map(Map<T, M> map);
    Set<T> filter(Filter<T> filter);

    interface Map<Source, Target> {
        Target map(Source item);
    }

    interface Filter<Source> {
        boolean filter(Source item);
    }


}
