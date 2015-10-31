package tara.lang.semantics;

import tara.lang.model.Element;
import tara.lang.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Context {

	private final String[] types;
	private final List<Constraint> constraints = new ArrayList<>();
	private final List<Assumption> assumptions = new ArrayList<>();
	private Documentation documentation;

	public Context(String[] types, Constraint[] globalConstrains) {
		this.types = types.clone();
		Collections.addAll(constraints, globalConstrains);
	}

	public String[] types() {
		return Arrays.copyOf(types, types.length);
	}

	public List<Constraint> constraints() {
		return constraints;
	}

	public List<Assumption> assumptions() {
		return assumptions;
	}

	public Documentation doc() {
		return documentation;
	}


	public Context doc(String file, int line, String doc) {
		documentation = new Documentation(file, line, doc);
		return this;
	}

	public Context assume(Assumption... assumptions) {
		this.assumptions.addAll(Arrays.asList(assumptions));
		return this;
	}

	public Context has(Constraint... constraints) {
		this.constraints().addAll(Arrays.asList(constraints));
		return add(newComponentNotFound(componentConstrains()), newParameterNotFound(parameterConstrains()));
	}

	public Constraint.ComponentNotFound newComponentNotFound(List<String> types) {
		return new Constraint.ComponentNotFound() {

			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
			}
		};
	}

	public List<String> componentConstrains() {
		return this.constraints().stream().filter(c -> c instanceof Constraint.Component).map(c -> ((Constraint.Component) c).type()).collect(Collectors.toList());
	}

	public List<Constraint.Parameter> parameterConstrains() {
		return this.constraints().stream().filter(c -> c instanceof Constraint.Parameter).map(c -> (Constraint.Parameter) c).collect(Collectors.toList());
	}

	private Constraint.ParameterNotFound newParameterNotFound(List<Constraint.Parameter> parameters) {
		return new Constraint.ParameterNotFound() {
			@Override
			public void check(Element element) throws SemanticException {
				for (String type : types) {

				}
			}
		};
	}

	private Context add(Constraint... constraint) {
		this.constraints.addAll(Arrays.asList(constraint));
		return this;
	}
}
