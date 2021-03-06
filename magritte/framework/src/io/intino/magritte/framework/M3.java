package io.intino.magritte.framework;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class M3 extends Layer {

	public M3(Node _node) {
		super(_node);
	}

	public List<Concept> conceptList() {
		return core$().graph().conceptList().stream().
				filter(c -> c.metatype == null).
				collect(toList());
	}
}
