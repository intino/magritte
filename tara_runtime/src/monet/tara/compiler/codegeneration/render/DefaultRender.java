package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;

public class DefaultRender extends Render {

	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(DefaultRender.class.getName());
	protected String tplName;
	protected String projectName;

	public DefaultRender(String tplName, String projectName) {
		super(new Logger(), null);
		setPath(this.getClass().getResource("/").getPath());
		this.tplName = tplName;
		this.projectName = projectName;
	}

	@Override
	protected void init() {
		try {
			render();
		} catch (TaraException ignored) {
			LOG.warning("Template not found: " + tplName + " in path " + path);
		}
	}

	protected void render() throws TaraException {
		try {
			loadCanvas(tplName, true);
			addMark("projectName", projectName.toLowerCase());
			addMark("projectProperName", RenderUtils.toProperCase(projectName));
			addMark("projectUpperName", projectName.toUpperCase());
		} catch (NullPointerException e) {
			throw new TaraException("Template not found: " + tplName);
		}

	}

	private static class Logger implements CanvasLogger {
		@Override
		public void debug(String message, Object... args) {
			LOG.info(message);
		}
	}

}
