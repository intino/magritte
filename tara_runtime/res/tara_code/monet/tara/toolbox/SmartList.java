package tara_code.monet.tara.toolbox;

import java.util.ArrayList;
import java.util.List;

public class SmartList<T> extends ArrayList<T> {

	public SmartList() {
		super();
	}

	public SmartList(T[] items) {
		this.addAll(items);
	}

    public void addAll(T[] items) {
		for (T item : items)
			add(item);
	}

	public List<T> filter(Filter<T> filter) {
		List<T> result = new ArrayList<>();
		for (T item : this)
			if (filter.check(item)) result.add(item);
		return result;
	}

	public static interface Filter<T> {

		public boolean check(T item);
	}
}
