package monopoly;

import tara.magritte.Morph;
import tara.magritte.Node;
import monopoly.natives.Count;

import java.util.Collections;
import java.util.List;

public class Terrain extends Square {

    public Terrain(Node node) {
        super(node);
    }

    public Terrain(Morph morph, Node node) {
        super(morph, node);
    }

}
