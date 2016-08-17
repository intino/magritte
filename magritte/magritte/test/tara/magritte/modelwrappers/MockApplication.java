package tara.magritte.modelwrappers;

import tara.magritte.Application;
import tara.magritte.Graph;

public class MockApplication extends MockPlatform implements Application {
	public MockApplication(Graph graph) {
		super(graph);
	}
}
