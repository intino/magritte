package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.SemanticException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.core.errorcollection.message.Message;
import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.compiler.semantic.SemanticAnalyzer;
import siani.tara.compiler.semantic.wrappers.LanguageElement;

import java.util.Collection;
import java.util.logging.Logger;

public class SemanticAnalysisOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(SemanticAnalysisOperation.class.getName());
	private static final String PROTEO = "Proteo";
	private CompilationUnit compilationUnit;

	public SemanticAnalysisOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
	}

	@Override
	public void call(Model model) {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Analyzing semantic");
			CompilerConfiguration conf = compilationUnit.getConfiguration();
			if (conf.getLanguage() == null) throw new TaraException("Error finding language.", true);
			new SemanticAnalyzer(model, conf.getLanguage()).analyze();
		} catch (TaraException e) {
			LOG.severe("Error linking with language: " + e.getMessage());
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		} catch (siani.tara.semantic.SemanticException e) {
			Element element = e.getOrigin() != null ? ((LanguageElement) e.getOrigin()).element() : null;
			SourceUnit sourceFromFile = getSourceFromFile(compilationUnit.getSourceUnits().values(), element);
			SemanticException semanticException = new SemanticException(e.getMessage(), e.getError());
			compilationUnit.getErrorCollector().addError(Message.create(semanticException, sourceFromFile));
		}
	}

	private SourceUnit getSourceFromFile(Collection<SourceUnit> values, siani.tara.compiler.model.Element origin) {
		if (origin == null) return null;
		for (SourceUnit value : values)
			if (value.getName().equals(origin.getFile())) return value;
		return null;
	}
}
