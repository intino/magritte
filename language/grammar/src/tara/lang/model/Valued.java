package tara.lang.model;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.DOUBLE;
import static tara.lang.model.Primitive.RESOURCE;

public interface Valued extends Element {

	default List<Object> makeUp(File resourcesRoot, Primitive type, List<Object> values) {
		if (type != null && type.equals(RESOURCE))
			return values.stream().
				map(o -> o instanceof EmptyNode ? null : new File(resourcesRoot, o.toString())).
				collect(Collectors.toList());
		if (type != null && type.equals(DOUBLE))
			return values.stream().map(o -> o instanceof Integer ? ((Integer) o).doubleValue() : o).collect(Collectors.toList());
		return values;
	}

	Rule rule();

	void rule(Rule rule);

	NodeContainer container();

	List<Object> values();

	Primitive type();

	String name();

	void values(List<Object> objects);
}