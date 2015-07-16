package monopoly.jailscape;

import monopoly.Player;
import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JailScape_Player extends JailScape {

    protected final Player _player;

    public JailScape_Player(Node node) {
        super(node);
        _player = node.morph(Player.class);
    }

    public JailScape_Player(Morph morph, Node node) {
        super(morph, node)
        _player = node.morph(Player.class);;
    }

}
