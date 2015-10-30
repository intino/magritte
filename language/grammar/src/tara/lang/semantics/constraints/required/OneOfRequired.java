package tara.lang.semantics.constraints.required;

import tara.lang.model.Element;
import tara.lang.model.Tag;
import tara.lang.model.rules.Size;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.SemanticException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

public class OneOfRequired implements Constraint.OneOf {
	private final Constraint[] requires;
	private final Size size;

	public OneOfRequired(Size size, Constraint... requires) {
		this.size = size;
		this.requires = requires;
	}

	@Override
	public void check(Element element) throws SemanticException {
		List<String> requireTypes = new ArrayList<>();
		for (Constraint require : requires) {
			requireTypes.add(((Constraint.Component) require).type());
			try {
				require.check(element);
				return;
			} catch (SemanticException ignored) {
			}
		}
		throw new SemanticException(new SemanticError("required.any.type.in.context", element, singletonList(String.join(", ", requireTypes))));
	}

	@Override
	public Constraint[] components() {
		return Arrays.copyOf(requires, requires.length);
	}

	@Override
	public String type() {
		return null;
	}

	@Override
	public Size size() {
		return this.size;
	}

	@Override
	public List<Tag> annotations() {
		return Collections.emptyList();
	}
}
