package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.dependencyresolver.FacetTargetsResolver;
import siani.tara.compiler.dependencyresolver.InsideModelDependencyResolver;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Model;

import java.util.logging.Logger;

public class ModelDependencyResolutionOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());
	private final CompilationUnit compilationUnit;

	public ModelDependencyResolutionOperation(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	public void call(Model model) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Resolving dependencies");
			new InsideModelDependencyResolver(model).resolve();
			new FacetTargetsResolver(model).resolve();
		} catch (TaraException e) {
			LOG.severe("Error during dependency resolution: " + e.getMessage());
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}
}
