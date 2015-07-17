package monopoly.jailscape;

import monopoly.Player;
import monopoly.Square;
import tara.magritte.Morph;
import tara.magritte.Node;

public class JailScape_Player extends JailScape {

    protected final Player _player;

    public JailScape_Player(Node node) {
        super(node);
        _player = node.morph(Player.class);
    }

    public JailScape_Player(Morph morph, Node node) {
        super(morph, node);
        _player = node.morph(Player.class);
    }

    public String id() {
        return _player.id();
    }

    public void id(String value) {
        _player.id(value);
    }

    public Square square() {
        return _player.square();
    }

    public void square(Square value) {
        _player.square(value);
    }
}
