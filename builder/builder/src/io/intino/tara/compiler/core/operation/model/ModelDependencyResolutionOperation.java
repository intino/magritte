package io.intino.tara.compiler.core.operation.model;

import io.intino.tara.compiler.core.CompilationUnit;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.SourceUnit;
import io.intino.tara.compiler.core.errorcollection.DependencyException;
import io.intino.tara.compiler.core.errorcollection.message.DependencyErrorMessage;
import io.intino.tara.compiler.core.errorcollection.message.WarningMessage;
import io.intino.tara.compiler.dependencyresolution.*;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.lang.model.Element;

import java.util.Collection;
import java.util.logging.Logger;

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
			final CompilerConfiguration conf = unit.configuration();
			if (conf.isVerbose())
				unit.configuration().out().println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + conf.outLanguage() + "]" + " Resolving dependencies...");
			final DependencyResolver dependencyResolver = new DependencyResolver(model, conf.workingPackage(), conf.rulesDirectory(), conf.getSemanticRulesLib(), conf.getTempDirectory());
			dependencyResolver.resolve();
			notifyRulesNotLoaded(dependencyResolver);
			new InheritanceResolver(model).resolve();
			new FacetTargetResolver(model).resolve();
			new TerminalResolver(model, conf.level()).resolve();
			new NativeResolver(model, conf.functionsDirectory()).resolve();
		} catch (DependencyException e) {
			LOG.severe("Error during dependency resolution: " + e.getMessage());
			unit.getErrorCollector().addError(DependencyErrorMessage.create(e, unit.getSourceUnits().get(e.getElement().file())), true);
		}
	}

	private void notifyRulesNotLoaded(DependencyResolver dependencyResolver) {
		for (DependencyException entry : dependencyResolver.rulesNotLoaded()) {
			SourceUnit sourceFromFile = getSourceFromFile(unit.getSourceUnits().values(), entry.getElement());
			unit.getErrorCollector().addWarning(new WarningMessage(WarningMessage.PARANOIA, entry.getMessage(), sourceFromFile, entry.getLine(), entry.getElement().column()));
		}
	}

	private SourceUnit getSourceFromFile(Collection<SourceUnit> values, Element origin) {
		if (origin == null) return null;
		for (SourceUnit value : values)
			if (value.getName().equals(origin.file())) return value;
		return null;
	}
}