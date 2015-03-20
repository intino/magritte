package siani.tara.compiler.core.operation.model;

import siani.tara.Language;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.semantic.LanguageLoader;
import siani.tara.compiler.model.Model;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.compiler.semantic.SemanticAnalyzer;
import siani.tara.semantic.SemanticException;

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
		String parent = model.getParentModelName();
		if (parent == null || parent.equals(PROTEO)) return;
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Analyzing semantic");
			CompilerConfiguration conf = compilationUnit.getConfiguration();
			Language language = LanguageLoader.load(conf.getLanguage(), conf.getLanguageDirectory());
			if (language == null) throw new TaraException("Error finding language.", true);
			new SemanticAnalyzer(model, language).analyze();
		} catch (TaraException e) {
			//throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
			LOG.severe("Error linking with language: " + e.getMessage());
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		} catch (SemanticException e) {
//			SourceUnit sourceFromFile = getSourceFromFile(compilationUnit.getSourceUnits().values(), error.getNode());
//			if (error instanceof SemanticError.FatalError) {
//				LOG.log(Level.SEVERE, "Error during semantic analyze: " + e.getMessage(), e);
//				compilationUnit.getErrorCollector().addError(Message.create(error, sourceFromFile));
//			} else
//				compilationUnit.getErrorCollector().addWarning(2, error.getMessage(), sourceFromFile);
		}
	}
}
