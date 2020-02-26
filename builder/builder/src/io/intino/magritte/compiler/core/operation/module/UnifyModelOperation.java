package io.intino.magritte.compiler.core.operation.module;

import io.intino.magritte.Language;
import io.intino.magritte.compiler.core.CompilationUnit;
import io.intino.magritte.compiler.core.SourceUnit;
import io.intino.magritte.compiler.model.Model;
import io.intino.magritte.compiler.parser.ASTMerger;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.intino.magritte.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class UnifyModelOperation extends ModuleUnitOperation {
	private static final Logger LOG = Logger.getGlobal();

	private CompilationUnit unit;

	public UnifyModelOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Collection<SourceUnit> sources) {
		try {
			if (unit.configuration().isVerbose())
				unit.configuration().out().println(PRESENTABLE_MESSAGE + "Tarac: Merging to global models");
			Map<Language, Model> models = new ASTMerger(sources, unit.configuration()).doMerge();
			for (Language language : models.keySet())
				unit.addModel(language, models.get(language));
		} catch (Throwable e) {
			LOG.log(Level.SEVERE, "Error merging sources representations: " + e.getMessage(), e);
		}
	}
}
