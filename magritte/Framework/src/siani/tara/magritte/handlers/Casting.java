package siani.tara.magritte.handlers;

import siani.tara.magritte.Node;
import siani.tara.magritte.NodeWrap;
import siani.tara.magritte.Set;
import siani.tara.magritte.wraps.Definition;
import siani.tara.magritte.wraps.Morph;
import siani.tara.magritte.wraps.Type;

public class Casting {

    public static NodeCasting cast(Node node) {
        return new NodeCasting(node, null);
    }

    public static NodeSetCasting cast(Set<Node> nodes) {
        return new NodeSetCasting(nodes, null);
    }

    public static NodeCasting cast(Node node, Node scope) {
        return new NodeCasting(node, scope);
    }

    public static NodeSetCasting cast(Set<Node> nodes, Node scope) {
        return new NodeSetCasting(nodes, scope);
    }


    public static class NodeCasting {
        private final Node scope;
        private final Node node;

        private NodeCasting(Node node, Node scope) {
            this.node = node;
            this.scope = scope;
        }

        public <T extends NodeWrap> T as(Class<T> wrapClass)  {
            try {
                return init(wrapClass.newInstance());
            }
            catch (InstantiationException | IllegalAccessException e) {
                return null;
            }
        }

        private <T extends NodeWrap> T init(T instance) {
            if (instance instanceof Morph)
                ((Morph) instance)._node(node);
            if (instance instanceof Definition) {
                ((Definition) instance)._node(node);
                ((Definition) instance)._scope(scope);
            }
            if (instance instanceof Type) {
                ((Type) instance)._node(node);
            }
            return instance;
        }
    }

    public static class NodeSetCasting {
        private final Set<Node> nodes;
        private final Node scope;

        public NodeSetCasting(Set<Node> nodes, Node scope) {
            this.nodes = nodes;
            this.scope = scope;
        }

        public <T extends NodeWrap> Set<T> as(final Class<T> wrapClass)  {
            return nodes.map(node -> cast(node, scope).as(wrapClass));
        }
    }




}
