package io.intino.magritte.builder.compiler.core.operation.model;

import io.intino.magritte.builder.compiler.core.CompilationUnit;
import io.intino.magritte.builder.compiler.core.CompilerConfiguration;
import io.intino.magritte.builder.compiler.core.SourceUnit;
import io.intino.magritte.builder.compiler.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.compiler.core.errorcollection.SemanticException;
import io.intino.magritte.builder.compiler.core.errorcollection.TaraException;
import io.intino.magritte.builder.compiler.core.errorcollection.message.Message;
import io.intino.magritte.builder.compiler.core.errorcollection.message.WarningMessage;
import io.intino.magritte.builder.compiler.model.Model;
import io.intino.magritte.builder.compiler.semantic.SemanticAnalyzer;
import io.intino.magritte.lang.model.Element;
import io.intino.magritte.lang.semantics.errorcollector.SemanticFatalException;
import io.intino.magritte.lang.semantics.errorcollector.SemanticNotification;

import java.util.Collection;
import java.util.logging.Logger;

import static io.intino.magritte.builder.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class SemanticAnalysisOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(SemanticAnalysisOperation.class.getName());
	private CompilationUnit unit;
	private final CompilerConfiguration conf;

	public SemanticAnalysisOperation(CompilationUnit unit) {
		this.unit = unit;
		this.conf = unit.configuration();
	}

	@Override
	public void call(Model model) {
		try {
			if (conf.isVerbose())
				unit.configuration().out().println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + unit.configuration().model().outDsl() + "]" + " Analyzing semantic...");
			if (model.language() == null) throw new TaraException("Error finding language.", true);
			new SemanticAnalyzer(model).analyze();
		} catch (TaraException e) {
			error(e);
		} catch (SemanticFatalException e) {
			semanticErrors(e);
		}
	}

	private void semanticErrors(SemanticFatalException fatal) {
		for (io.intino.magritte.lang.semantics.errorcollector.SemanticException e : fatal.exceptions()) {
			Element[] origins = e.origin();
			if (origins == null || origins.length == 0) return;
			SourceUnit sourceFromFile = getSourceFromFile(unit.getSourceUnits().values(), origins[0]);
			SemanticException semanticException = new SemanticException(e.getMessage(), e.getNotification());
			for (Element element : origins) {
				if (e.level() == SemanticNotification.Level.ERROR || e.level() == SemanticNotification.Level.RECOVERABLE_ERROR)
					unit.getErrorCollector().addError(Message.create(semanticException, sourceFromFile));
				else if (e.level() == SemanticNotification.Level.WARNING)
					unit.getErrorCollector().addWarning(new WarningMessage(WarningMessage.PARANOIA, e.getMessage(), sourceFromFile, element != null ? element.line() : -1, element != null ? element.column() : -1));
			}
		}
	}

	public void error(TaraException e) {
		LOG.severe(e.getMessage());
		throw new CompilationFailedException(unit.getPhase(), unit, e);
	}

	private SourceUnit getSourceFromFile(Collection<SourceUnit> values, Element origin) {
		if (origin == null) return null;
		for (SourceUnit value : values)
			if (value.getName().equals(origin.file())) return value;
		return null;
	}
}
