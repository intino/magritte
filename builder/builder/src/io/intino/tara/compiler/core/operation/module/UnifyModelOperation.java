package io.intino.tara.compiler.core.operation.module;

import io.intino.tara.Language;
import io.intino.tara.compiler.core.CompilationUnit;
import io.intino.tara.compiler.core.SourceUnit;
import io.intino.tara.compiler.core.errorcollection.MergeException;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.parser.ASTMerger;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.intino.tara.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;
import static java.lang.System.out;

public class UnifyModelOperation extends ModuleUnitOperation {
	private static final Logger LOG = Logger.getGlobal();

	private CompilationUnit unit;

	public UnifyModelOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Collection<SourceUnit> sources) {
		try {
			if (unit.getConfiguration().isVerbose())
				out.println(PRESENTABLE_MESSAGE + "Tarac: Merging to global models");
			Map<Language, Model> models = new ASTMerger(sources, unit.getConfiguration()).doMerge();
			for (Language language : models.keySet())
				unit.addModel(language, models.get(language));
		} catch (Throwable e) {
			LOG.log(Level.SEVERE, "Error merging sources representations: " + e.getMessage(), e);
		}
	}
}
