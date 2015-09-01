package tara.magritte;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Morph {

	protected final Node node;

	public Morph(Node node) {
		this.node = node;
	}

	public Node _node() {
		return node;
	}

	public boolean is(String name) {
		return node.is(name);
	}

	public boolean is(Class<? extends Morph> aClass) {
		return node.is(aClass);
	}

	public Node _owner() {
		return node.owner();
	}

	public <T extends Morph> T _owner(Class<T> $Class) {
		return node.owner($Class);
	}

	public <T extends Morph> T as(Class<T> tClass) {
		return node.morph(tClass);
	}

	protected void _add(Node component) {
	}

	protected void _add(List<Node> components) {
		components.forEach(this::_add);
	}

	protected void _set(String name, Object object) {
	}

	public List<Node> _components() {
		return Collections.emptyList();
	}

	public Map<String, Object> _variables() {
		return Collections.emptyMap();
	}

	protected Object _link(NativeCode value) {
		if (value == null) return null;
		Node context = node.is(value.$Class()) ? node : searchOwner(value);
		if (context instanceof Type) return value;
		Morph morph = context == null ? this : context.morph(value.$Class());
		value.set(morph == null ? this : morph);
		return value;
	}

	private Node searchOwner(NativeCode value) {
		Morph owner = node.owner(value.$Class());
		return owner != null ? owner._node() : null;
	}

	protected Node _loadNode(Object id) {
		return PersistenceManager.loadNode(id);
	}

	protected List<Node> _loadNode(List ids) {
		return PersistenceManager.loadNode(ids);
	}

	protected Object _newInstanceOf(Object aClass) {
		try {
			if (aClass == null) return null;
			return aClass instanceof String ? Class.forName(aClass.toString()).newInstance() : aClass.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected void save() {
		PersistenceManager.save(node);
	}

	private static final String[] DATE_FORMATS = {"dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm", "dd/MM/yyyy HH", "dd/MM/yyyy", "MM/yyyy", "yyyy", "HH:mm"};
	private static final String[] TIME_FORMATS = {"HH:mm:ss", "HH:mm", "HH"};

	protected LocalDateTime _asDate(String date) {
		return parseDate(date);
	}

	protected List<LocalDateTime> _asDate(List<String> dates) {
		return dates.stream().map(Morph::parseDate).collect(Collectors.toList());
	}

	protected LocalTime _asTime(String date) {
		return parseTime(date);
	}

	protected List<LocalTime> _asTime(List<String> dates) {
		return dates.stream().map(Morph::parseTime).collect(Collectors.toList());
	}

	private static LocalDateTime parseDate(String date) {
		if (date.isEmpty()) return null;
		for (String formatString : DATE_FORMATS) {
			try {
				return LocalDateTime.from(DateTimeFormatter.ofPattern(formatString).parse(date));
			} catch (DateTimeException ignored) {
			}
		}
		throw new RuntimeException("Date couldn't be parsed: " + date);
	}

	private static LocalTime parseTime(String time) {
		if (time.isEmpty()) return null;
		for (String formatString : TIME_FORMATS) {
			try {
				return LocalTime.from(DateTimeFormatter.ofPattern(formatString).parse(time));
			} catch (DateTimeException ignored) {
			}
		}
		throw new RuntimeException("Time couldn't be parsed: " + time);
	}

	protected List<Double> _toList(double... doubles) {
		List<Double> result = new ArrayList<>();
		for (Double aDouble : doubles) result.add(aDouble);
		return result;
	}

	protected List<Integer> _toList(int... integers) {
		List<Integer> result = new ArrayList<>();
		for (Integer integer : integers) result.add(integer);
		return result;
	}

	@Override
	public String toString() {
		return node.name();
	}

	public static String _Type() {
		return "Morph";
	}


}
