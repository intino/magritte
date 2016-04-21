package tara.compiler.core.operation.model;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.core.errorcollection.message.Message;
import tara.compiler.dependencyresolution.*;
import tara.compiler.model.Model;

import java.util.logging.Logger;

import static tara.compiler.constants.TaraBuildConstants.PRESENTABLE_MESSAGE;

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
			if (conf.isVerbose()) System.out.println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + "]" + " Resolving dependencies");
			new DependencyResolver(model, conf.generatedLanguage(), conf.getRulesDirectory(), conf.getSemanticRulesLib(), conf.getTempDirectory()).resolve();
			new InheritanceResolver(model).resolve();
			new FacetTargetResolver(model).resolve();
			new TerminalResolver(model, conf.level()).resolve();
			new NativeResolver(model, conf.getNativePath()).resolve();
		} catch (DependencyException e) {
			LOG.severe("Error during dependency resolution: " + e.getMessage());
			unit.getErrorCollector().addError(Message.create(e, unit.getSourceUnits().get(e.getElement().file())), true);
		}
	}
}