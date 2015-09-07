package tara.magritte;

public interface NativeCode extends Cloneable {
    void $(Morph context);
    Class<? extends Morph> $Class();
}
