package tara.intellij.framework;

import com.intellij.openapi.module.Module;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.io.File;
import java.io.IOException;

public class FrameworkExporter {

	private final File dslFile;
	private final TaraFacetConfiguration configuration;

	public FrameworkExporter(Module module, File dslFile) {
		configuration = TaraUtil.getFacetConfiguration(module);
		this.dslFile = dslFile;
	}




	public void export() throws IOException {
		if (configuration.getGeneratedDslKey().isEmpty()) newDsl();
		put(configuration.getGeneratedDslKey());
	}

	private void newDsl() throws IOException {
		TaraHubConnector connector = new TaraHubConnector();
		configuration.setGeneratedDslKey(connector.newDsl(configuration.getGeneratedDslName()));
	}

	private void put(String key) throws IOException {
		TaraHubConnector connector = new TaraHubConnector();
		connector.putDsl(key, dslFile);
	}
}
