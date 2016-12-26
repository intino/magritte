package io.intino.tara.magritte.modelwrappers;

import io.intino.tara.magritte.Application;
import io.intino.tara.magritte.Graph;

public class MockApplication extends MockPlatform implements Application {
	public MockApplication(Graph graph) {
		super(graph);
	}
}
