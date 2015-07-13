package monopoly.monopoly;

import monopoly.monopoly.natives.Get;
import wata._magritte.lite.Morph;
import wata._magritte.lite.NativeCode;
import wata._magritte.lite.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Cards extends Morph {

    protected Get get = () -> card(new Random().nextInt(cards().size()));
    ArrayList<Card> cards = new ArrayList<>(0);

    public Cards(Node node) {
        super(node);
    }

    public Card get() {
        return ((Get) node.get("get")).get();
    }

    public void get(NativeCode value) {
        node.set("get", value);
    }

    public List<Card> cards() {
        return node.components(Card.class);
    }

    public Card card(int index) {
        return cards().get(index);
    }

    @Override
    protected void add(Node component) {
        if(component.is(Card.class)) cards.add(component.morph(Card.class));
    }

    @Override
    protected void set(String name, Object object) {
        // TODO como no se setean no est� hecho
    }
}
