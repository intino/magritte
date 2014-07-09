package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.codegeneration.intellij.PluginUpdater;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.Phases;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Model;

import java.util.logging.Logger;

public class PluginUpdateOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(PluginUpdateOperation.class.getName());
	private final CompilationUnit unit;

	public PluginUpdateOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Generating plugin code");
			PluginUpdater generator = new PluginUpdater(unit.getConfiguration());
			generator.generate(model);
			unit.getErrorCollector().failIfErrors();
		} catch (TaraException e) {
			LOG.severe("Error during plugin generation: " + e.getMessage() + "\n");
			throw new CompilationFailedException(Phases.PLUGIN_GENERATION, unit);
		}
	}
}
