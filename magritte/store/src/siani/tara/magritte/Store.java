package siani.tara.magritte;

public interface Store {
    boolean exists(Reference reference);
    Source sourceOf(Reference reference);
    void save(Reference reference, byte[] data);
}
