package tara.magritte.modelwrappers;

import tara.magritte.Application;
import tara.magritte.Model;

public class MockDomain extends MockEngine implements Application {
	public MockDomain(Model model) {
		super(model);
	}
}
