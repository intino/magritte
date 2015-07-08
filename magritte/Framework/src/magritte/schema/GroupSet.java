package magritte.schema;

import magritte.Set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupSet<E extends Enum, Type> {

	private final Map<E, List<Type>> groups = new HashMap<>();

	public int groups() {
		return groups.size();
	}

	public Set<Type> group(E group) {
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
