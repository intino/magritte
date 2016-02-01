package tara.intellij.framework;

import com.intellij.openapi.module.Module;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.io.File;
import java.io.IOException;

public class FrameworkExporter {

	private final File dslFile;
	private final TaraFacetConfiguration configuration;
	private int code;

	public FrameworkExporter(Module module, File dslFile) {
		configuration = TaraUtil.getFacetConfiguration(module);
		this.dslFile = dslFile;
	}

	public int export() throws IOException {
		code = 200;
		if (configuration.outputDslKey().isEmpty()) code = newDsl();
		if (code == 200) code = put(configuration.outputDslKey());
		return code;
	}

	private int newDsl() throws IOException {
		TaraHubConnector connector = new TaraHubConnector();
		final String generatedDslKey = connector.newDsl(configuration.outputDsl());
		if (generatedDslKey == null || generatedDslKey.isEmpty()) return 404;
		configuration.outputDslKey(generatedDslKey);
		return 200;
	}

	private int put(String key) throws IOException {
		TaraHubConnector connector = new TaraHubConnector();
		return connector.putDsl(key, dslFile);
	}
}