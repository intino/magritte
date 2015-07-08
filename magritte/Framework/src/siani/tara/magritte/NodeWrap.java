package siani.tara.magritte;

public interface NodeWrap extends Cloneable {

    Node _node();
    Object clone() throws CloneNotSupportedException;

}
