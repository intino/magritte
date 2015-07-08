package siani.tara.magritte.helpers;

import siani.tara.magritte.Graph;
import siani.tara.magritte.schema.Box;


public class SceneLoader {
    private Graph graph;

    private SceneLoader(Graph graph) {
        this.graph = graph;
    }

    public static SceneLoader load(Graph graph)  {
        return new SceneLoader(graph);
    }


    public SceneLoader with(String... boxes)  {
        for (String box : boxes) load(box);
        return this;
    }

    public Graph graph() {
        return graph;
    }

    private void load(String box)  {
        try {
            Box.Scene scene = (Box.Scene) Class.forName(of(box)).newInstance();
            scene.load(graph);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private String of(String box) {
        return "magritte.scene." + box;
    }
}
