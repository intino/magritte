package tara.magritte;

public interface NativeCode extends Cloneable {
    void $(Layer context);
    Class<? extends Layer> $Class();
}
