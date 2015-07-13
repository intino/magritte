package monopoly.tafat;

import siani.tara.magritte.Node;

public class Tafat {

    public static Node node;
    public static Tafat model = new Tafat();

    public Simulation simulation() {
        return node.components(Simulation.class).get(0);
    }

}