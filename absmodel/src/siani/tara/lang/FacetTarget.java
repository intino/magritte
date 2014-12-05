package siani.tara.lang;

import java.util.ArrayList;
import java.util.List;

public class FacetTarget {

	private String destinyQN;
	private String destinyName;
	private transient NodeObject destiny;
	private boolean always;
	private transient FacetTarget parentTarget;
	private ArrayList<Variable> variables = new ArrayList<>();
	private List<Node> inner = new ArrayList<>();

	public FacetTarget(String destiny, FacetTarget parentTarget) {
		this.destinyName = destiny;
		this.parentTarget = parentTarget;
	}

	public String getDestinyName() {
		return destinyName;
	}

	public String getDestinyQN() {
		return destinyQN;
	}

	public void setDestinyQN(String destinyQN) {
		this.destinyQN = destinyQN;
	}

	public NodeObject getDestiny() {
		return destiny;
	}

	public void setDestiny(NodeObject destiny) {
		this.destiny = destiny;
	}

	public FacetTarget getParentTarget() {
		return parentTarget;
	}

	public boolean isAlways() {
		return always;
	}

	public void setAlways(boolean always) {
		this.always = always;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public boolean add(Variable var) {
		return variables.add(var);
	}

	public void add(int index, Variable var) {
		variables.add(index, var);
	}

	public List<Reference> getVariableReferences() {
		return extractElements(variables, Reference.class);
	}

	private <T> List<T> extractElements(List items, Class<T> type) {
		List<T> result = new ArrayList<>();
		for (Object e : items)
			if (type.isAssignableFrom(e.getClass()))
				result.add((T) e);
		return result;
	}

	public List<Node> getInner() {
		return inner;
	}

	public boolean add(Node node) {
		return inner.add(node);
	}

	@Override
	public String toString() {
		return "Target{" + destinyQN + '}';
	}
}
