package tara.compiler.core.operation.model;

import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.SemanticException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.errorcollection.message.Message;
import tara.compiler.model.Model;
import tara.compiler.semantic.SemanticAnalyzer;
import tara.lang.model.Element;

import java.util.Collection;
import java.util.logging.Logger;

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
				System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "[" + conf.getModule() + "]" + "Analyzing semantic");
			if (conf.getLanguage() == null) throw new TaraException("Error finding language.", true);
			new SemanticAnalyzer(model, conf.getLanguage(), conf.isDynamicLoad()).analyze();
		} catch (TaraException e) {
			error(e);
		} catch (tara.lang.semantics.SemanticException e) {
			semanticError(e);
		}
	}

	public void error(TaraException e) {
		LOG.severe(e.getMessage());
		throw new CompilationFailedException(unit.getPhase(), unit, e);
	}

	public void semanticError(tara.lang.semantics.SemanticException e) {
		Element element = e.getOrigin() != null ? e.getOrigin() : null;
		SourceUnit sourceFromFile = getSourceFromFile(unit.getSourceUnits().values(), element);
		SemanticException semanticException = new SemanticException(e.getMessage(), e.getError());
		unit.getErrorCollector().addError(Message.create(semanticException, sourceFromFile));
	}

	private SourceUnit getSourceFromFile(Collection<SourceUnit> values, Element origin) {
		if (origin == null) return null;
		for (SourceUnit value : values)
			if (value.getName().equals(origin.file())) return value;
		return null;
	}
}
