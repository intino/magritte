package siani.tara.magritte.wraps;

import siani.tara.magritte.Node;
import siani.tara.magritte.NodeWrap;

public class Type implements NodeWrap {
    private Node node;

    @Override
    public Node _node() {
        return node;
    }

    public void _node(Node node) {
        this.node = node;
    }

    public String _name() {
        return node.title();
    }

    @Override
    public String toString() {
        return node.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
