package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.core.errorcollection.message.Message;
import siani.tara.compiler.dependencyresolution.DependencyResolver;
import siani.tara.compiler.dependencyresolution.FacetTargetResolver;
import siani.tara.compiler.dependencyresolution.InheritanceResolver;
import siani.tara.compiler.dependencyresolution.TerminalResolver;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.rt.TaraRtConstants;

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
			new DependencyResolver(model).resolve();
			new InheritanceResolver(model).resolve();
			new FacetTargetResolver(model).resolve();
			new TerminalResolver(model).resolve();
		} catch (DependencyException e) {
			LOG.severe("Error during dependency resolution: " + e.getMessage());
			compilationUnit.getErrorCollector().addError(Message.create(e, compilationUnit.getSourceUnits().get(e.getElement().getFile())), true);
		}
	}
}
