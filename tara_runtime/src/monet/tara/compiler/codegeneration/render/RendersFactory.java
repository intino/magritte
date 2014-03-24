package monet.tara.compiler.codegeneration.render;

import com.intellij.openapi.diagnostic.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class RendersFactory {

	private static final Logger LOG = Logger.getInstance(RendersFactory.class.getName());
	private static Map<String, Class<? extends DefaultRender>> renders = new HashMap<>();

	static {
		renders.put("Definition", DefinitionRender.class);
		renders.put("SyntaxHighlighter.java", HighlightRender.class);
	}


	public static DefaultRender getRender(String name, String project, Object params) {
		try {
			Class<? extends DefaultRender> clazz = renders.get(RenderUtils.toProperCase(name));
			Constructor constructor = clazz.getConstructor(String.class, String.class, Object.class);
			return (DefaultRender) constructor.newInstance(TemplateFactory.getTemplate(name), project, params);
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
			LOG.error(e.getMessage());
			return null;
		} catch (NoSuchMethodException | NullPointerException e) {
			return new DefaultRender(TemplateFactory.getTemplate(name), project);
		}
	}
}
