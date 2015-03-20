package siani.tara.compiler.model;

import java.util.ArrayList;
import java.util.List;

public class FacetTarget extends Element {

	private String destinyQN;
	private String destinyName;
	private String facetQN;
	private transient NodeObject destiny;
	private boolean always;
	private boolean intention;
	private List<Variable> variables = new ArrayList<>();
	private NodeTree inner = new NodeTree();

	public FacetTarget(String destiny) {
		this.destinyName = destiny;
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

	public NodeTree getInner() {
		return inner;
	}

	public boolean add(Node node) {
		return inner.add(node);
	}

	@Override
	public String toString() {
		return "Target{" + destinyQN + '}';
	}

	public FacetTarget clone() {
		FacetTarget target = new FacetTarget(this.getDestinyName());
		target.setDestiny(this.getDestiny());
		target.setDestinyQN(this.getDestinyQN());
		target.setAlways(this.isAlways());
		target.setIntention(this.isIntention());
		target.setFacetQN(this.getFacetQN());
		for (Node node : this.getInner())
			target.add(node.is(LinkNode.class) ? node : new LinkNode((DeclaredNode) node, null));
		for (Variable variable : this.getVariables())
			target.add(variable);
		return target;
	}

	public boolean isIntention() {
		return intention;
	}

	public void setIntention(boolean intention) {
		this.intention = intention;
	}

	public String getFacetQN() {
		return facetQN;
	}

	public void setFacetQN(String facetQN) {
		this.facetQN = facetQN;
	}
}
