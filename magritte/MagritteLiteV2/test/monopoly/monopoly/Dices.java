package monopoly.monopoly;

import monopoly.monopoly.natives.Doubles;
import siani.tara.magritte.Morph;
import siani.tara.magritte.Node;
import monopoly.monopoly.natives.Roll;
import monopoly.monopoly.natives.Value;

import java.util.Random;

public class Dices extends Morph {

    protected int value1 = 0;
    protected int value2 = 0;
    protected Roll roll = () -> {
        value1(new Random().nextInt(6) + 1);
        value2(new Random().nextInt(6) + 1);
    };
    protected Doubles doubles = () -> value1() == value2();
    protected Value value = () -> value1() + value2();
    public Dices(Node node) {
        super(node);
    }

    public int value1() {
        return value1;
    }

    public int value2() {
        return value2;
    }

    public void roll() {
        roll.roll();
    }

    public boolean doubles() {
        return doubles.check();
    }

    public int value() {
        return value.value();
    }

    public void value1(int value) {
        value1 = value;
    }

//    public void value1(Expression<Integer> value) {
//        node.set("value1", value);
//    }

    public void value2(int value) {
        value2 = value;
    }

//    public void value2(Expression<Integer> value) {
//        node.set("value2", value);
//    }

    public void roll(Roll value) {
        roll = value;
    }

    public void doubles(Doubles value) {
        doubles = value;
    }

    public void value(Value value) {
        this.value = value;
    }

    @Override
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
        // TODO como no se setean no est� hecho
    }
}
