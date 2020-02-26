package io.intino.magritte.framework;

@SuppressWarnings("unused")
public interface Expression<T> extends NativeCode {
	T value();
}
