package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.dependencyresolver.ModelDependencyResolver;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Model;

import java.util.logging.Logger;

public class ModelDependencyResolutionOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());
	private CompilationUnit compilationUnit;

	public ModelDependencyResolutionOperation(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	public void call(Model model) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Resolving dependencies");
			ModelDependencyResolver resolver = new ModelDependencyResolver(compilationUnit.getModel());
			resolver.resolve();
		} catch (DependencyException e) {
			LOG.severe("Error during Dependency resolution: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
