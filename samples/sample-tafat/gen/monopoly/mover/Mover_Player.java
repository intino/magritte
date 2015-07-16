package monopoly.mover;

import monopoly.Player;
import monopoly.natives.Check;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.*;


public class Mover_Player extends Mover {

    protected Player _player;
    protected int turnsToBeInJail;
    protected int numberOfRolls;
    protected List<Rule> ruleList = new ArrayList<>();
    protected List<PlayerIsJailed> playerIsJailedList = new ArrayList<>();
    protected List<JailAfterThreeDoubles> jailAfterThreeDoublesList = new ArrayList<>();
    protected List<Advance> advanceList = new ArrayList<>();
    protected List<ToJailWhenInGoToJailSquare> toJailWhenInGoToJailSquareList = new ArrayList<>();
    protected List<CheckCard> checkCardList = new ArrayList<>();
    protected List<Doubles> doublesList = new ArrayList<>();

    public Mover_Player(Node node) {
        super(node);
        _player = node.morph(Player.class);
        set("turnstobeinjail", 0);
        set("numberofrolls", 0);
    }

    public Mover_Player(Morph morph, Node node) {
        super(morph, node);
        _player = node.morph(Player.class);
        set("turnstobeinjail", ((Mover_Player)morph).turnsToBeInJail);
        set("numberofrolls", ((Mover_Player)morph).numberOfRolls);
    }

    public String id() {
        return _player.id();
    }

    public void id(String id) {
        _player.id(id);
    }

    public monopoly.Square square() {
        return _player.square();
    }

    public void square(monopoly.Square value) {
        _player.square(value);
    }

    public int turnsToBeInJail() {
        return turnsToBeInJail;
    }

    public void turnsToBeInJail(int value) {
        turnsToBeInJail = value;
    }

    public int numberOfRolls() {
        return numberOfRolls;
    }

    public void numberOfRolls(int value) {
        numberOfRolls = value;
    }

    public List<Rule> ruleList() {
        return ruleList;
    }

    public Rule rule(int index) {
        return ruleList.get(index);
    }
    public List<PlayerIsJailed> plajerIsJailedList() {
        return playerIsJailedList;
    }

    public PlayerIsJailed playerIsJailed(int index) {
        return playerIsJailedList.get(index);
    }

    public List<JailAfterThreeDoubles> jailAfterThreeDoublesList() {
        return jailAfterThreeDoublesList;
    }

    public JailAfterThreeDoubles jailAfterThreeDoubles(int index) {
        return jailAfterThreeDoublesList.get(index);
    }

    public List<Advance> advanceList() {
        return advanceList;
    }

    public Advance advance(int index) {
        return advanceList.get(index);
    }

    public List<ToJailWhenInGoToJailSquare> toJailWhenInGoToJailSquareList() {
        return toJailWhenInGoToJailSquareList;
    }

    public ToJailWhenInGoToJailSquare jailWhenInGoToJailSquare(int index) {
        return toJailWhenInGoToJailSquareList.get(index);
    }

    public List<CheckCard> checkCardList() {
        return checkCardList;
    }

    public CheckCard checkCard(int index) {
        return checkCardList.get(index);
    }

    public List<Doubles> doublesList() {
        return doublesList;
    }

    public Doubles doubles(int index) {
        return doublesList.get(index);
    }

    @Override
    public List<Node> _components() {
        Set<Node> nodes = new LinkedHashSet<>();
        ruleList.stream().forEach(rule -> nodes.add(rule.node()));
        // Remove if you can find out that the type of all these lists are subs of the Rule
        playerIsJailedList.stream().forEach(rule -> nodes.add(rule.node()));
        jailAfterThreeDoublesList.stream().forEach(rule -> nodes.add(rule.node()));
        advanceList.stream().forEach(rule -> nodes.add(rule.node()));
        toJailWhenInGoToJailSquareList.stream().forEach(rule -> nodes.add(rule.node()));
        checkCardList.stream().forEach(rule -> nodes.add(rule.node()));
        doublesList.stream().forEach(rule -> nodes.add(rule.node()));
        return new ArrayList<>(nodes);
    }

    @Override
    public Map<String, Object> _variables() {
        Map<String, Object> map = new LinkedHashMap<>(super._variables());
        map.put("turnsToBeInJail", turnsToBeInJail);
        map.put("numberOfRolls", numberOfRolls);
        return map;
    }

    @Override
    protected void add(Node component) {
        super.add(component);
        if (component.is("Mover_Player$Rule")) ruleList.add(component.morph(Rule.class));
        if (component.is("Mover_Player$PlayerIsJailed")) playerIsJailedList.add(component.morph(PlayerIsJailed.class));
        if (component.is("Mover_Player$JailAfterThreeDoubles")) jailAfterThreeDoublesList.add(component.morph(JailAfterThreeDoubles.class));
        if (component.is("Mover_Player$Advance")) advanceList.add(component.morph(Advance.class));
        if (component.is("Mover_Player$ToJailWhenInGoToJailSquare")) toJailWhenInGoToJailSquareList.add(component.morph(ToJailWhenInGoToJailSquare.class));
        if (component.is("Mover_Player$CheckCard")) checkCardList.add(component.morph(CheckCard.class));
        if (component.is("Mover_Player$Doubles")) doublesList.add(component.morph(Doubles.class));
    }

    @Override
    protected void set(String name, Object object) {
        super.set(name, object);
        if(name.equalsIgnoreCase("turnstobeinjail")) turnsToBeInJail = (int) object;
        else if(name.equalsIgnoreCase("numberofrolls")) numberOfRolls = (int) object;
    }

    public static abstract class Rule extends Morph {

        protected Check check;

        public Rule(Node node) {
            super(node);
        }

        public Rule(Morph morph, Node node) {
            super(morph, node);
            set("check", ((Rule)morph).check);
        }

        public boolean check() {
            return check.check();
        }

        public void check(Check value) {
            check = value;
        }

        @Override
        public List<Node> _components() {
            return Collections.emptyList();
        }

        @Override
        public Map<String, Object> _variables() {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("check", check);
            return map;
        }

        @Override
        protected void set(String name, Object object) {
            if (name.equalsIgnoreCase("check")) check = (Check) link((NativeCode) object);
        }

        @Override
        protected void add(Node component) {
        }

    }

    public static class PlayerIsJailed extends Rule {

        public PlayerIsJailed(Node node) {
            super(node);
        }

        public PlayerIsJailed(Morph morph, Node node) {
            super(morph, node);
        }

    }

    public static class JailAfterThreeDoubles extends Rule {

        public JailAfterThreeDoubles(Node node) {
            super(node);
        }

        public JailAfterThreeDoubles(Morph morph, Node node) {
            super(morph, node);
        }

    }

    public static class Advance extends Rule {
        public Advance(Node node) {
            super(node);
        }

        public Advance(Morph morph, Node node) {
            super(morph, node);
        }

    }

    public static class ToJailWhenInGoToJailSquare extends Rule {

        public ToJailWhenInGoToJailSquare(Node node) {
            super(node);
        }

        public ToJailWhenInGoToJailSquare(Morph morph, Node node) {
            super(morph, node);
        }

    }

    public static class CheckCard extends Rule {

        public CheckCard(Node node) {
            super(node);
        }

        public CheckCard(Morph morph, Node node) {
            super(morph, node);
        }

    }

    public static class Doubles extends Rule {

        public Doubles(Node node) {
            super(node);
        }

        public Doubles(Morph morph, Node node) {
            super(morph, node);
        }

    }
}
