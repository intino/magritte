package monopoly;

import monopoly.natives.Get;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.*;


public class Cards extends Morph {

    protected Get get;
    ArrayList<Card> cardList = new ArrayList<>();

    public Cards(Node node) {
        super(node);
        set("get", new get_meme());
    }

    public Cards(Morph morph, Node node) {
        super(morph, node);
        set("get", ((Cards) morph).get);
    }

    public Card get() {
        return get.get();
    }

    public void get(Get value) {
        get = value;
    }

    public List<Card> cardList() {
        return cardList;
    }

    public Card card(int index) {
        return cardList.get(index);
    }

    @Override
    public List<Node> _components() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> _variables() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("get", get);
        return map;
    }

    @Override
    protected void add(Node component) {
        if(component.is("Card")) cardList.add(component.morph(Card.class));
    }

    @Override
    protected void set(String name, Object object) {
        if (name.equalsIgnoreCase("get")) get = (Get) link((NativeCode) object);
    }

    public static class get_meme implements Get, NativeCode {
        Cards $;

        @Override
        public Card get() {
            return $.card(new Random().nextInt($.cardList().size()));
        }

        @Override
        public void set(Morph context) {
            $ = (Cards) context;
        }

        @Override
        public Class<? extends Morph> $Class() {
            return Cards.class;
        }
    }
}
