package io.intino.magritte.lang.semantics.constraints;

import io.intino.magritte.lang.model.Element;
import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.semantics.Constraint;
import io.intino.magritte.lang.semantics.errorcollector.SemanticException;
import io.intino.magritte.lang.semantics.errorcollector.SemanticNotification;

import java.util.Arrays;
import java.util.List;

import static io.intino.magritte.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;


class MetaAspectConstraint implements Constraint.MetaAspect {

	private final String type;
	private final String[] with;

	MetaAspectConstraint(String type, String[] with) {
		this.type = type;
		this.with = with;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public String[] with() {
		return with;
	}

	@Override
	public void check(Element element) throws SemanticException {
		Node node = (Node) element;
		if (!is(node.types()))
			throw new SemanticException(new SemanticNotification(ERROR, "reject.aspect.with.no.constrains.in.context", node, Arrays.asList(this.with)));
	}


	private boolean is(List<String> nodeTypes) {
		if (with == null) return true;
		for (String aType : with)
			if (!nodeTypes.contains(aType)) return false;
		return true;
	}

}
