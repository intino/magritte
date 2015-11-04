package tara.lang.semantics;

import tara.lang.semantics.constraints.RuleFactory;

import java.util.*;
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
		add(RuleFactory.rejectOtherComponents(componentConstrains()));
		add(RuleFactory.rejectOtherParameters(parameterConstrains()));
		return this;
	}

	private List<String> componentConstrains() {
		Set<String> types = new HashSet<>();
		final List<String> collect = this.constraints().stream().filter(c -> c instanceof Constraint.Component).map(c -> ((Constraint.Component) c).type()).collect(Collectors.toList());
		final List<List<Constraint.Component>> typeCollection = this.constraints().stream().filter(c -> c instanceof Constraint.OneOf).map(constraint -> ((Constraint.OneOf) constraint).components()).collect(Collectors.toList());
		for (List<Constraint.Component> components : typeCollection)
			types.addAll(components.stream().map(Constraint.Component::type).collect(Collectors.toList()));
		collect.addAll(types);
		return collect;
	}

	private List<Constraint.Parameter> parameterConstrains() {
		return this.constraints().stream().filter(c -> c instanceof Constraint.Parameter).map(c -> (Constraint.Parameter) c).collect(Collectors.toList());
	}

	private Context add(Constraint... constraint) {
		this.constraints.addAll(Arrays.asList(constraint));
		return this;
	}
}
