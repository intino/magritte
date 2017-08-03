package io.intino.tara.magritte.modelviews;

import io.intino.tara.magritte.Graph;

public class MockApplication extends MockPlatform {
	public MockApplication(Graph graph) {
		super(graph);
	}

	@SuppressWarnings("WeakerAccess")
	public MockApplication(Graph graph, MockApplication mockApplication) {
		super(graph, mockApplication);
	}
}
