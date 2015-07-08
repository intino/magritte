package magritte.helpers;

import magritte.Node;
import magritte.NodeWrap;
import magritte.Tag;

import static magritte.Set.*;
import static magritte.Tag.Abstract;
import static magritte.Tag.Main;
import static magritte.Tag.Singleton;
import static magritte.helpers.Check.check;
import static magritte.helpers.Extract.nameOf;

public class Selection {

    public static Filter<Node> name(String name) {
        return item -> name.equals(item.title());
    }

    public static Filter<Node> tag(Tag tag) {
        return item -> item.is(tag);
    }

    public static Filter<Node> instancesOf(String type) {
        return item -> check(item, type);
    }

    public static Filter<Node> nonSingletonMainTypes() {
        return item -> item.is(Main) & !item.is(Singleton) & !item.is(Abstract);
    }

    public static <T extends NodeWrap> Filter<T> instancesOf(Class<? extends NodeWrap> wrapClass) {
        return item -> check(item._node(), nameOf(wrapClass));
    }


}
