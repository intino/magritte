package monet.tara.compiler.code_generation.render;

import monet.tara.compiler.code_generation.DefinitionRender;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class RendersFactory {

	private static Map<String, Class<? extends DefaultRender>> renders = new HashMap<>();

	static {
		renders.put("Definition", DefinitionRender.class);
	}


	public static DefaultRender getRender(String name, String project, Object params) {
		try {
			Class<? extends DefaultRender> clazz = renders.get(RenderUtils.toProperCase(name));
			Constructor constructor = clazz.getConstructor(String.class, String.class, Object.class);
			return (DefaultRender) constructor.newInstance(TemplateFactory.getTemplate(name), project, params);
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException | NullPointerException e) {
			return new DefaultRender(TemplateFactory.getTemplate(name), project);
		}
	}
}
