package tara.language.semantics.constraints.required;

import tara.language.model.Element;
import tara.language.model.Tag;
import tara.language.semantics.Constraint;
import tara.language.semantics.SemanticError;
import tara.language.semantics.SemanticException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

public class OneOfRequired implements Constraint.Require.OneOf {
	private final Require[] requires;

	public OneOfRequired(Require... requires) {
		this.requires = requires;
	}

	@Override
	public void check(Element element) throws SemanticException {
		List<String> requireTypes = new ArrayList<>();
		for (Require require : requires) {
			requireTypes.add(((Include) require).type());
			try {
				require.check(element);
				return;
			} catch (SemanticException ignored) {
			}
		}
		throw new SemanticException(new SemanticError("required.any.type.in.context", element, singletonList(String.join(", ", requireTypes))));
	}

	@Override
	public Require[] requires() {
		return Arrays.copyOf(requires, requires.length);
	}

	@Override
	public String type() {
		return null;
	}

	@Override
	public Tag[] annotations() {
		return new Tag[0];
	}
}
