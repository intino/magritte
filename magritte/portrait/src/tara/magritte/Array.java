package tara.magritte;

public interface Array<T> extends Set<T> {
    void add(T item);
    void remove(T item);
}
