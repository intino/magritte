package tara.compiler.core.operation.model;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.SemanticException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.errorcollection.message.Message;
import tara.compiler.core.errorcollection.message.WarningMessage;
import tara.compiler.model.Model;
import tara.compiler.semantic.SemanticAnalyzer;
import tara.lang.model.Element;
import tara.lang.semantics.errorcollector.SemanticFatalException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.Collection;
import java.util.logging.Logger;

import static tara.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class SemanticAnalysisOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(SemanticAnalysisOperation.class.getName());
	private CompilationUnit unit;
	private final CompilerConfiguration conf;

	public SemanticAnalysisOperation(CompilationUnit unit) {
		this.unit = unit;
		this.conf = unit.getConfiguration();
	}

	@Override
	public void call(Model model) {
		try {
			if (conf.isVerbose())
				System.out.println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + unit.getConfiguration().outDSL() + "]" + " Analyzing semantic...");
			if (conf.language() == null) throw new TaraException("Error finding language.", true);
			new SemanticAnalyzer(model, conf.resourcesDirectory()).analyze();
		} catch (TaraException e) {
			error(e);
		} catch (SemanticFatalException e) {
			semanticErrors(e);
		}
	}

	private void semanticErrors(SemanticFatalException fatal) {
		for (tara.lang.semantics.errorcollector.SemanticException e : fatal.exceptions()) {
			Element[] origins = e.origin() != null ? e.origin() : null;
			if (origins == null || origins.length == 0) return;
			SourceUnit sourceFromFile = getSourceFromFile(unit.getSourceUnits().values(), origins[0]);
			SemanticException semanticException = new SemanticException(e.getMessage(), e.getNotification());
			for (Element element : origins) {
				if (e.level() == SemanticNotification.Level.ERROR)
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
