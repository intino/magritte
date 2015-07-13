package monopoly.monopoly;

import wata._magritte.lite.Morph;
import wata._magritte.lite.Node;
import monopoly.monopoly.natives.Count;

public abstract class Square extends Morph {

    public Square(Node node) {
        super(node);
    }

    public abstract int count();

    public abstract void increment();

    public abstract void count(int value);

//    public abstract void count(Expression<Integer> value);

    public abstract void increment(Count value);

}
