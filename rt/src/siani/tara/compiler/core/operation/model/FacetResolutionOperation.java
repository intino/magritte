package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.core.errorcollection.message.Message;
import siani.tara.compiler.dependencyresolver.FacetTargetsResolver;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Model;

import java.util.logging.Logger;

public class FacetResolutionOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());
	private final CompilationUnit compilationUnit;

	public FacetResolutionOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Resolving Facets");
			new FacetTargetsResolver(model).resolve();
		} catch (DependencyException e) {
			LOG.severe("Error during facets resolution: " + e.getMessage());
			compilationUnit.getErrorCollector().addError(Message.create(e, compilationUnit.getSourceUnits().get(e.getNode().getFile())), true);
		}
	}
}
