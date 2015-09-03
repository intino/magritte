package tara.magritte;

import tara.util.WordGenerator;

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

	protected final Instance instance;

	public Facet(Instance instance) {
		this.instance = instance;
	}

	public Instance _instance() {
		return instance;
	}

	public boolean is(String name) {
		return instance.is(name);
	}

	public boolean is(Class<? extends Facet> aClass) {
		return instance.is(aClass);
	}

    public boolean is(Type type) {
        return instance.is(type);
    }

    public Instance _owner() {
		return instance.owner();
	}

	public <T extends Facet> T _owner(Class<T> $Class) {
		return instance.owner($Class);
	}

	public <T extends Facet> T as(Class<T> tClass) {
		return instance.morph(tClass);
	}

	protected void _add(Instance component) {
	}

	protected void _set(String name, Object object) {
	}

	protected void _init(String name, Object object) {
	}

    public void _newComponent(Type type){
        _newComponent(type, WordGenerator.generate());
    }

    public void _newComponent(Type type, String componentId){
        instance.add(type.newCase(componentId));
    }

	public List<Case> _components() {
		return Collections.emptyList();
	}

	public Map<String, Object> _variables() {
		return Collections.emptyMap();
	}

	protected Object _link(NativeCode value) {
		if (value == null) return null;
		Instance context = instance.is(value.$Class()) ? instance : searchOwner(value);
		if (context instanceof Type) return value;
		Facet facet = context == null ? this : context.morph(value.$Class());
		value.set(facet == null ? this : facet);
		return value;
	}

	private Instance searchOwner(NativeCode value) {
		Facet owner = instance.owner(value.$Class());
		return owner != null ? owner._instance() : null;
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
		PersistenceManager.save(instance);
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
		return instance.name();
	}

	public static String _Type() {
		return "Facet";
	}

}
