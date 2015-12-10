package tara.magritte.schema;

import java.util.*;

public class GroupSet<E extends Enum, Type>  {

    private final Map<E, List<Type>> groups = new HashMap<>();

    public int groups() {
        return groups.size();
    }

    public tara.magritte.Set<Type> group(E group) {
        return ListSet.cast(items(group));
    }

    private List<Type> items(E group) {
        return groups.containsKey(group) ?
                groups.get(group) :
                new ArrayList<>();
    }

    public void add(Type item, E group) {
        if (group.ordinal() > 8)
            return;
        if (!groups.containsKey(group)) groups.put(group, new ArrayList<>());
        items(group).add(item);
    }

    public void remove(Type item, E group) {
        if (group.ordinal() > 8)
            return;
        items(group).remove(item);
    }
}
