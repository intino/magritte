package siani.tara.magritte.evolution;

import siani.tara.magritte.Graph;
import siani.tara.magritte.Node;
import siani.tara.magritte.handlers.NodeProducer;

import static siani.tara.magritte.Tag.Case;
import static siani.tara.magritte.helpers.Selection.tag;


public class ModelEvolver {

    private final Graph graph;

    public static ModelEvolver evolve(Graph graph) {
        return new ModelEvolver(graph);
    }

    private ModelEvolver(Graph graph) {
        this.graph = graph;
    }

    public void from(Graph priorGraph) {
        ModelEquivalence.track(priorGraph, graph);
        execute();
    }

    private void execute() {
        for (Node node : graph.roots().filter(tag(Case)))
            createHoldingsOf(node);
    }

    private void createHoldingsOf(Node node) {
//        if ($.prior() == null) return;
////TODO
////        for (Node priorHolding : $.prior().holdings())
////            $.link(createNode().as(priorHolding), AsHolding);
    }

    private Action createNode() {
        return null;
//        return new Action() {
//            @Override
//            public Node as(Node priorNode) {
//                Node $ = m0.createNode();
//                $.let(Case);
//                $.let(priorNode._name_());
//                $.let(priorNode.address());
//                m0.indexAsRoot($);
//                for (Node type : priorNode.primitives())
//                    create($, type.novel());
//                copyNode(priorNode, $);
//                return $;
//            }
//
//        };
    }

    private interface Action {
        public Node with(Node node);
    }

    private void produce(Node node, Node type) {
        if (type == null) return;
        NodeProducer.produce(node).with(type);
    }


    private void copyNode(Node source, Node target) {
        copyVars(source, target);
        copyComponents(source, target);
    }

    private void copyVars(Node source, Node target) {
        for (String var : source.vars())
            target.set(var, source.get(var));
    }

    private void copyComponents(Node source, Node target) {
//        for (Node targetComponent : target.allows()) {
//            Node sourceComponent = getComponentOf(source).as(targetComponent.type().prior());
//            if (sourceComponent == null)
//                copyComponents(source, targetComponent);
//            else
//                copyNode(sourceComponent, targetComponent);
//        }
    }


    private Action getComponentOf(final Node source) {
        return new Action() {
            @Override
            public Node with(Node type) {
                return doSearch(source, type);
            }

            public Node doSearch(Node source, Node type) {
//                if (type == null) return null;
//                for (Node member : source.allows()) {
//                    if (check(member.type()).isEquivalentTo(type)) return member;
//                    member = doSearch(member, type);
//                    if (member != null) return member;
//                }
                return null;
            }
        };
    }

}
