package magritte;

public interface Array<T> extends Set<T> {
    public void add(T item);
    public void remove(T item);
}
