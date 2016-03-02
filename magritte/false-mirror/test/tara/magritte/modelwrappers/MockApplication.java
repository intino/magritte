package tara.magritte.modelwrappers;

import tara.magritte.Application;
import tara.magritte.Model;

public class MockApplication extends MockPlatform implements Application {
	public MockApplication(Model model) {
		super(model);
	}
}
