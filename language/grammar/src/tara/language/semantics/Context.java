package tara.language.semantics;

import tara.language.model.Element;
import tara.language.model.Node;
import tara.language.semantics.constraints.ConstraintTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Context implements AllowContainer {

	private final String[] types;
	private final List<Constraint> constraints = new ArrayList<>();
	private final List<Assumption> assumptions = new ArrayList<>();
	private Documentation documentation;
	private final List<Allow> allows = new ArrayList<>();

	public Context(String[] types, Constraint[] globalConstrains) {
		this.types = types.clone();
		Collections.addAll(constraints, globalConstrains);
	}

	public String[] types() {
		return types;
	}

	public List<Constraint> constraints() {
		return constraints;
	}

	public List<Assumption> assumptions() {
		return assumptions;
	}

	public List<Allow> allows() {
		return allows;
	}

	public Documentation doc() {
		return documentation;
	}

	public Context allow(final Allow... allows) {
		this.allows.addAll(Arrays.asList(allows));
		return add(new Constraint.Reject() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				List<Rejectable> rejectables = Rejectable.build(node);
				for (Allow allow : Context.this.allows) allow.check(node, rejectables);
				if (!rejectables.isEmpty()) throw new SemanticException(rejectables.get(0).error());
			}
		});
	}

	public Context doc(String file, int line, String doc) {
		documentation = new Documentation(file, line, doc);
		return this;
	}

	public Context assume(Assumption... assumptions) {
		this.assumptions.addAll(Arrays.asList(assumptions));
		return this;
	}


	public Context require(Constraint.Require... constraints) {
		new ConstraintTransformer(this).transformCorrespondingAllows(constraints);
		return add(constraints);
	}

	private Context add(Constraint... constraints) {
		this.constraints.addAll(Arrays.asList(constraints));
		return this;
	}

	public void commit() {
		if (allows.isEmpty()) allow();
	}


}
