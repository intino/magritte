package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.dependencyresolver.InsideModelDependencyResolver;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Model;

import java.util.logging.Logger;

public class ModelDependencyResolutionOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());

	public ModelDependencyResolutionOperation() {
	}

	public void call(Model model) throws TaraException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Resolving dependencies");
			InsideModelDependencyResolver resolver = new InsideModelDependencyResolver(model);
			resolver.resolve();
		} catch (DependencyException e) {
			LOG.severe("Error during Dependency resolution: " + e.getMessage());
			throw new TaraException("Error during Dependency resolution: " + e.getMessage());
		}
	}
}
