package io.intino.magritte.io.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Variable {

	public java.lang.String name;
	public List<?> values = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(name, values);
	}
}
