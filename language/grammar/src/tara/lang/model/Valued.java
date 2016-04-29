package tara.lang.model;

import tara.lang.model.rules.variable.VariableRule;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.DOUBLE;
import static tara.lang.model.Primitive.RESOURCE;

public interface Valued extends Element {

	default List<Object> makeUp(File resourcesRoot, Primitive type, List<Object> values) {
		if (!values.isEmpty() && values.get(0) instanceof Primitive.Expression) return values;
		if (type != null && type.equals(RESOURCE))
			return values.stream().
				map(o -> o instanceof EmptyNode ? null : new File(resourcesRoot, o.toString())).
				collect(Collectors.toList());
		if (type != null && type.equals(DOUBLE))
			return values.stream().map(o -> o instanceof Integer ? ((Integer) o).doubleValue() : o).collect(Collectors.toList());
		return values;
	}

	VariableRule rule();

	void rule(VariableRule rule);

	Node container();

	String scope();

	List<Object> values();

	Primitive type();

	String name();

	List<Tag> flags();

	void values(List<Object> objects);
}
