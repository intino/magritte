package io.intino.tara.magritte;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class M3 extends Layer {

	public M3(Node _node) {
		super(_node);
	}

	public List<Concept> conceptList() {
		return node().graph().conceptList().stream().
				filter(c -> c.metatype == null).
				collect(toList());
	}
}
