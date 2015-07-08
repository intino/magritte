package magritte.evolution;

import magritte.Graph;
import magritte.Node;


public class ModelEquivalence {
    private final Graph priorGraph;
    private final Graph novelGraph;

    public static void track(Graph priorGraph, Graph novelGraph) {
        new ModelEquivalence(priorGraph, novelGraph).execute();
    }

    private ModelEquivalence(Graph priorGraph, Graph novelGraph) {
        this.priorGraph = priorGraph;
        this.novelGraph = novelGraph;
    }

    private void execute() {
        linkRoots();
    }

    private void linkRoots() {
        for (Node node : this.novelGraph.roots()) {
            link(node, nodeEquivalentTo(node));
        }
    }


    private Node nodeEquivalentTo(Node node) {
//        Node result = priorModel.get($.address());
//        if (result != null) return result;
        return priorGraph.get(node.title());
    }

    private void link(Node novel, Node prior) {
//        if (prior != null) {
//            novel.link(prior).as(Prior);
//            prior.link(novel).as(Novel);
//        }
//        linkComponents(novel);
    }

}
