package tafat;

import tara.magritte.Node;

public class Tafat {

    private static Simulation simulation;

    public static void use(Node node) {
        simulation = node.components(Simulation.class).get(0);
    }

    public static Simulation simulation() {
        return simulation;
    }

}