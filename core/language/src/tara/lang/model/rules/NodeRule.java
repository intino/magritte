package tara.lang.model.rules;

import tara.lang.model.Node;
import tara.lang.model.Rule;

public interface NodeRule extends Rule<Node> {


	NodeRule is();

	void is(NodeRule rule);

	NodeRule into();

	void into(NodeRule rule);

}
