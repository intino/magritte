package monopoly.monopoly;

import siani.tara.magritte.Morph;
import siani.tara.magritte.Node;
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
