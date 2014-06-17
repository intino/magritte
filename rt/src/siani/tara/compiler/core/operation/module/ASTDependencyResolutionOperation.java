package siani.tara.compiler.core.operation.module;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.core.errorcollection.message.Message;
import siani.tara.compiler.dependencyresolver.ModelDependencyResolver;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Node;

import java.util.Collection;
import java.util.logging.Logger;

public class ASTDependencyResolutionOperation extends ModuleUnitOperation {
	private static final Logger LOG = Logger.getLogger(ASTDependencyResolutionOperation.class.getName());
	private CompilationUnit compilationUnit;

	public ASTDependencyResolutionOperation(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	public void call(Collection<SourceUnit> sources) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Resolving dependencies");
			ModelDependencyResolver resolver = new ModelDependencyResolver(compilationUnit.getModel());
			compilationUnit.setModel(resolver.resolve());
		} catch (DependencyException e) {
			LOG.severe("Error during Dependency resolution: " + e.getMessage());
			e.printStackTrace();
			compilationUnit.getErrorCollector().addError(Message.create(e, getSourceFromFile(sources, e.getNode())));
		}
	}

	private SourceUnit getSourceFromFile(Collection<SourceUnit> sources, Node node) {
		if (node == null) return null;
		for (SourceUnit source : sources)
			if (source.getName().equals(node.getFile())) return source;
		return null;
	}
}
