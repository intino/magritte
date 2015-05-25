package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.core.errorcollection.message.Message;
import siani.tara.compiler.dependencyresolution.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.rt.TaraRtConstants;

import java.util.logging.Logger;

public class ModelDependencyResolutionOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());
	private final CompilationUnit unit;

	public ModelDependencyResolutionOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	public void call(Model model) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Resolving dependencies");
			new DependencyResolver(model).resolve();
			new InheritanceResolver(model).resolve();
			new FacetTargetResolver(model).resolve();
			new TerminalResolver(model).resolve();
			new NativeResolver(model, unit.getConfiguration().getNativePath()).resolve();
		} catch (DependencyException e) {
			LOG.severe("Error during dependency resolution: " + e.getMessage());
			unit.getErrorCollector().addError(Message.create(e, unit.getSourceUnits().get(e.getElement().getFile())), true);
		}
	}
}
