package io.intino.magritte.builder.core.operation.model;

import io.intino.magritte.builder.core.CompilationUnit;
import io.intino.magritte.builder.core.CompilerConfiguration;
import io.intino.magritte.builder.core.SourceUnit;
import io.intino.magritte.builder.core.errorcollection.DependencyException;
import io.intino.magritte.builder.core.errorcollection.message.DependencyErrorMessage;
import io.intino.magritte.builder.core.errorcollection.message.WarningMessage;
import io.intino.magritte.builder.dependencyresolution.DependencyResolver;
import io.intino.magritte.builder.dependencyresolution.InheritanceResolver;
import io.intino.magritte.builder.dependencyresolution.NativeResolver;
import io.intino.magritte.builder.dependencyresolution.TerminalResolver;
import io.intino.magritte.builder.model.Model;
import io.intino.magritte.lang.model.Element;

import java.util.Collection;
import java.util.logging.Logger;

import static io.intino.magritte.builder.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class ModelDependencyResolutionOperation extends ModelOperation {
	private static final Logger LOG = Logger.getGlobal();

	public ModelDependencyResolutionOperation(CompilationUnit unit) {
		super(unit);
	}

	@Override
	public void call(Model model) {
		try {
			final CompilerConfiguration conf = unit.configuration();
			if (conf.isVerbose())
				unit.configuration().out().println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + conf.model().outDsl() + "]" + " Resolving dependencies...");
			final DependencyResolver dependencyResolver = new DependencyResolver(model, conf.workingPackage(), conf.rulesDirectory(), conf.getSemanticRulesLib(), conf.getTempDirectory());
			dependencyResolver.resolve();
			notifyRulesNotLoaded(dependencyResolver);
			new InheritanceResolver(model).resolve();
			new TerminalResolver(model, conf.model().level()).resolve();
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