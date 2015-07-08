package siani.tara.magritte.helpers;

import siani.tara.magritte.Node;
import siani.tara.magritte.NodeWrap;
import siani.tara.magritte.Tag;
import siani.tara.magritte.Set;

import static siani.tara.magritte.helpers.Check.check;

public class Selection {

    public static Set.Filter<Node> name(String name) {
        return item -> name.equals(item.title());
    }

    public static Set.Filter<Node> tag(Tag tag) {
        return item -> item.is(tag);
    }

    public static Set.Filter<Node> instancesOf(String type) {
        return item -> check(item, type);
    }

    public static Set.Filter<Node> nonSingletonMainTypes() {
        return item -> item.is(Tag.Main) & !item.is(Tag.Singleton) & !item.is(Tag.Abstract);
    }

    public static <T extends NodeWrap> Set.Filter<T> instancesOf(Class<? extends NodeWrap> wrapClass) {
        return item -> check(item._node(), Extract.nameOf(wrapClass));
    }


}
