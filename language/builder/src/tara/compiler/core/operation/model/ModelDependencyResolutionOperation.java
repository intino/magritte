package tara.compiler.core.operation.model;

import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.core.errorcollection.message.Message;
import tara.compiler.dependencyresolution.*;
import tara.compiler.model.Model;

import java.util.logging.Logger;

public class ModelDependencyResolutionOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());
	private final CompilationUnit unit;

	public ModelDependencyResolutionOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Model model) {
		try {
			final CompilerConfiguration conf = unit.getConfiguration();
			if (conf.isVerbose())
				System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Resolving dependencies");
			new DependencyResolver(model, conf.getGeneratedLanguage(), conf.getRulesDirectory(), conf.getSemanticRulesLib(), conf.getTempDirectory()).resolve();
			new InheritanceResolver(model).resolve();
			new FacetTargetResolver(model).resolve();
			new TerminalResolver(model, conf.getLevel()).resolve();
			new FinalResolver(model).resolve();
			new NativeResolver(model, conf.getNativePath(), conf.getGeneratedLanguage()).resolve();
		} catch (DependencyException e) {
			LOG.severe("Error during dependency resolution: " + e.getMessage());
			unit.getErrorCollector().addError(Message.create(e, unit.getSourceUnits().get(e.getElement().file())), true);
		}
	}
}
