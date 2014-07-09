package siani.tara.compiler.codegeneration.render;

import org.monet.templation.CanvasLogger;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.Model;

import java.util.Set;

public class JFlexRender extends DefaultRender {
	private static final String OR = " | ";
	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(JFlexRender.class.getName());
	private Set<String> identifiers;
	private StringBuilder keywords = new StringBuilder();

	public JFlexRender(String tplName, String projectName, Object ast) throws TaraException {
		super(tplName, projectName);
		this.identifiers = ((Model) ast).getIdentifiers();
	}

	@Override
	protected void init() {
		super.init();
		for (String identifier : identifiers)
			keywords.append(OR).append(identifier);
		addMark("concepts", keywords.toString().substring(OR.length()));
	}


	private static class Logger implements CanvasLogger {
		@Override
		public void debug(String message, Object... args) {
			LOG.severe(String.format(message, args));
		}
	}
}
