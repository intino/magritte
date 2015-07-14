package monopoly;

import monopoly.natives.Count;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.Collections;
import java.util.List;

public class Station extends Square {

    public Station(Node node) {
        super(node);
    }

    public Station(Morph morph, Node node) {
        super(morph, node);
    }
}
