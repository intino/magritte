package magritte.schema;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VariableSet {
	private final Map<String, Object> map = new HashMap<>();

	public Object get(String key) {
		return map.get(key);
	}

	public boolean contains(String key) {
		return map.containsKey(key);
	}

	public void put(String key, Object value) {
		if (value == null)
			map.remove(key);
		else
			map.put(key, value);
	}

	public String[] names() {
		Collection<String> keySet = map.keySet();
		return keySet.toArray(new String[keySet.size()]);
	}

	@Override
	public String toString() {
		String result = "";
		for (String key : map.keySet()) {
			result += key + "=" + this.get(key) + "\n";
		}
		return result;
	}
}
