package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.SemanticException;
import siani.tara.compiler.core.errorcollection.message.Message;
import siani.tara.compiler.core.errorcollection.semantic.SemanticError;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.compiler.semantic.SemanticPreAnalyzer;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.Collection;
import java.util.logging.Logger;

public class SemanticPreAnalysisOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(SemanticPreAnalysisOperation.class.getName());
	private final CompilationUnit compilationUnit;


	public SemanticPreAnalysisOperation(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + " Semantic pre-analysis");
			SemanticPreAnalyzer analyzer = new SemanticPreAnalyzer(compilationUnit.getModel());
			analyzer.analyze();
		} catch (SemanticException e) {
			for (SemanticError error : e.getErrors()) {
				SourceUnit sourceFromFile = getSourceFromFile(compilationUnit.getSourceUnits().values(), error.getNode());
				if (error instanceof SemanticError.FatalError) {
					LOG.severe("Error during semantic analyze: " + error.getMessage());
					compilationUnit.getErrorCollector().addError(Message.create(error, sourceFromFile));
				} else
					compilationUnit.getErrorCollector().addWarning(2, error.getMessage(), sourceFromFile);
			}
		}
	}

	private SourceUnit getSourceFromFile(Collection<SourceUnit> sources, Node node) {
		if (node == null) return null;
		for (SourceUnit source : sources)
			if (source.getName().equals(node.getFile())) return source;
		return null;
	}
}
