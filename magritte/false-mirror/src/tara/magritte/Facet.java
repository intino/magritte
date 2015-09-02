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

public abstract class Facet {

	protected final Case aCase;

	public Facet(Case aCase) {
		this.aCase = aCase;
	}

	public Case _node() {
		return aCase;
	}

	public boolean is(String name) {
		return aCase.is(name);
	}

	public boolean is(Class<? extends Facet> aClass) {
		return aCase.is(aClass);
	}

    public boolean is(Type type) {
        return aCase.is(type);
    }

    public Case _owner() {
		return aCase.owner();
	}

	public <T extends Facet> T _owner(Class<T> $Class) {
		return aCase.owner($Class);
	}

	public <T extends Facet> T as(Class<T> tClass) {
		return aCase.morph(tClass);
	}

	protected void _add(Case component) {
	}

	protected void _add(List<Case> components) {
		components.forEach(this::_add);
	}

	protected void _set(String name, Object object) {
	}

	protected void _init(String name, Object object) {
	}

	public List<Case> _components() {
		return Collections.emptyList();
	}

	public Map<String, Object> _variables() {
		return Collections.emptyMap();
	}

	protected Object _link(NativeCode value) {
		if (value == null) return null;
		Case context = aCase.is(value.$Class()) ? aCase : searchOwner(value);
		if (context instanceof Type) return value;
		Facet facet = context == null ? this : context.morph(value.$Class());
		value.set(facet == null ? this : facet);
		return value;
	}

	private Case searchOwner(NativeCode value) {
		Facet owner = aCase.owner(value.$Class());
		return owner != null ? owner._node() : null;
	}

	protected Case _loadNode(Object id) {
		return PersistenceManager.loadNode(id);
	}

	protected List<Case> _loadNode(List ids) {
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
		PersistenceManager.save(aCase);
	}

	private static final String[] DATE_FORMATS = {"dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm", "dd/MM/yyyy HH", "dd/MM/yyyy", "MM/yyyy", "yyyy", "HH:mm"};
	private static final String[] TIME_FORMATS = {"HH:mm:ss", "HH:mm", "HH"};

	protected LocalDateTime _asDate(String date) {
		return parseDate(date);
	}

	protected List<LocalDateTime> _asDate(List<String> dates) {
		return dates.stream().map(Facet::parseDate).collect(Collectors.toList());
	}

	protected LocalTime _asTime(String date) {
		return parseTime(date);
	}

	protected List<LocalTime> _asTime(List<String> dates) {
		return dates.stream().map(Facet::parseTime).collect(Collectors.toList());
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
		return aCase.name();
	}

	public static String _Type() {
		return "Facet";
	}


}
