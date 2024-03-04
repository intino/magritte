package io.intino.magritte.framework.modelviews;

import io.intino.magritte.framework.Graph;

public class MockApplication extends MockPlatform {
	public MockApplication(Graph graph) {
		super(graph);
	}

	@SuppressWarnings("WeakerAccess")
	public MockApplication(Graph graph, MockApplication mockApplication) {
		super(graph, mockApplication);
	}
}
