package io.intino.tara.compiler.core.operation.model;

import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.errorcollection.DependencyException;
import io.intino.tara.compiler.dependencyresolution.*;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.core.CompilationUnit;
import io.intino.tara.compiler.core.errorcollection.message.DependencyErrorMessage;

import java.util.logging.Logger;

import static java.lang.System.out;
import static io.intino.tara.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class ModelDependencyResolutionOperation extends ModelOperation {
	private static final Logger LOG = Logger.getGlobal();
	private final CompilationUnit unit;

	public ModelDependencyResolutionOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Model model) {
		try {
			final CompilerConfiguration conf = unit.getConfiguration();
			if (conf.isVerbose())
				out.println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + conf.outDSL() + "]" + " Resolving dependencies...");
			new DependencyResolver(model, conf.workingPackage(), conf.rulesDirectory(), conf.getSemanticRulesLib(), conf.getTempDirectory()).resolve();
			new InheritanceResolver(model).resolve();
			new FacetTargetResolver(model).resolve();
			new TerminalResolver(model, conf.level()).resolve();
			new NativeResolver(model, conf.functionsDirectory()).resolve();
		} catch (DependencyException e) {
			LOG.severe("Error during dependency resolution: " + e.getMessage());
			unit.getErrorCollector().addError(DependencyErrorMessage.create(e, unit.getSourceUnits().get(e.getElement().file())), true);
		}
	}
}