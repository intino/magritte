package tara.lang.semantics.constraints.component;

import tara.lang.model.Element;
import tara.lang.model.Tag;
import tara.lang.model.rules.CompositionRule;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.SemanticException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OneOf implements Constraint.OneOf {
	private final CompositionRule compositionRule;
	private final List<Component> constraints;

	public OneOf(List<Component> constraints, CompositionRule compositionRule) {
		this.compositionRule = compositionRule;
		this.constraints = constraints;
	}

	@Override
	public void check(Element element) throws SemanticException {
		List<String> requireTypes = new ArrayList<>();
		for (Constraint constraint : constraints) {
			requireTypes.add(((Constraint.Component) constraint).type());
			try {
				constraint.check(element);
				return;
			} catch (SemanticException ignored) {
				System.out.println(ignored.getMessage());
			}
		}
		throw new SemanticException(new SemanticError("required.any.type.in.context", element, Collections.singletonList(String.join(", ", requireTypes))));
	}


	@Override
	public String type() {
		return "";
	}

	@Override
	public CompositionRule compositionRule() {
		return this.compositionRule;
	}

	@Override
	public List<Tag> annotations() {
		return Collections.emptyList();
	}

	@Override
	public List<Component> components() {
		return constraints;
	}

	@Override
	public String toString() {
		List<String> types = constraints.stream().map(Component::type).collect(Collectors.toList());
		return "OneOf{" + String.join(", ", types) + '}';
	}

}
