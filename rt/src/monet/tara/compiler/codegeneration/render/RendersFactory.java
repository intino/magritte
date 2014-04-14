package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RendersFactory {

	private static final Logger LOG = Logger.getLogger(RendersFactory.class.getName());
	private static Map<String, Class<? extends DefaultRender>> renders = new HashMap<>();

	static {
		renders.put("Definition", DefinitionRender.class);
		renders.put("SyntaxHighlighter.java", HighlightRender.class);
		renders.put("PluginDescriptorRender.java", PluginDescriptorRender.class);
	}

	private RendersFactory() {
	}

	public static DefaultRender getRender(String name, String project, Object params) throws TaraException {
		try {
			Class<? extends DefaultRender> clazz = renders.get(RenderUtils.toProperCase(name));
			Constructor constructor = clazz.getConstructor(String.class, String.class, Object.class);
			return (DefaultRender) constructor.newInstance(TemplateFactory.getTemplate(name), project, params);
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
			LOG.severe(e.getMessage());
			return null;
		} catch (Exception e) {
			return new DefaultRender(TemplateFactory.getTemplate(name), project);
		}
	}
}
