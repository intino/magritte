package io.intino.tara.magritte;

@SuppressWarnings("unused")
public interface Expression<T> extends NativeCode {
	T value();
}