package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.errorcollection.TaraException;

public class PluginDescriptorRender extends DefaultRender {
	CompilerConfiguration configuration;

	public PluginDescriptorRender(String tplName, String projectName, Object configuration) throws TaraException {
		super(tplName, projectName);
		this.configuration = (CompilerConfiguration) configuration;
	}

	@Override
	protected void init() {
		super.init();
		addMark("version", "\n<version>" + configuration.getVersion() + "</version>\n");
		addMark("description", "\n<description>\n<![CDATA[" + configuration.getDescription() + "]]>\n</description>\n");
	}
}

