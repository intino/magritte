package tara.magritte.helpers;

import tara.magritte.Node;
import tara.magritte.NodeWrap;
import tara.magritte.Tag;
import tara.magritte.Set;

public class Selection {

    public static Set.Filter<Node> name(String name) {
        return item -> name.equals(item.title());
    }

    public static Set.Filter<Node> tag(Tag tag) {
        return item -> item.is(tag);
    }

    public static Set.Filter<Node> instancesOf(String type) {
        return item -> Check.check(item, type);
    }

    public static Set.Filter<Node> nonSingletonMainTypes() {
        return item -> item.is(Tag.Main) & !item.is(Tag.Singleton) & !item.is(Tag.Abstract);
    }

    public static <T extends NodeWrap> Set.Filter<T> instancesOf(Class<? extends NodeWrap> wrapClass) {
        return item -> Check.check(item._node(), Extract.nameOf(wrapClass));
    }


}
