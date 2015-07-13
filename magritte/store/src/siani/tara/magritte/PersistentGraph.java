package siani.tara.magritte;

public interface PersistentGraph extends Graph {
    Store store();
    Node register(Reference reference);
    void save();
}