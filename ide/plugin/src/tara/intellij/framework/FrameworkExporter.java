package tara.intellij.framework;

import com.intellij.openapi.module.Module;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.io.File;

public class FrameworkExporter {

	private final File dslFile;
	private final TaraFacetConfiguration configuration;

	public FrameworkExporter(Module module, File dslFile) {
		configuration = TaraUtil.getFacetConfiguration(module);
		this.dslFile = dslFile;
	}


	public void export() {
		if (configuration.getGeneratedDslKey().isEmpty()) newDsl();
		put(configuration.getGeneratedDslKey());
	}

	private void newDsl() {
		TaraHubConnector connector = new TaraHubConnector();
		configuration.setGeneratedDslKey(connector.newDsl(configuration.getGeneratedDslName()));
	}

	private void put(String key) {
		TaraHubConnector connector = new TaraHubConnector();
		connector.putDsl(key, dslFile);
	}
}
