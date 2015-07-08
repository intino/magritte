package magritte;

import static magritte.helpers.Check.check;

public interface NativeCode extends Cloneable {
    void _target(Node node);
    Object clone() throws CloneNotSupportedException;


}
