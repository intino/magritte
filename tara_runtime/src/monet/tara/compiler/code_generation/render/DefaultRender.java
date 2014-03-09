package monet.tara.compiler.code_generation.render;

import monet.tara.compiler.core.error_collection.TaraException;
import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;

public class DefaultRender extends Render {

	protected String tplName;
	protected String projectName;

	public DefaultRender(String tplName, String projectName) {
		super(new Logger(), null);
		setPath(this.getClass().getResource("/tpl/").getPath());
		this.tplName = tplName;
		this.projectName = projectName;
	}

	@Override
	protected void init() {
		try {
			render();
		} catch (TaraException e) {
			e.printStackTrace();
		}
	}

	protected void render() throws TaraException {
		try {
			loadCanvas(tplName, true);
			addMark("projectName", projectName);
			addMark("projectProperName", RenderUtils.toProperCase(projectName));
		} catch (NullPointerException e) {
			throw new TaraException("Template not found: " + tplName);
		}

	}

	private static class Logger implements CanvasLogger {
		@Override
		public void debug(String message, Object... args) {
		}
	}

}
