package monet.tara.intellij.plugingeneration.render;

import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;

public class DefaultRender extends Render {

	private static String templatesPath = "_resources_/tpl/";
	protected String tplName;
	protected String projectName;

	@Override
	protected void init() {
		render();
	}


	public DefaultRender(String tplName, String projectName) {
		super(new Logger(), templatesPath);
		this.tplName = tplName;
		this.projectName = projectName;
	}

	protected void render() {
		loadCanvas(tplName, true);
		addMark("projectName", projectName);
		addMark("projectProperName", RenderUtils.toProperCase(projectName));
	}

	private static class Logger implements CanvasLogger {
		@Override
		public void debug(String message, Object... args) {
		}
	}

}
