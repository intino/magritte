package magritte;

public interface Expression<T> extends NativeCode {
    T value();
}
