package tara.intellij.framework;

import com.intellij.openapi.module.Module;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.settings.ArtifactorySettings;

import java.io.File;
import java.io.IOException;

public class FrameworkExporter {

	private final File source;
	private final TaraFacetConfiguration configuration;
	private final Module module;
	private int code;

	public FrameworkExporter(Module module, File source) {
		this.module = module;
		configuration = TaraUtil.getFacetConfiguration(module);
		this.source = source;
	}

	public int export() throws IOException {
		code = 200;
		if (code == 200) code = put();
		return code;
	}

	private int put() throws IOException {
		ArtifactoryConnector connector = new ArtifactoryConnector(ArtifactorySettings.getSafeInstance(module.getProject()));
		return connector.putDsl(source, configuration.outputDsl(), version());
	}

	private String version() {
		return null;
	}
}