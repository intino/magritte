package tara.lang.semantics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static tara.lang.semantics.constraints.ConstraintHelper.*;
import static tara.lang.semantics.constraints.RuleFactory.*;

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
		return rejectOthers();
	}

	public Context rejectOthers() {
		this.constraints().add(rejectOtherComponents(componentConstrains(this.constraints())));
		this.constraints().add(rejectOtherParameters(parameterConstrains(this.constraints())));
		this.constraints().add(rejectOtherFacets(facetConstrains(this.constraints())));
		return this;
	}

}
