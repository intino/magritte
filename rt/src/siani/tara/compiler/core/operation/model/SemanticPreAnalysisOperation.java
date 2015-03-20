package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.model.Model;
import siani.tara.compiler.model.Node;

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
//		try {
//			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + " Semantic pre-analysis");
//			SemanticPreAnalyzer analyzer = new SemanticPreAnalyzer(compilationUnit.getModel());
//			analyzer.analyze();
//		} catch (SemanticException e) {
//			for (SemanticError error : e.getErrors()) {
//				SourceUnit sourceFromFile = getSourceFromFile(compilationUnit.getSourceUnits().values(), error.getNode());
//				if (error instanceof SemanticError.FatalError) {
//					LOG.log(Level.SEVERE, "Error during semantic analyze: " + error.getMessage(), e);
//					compilationUnit.getErrorCollector().addError(Message.create(error, sourceFromFile));
//				} else
//					compilationUnit.getErrorCollector().addWarning(2, error.getMessage(), sourceFromFile);
//			}
//		}
	}

	private SourceUnit getSourceFromFile(Collection<SourceUnit> sources, Node node) {
		if (node == null) return null;
		for (SourceUnit source : sources)
			if (source.getName().equals(node.getFile())) return source;
		return null;
	}
}
