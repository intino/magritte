package io.intino.magritte.framework;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class M2 extends Layer {

	public M2(Node _node) {
		super(_node);
	}

	@SuppressWarnings("unused")
	public List<Concept> conceptList() {
		return core$().graph().conceptList().stream().
				filter(c -> c.metatype != null).
				collect(toList());
	}

}
