package tara.compiler.core.operation.model;

import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.core.errorcollection.message.Message;
import tara.compiler.dependencyresolution.*;
import tara.compiler.model.Model;

import java.util.logging.Logger;

public class ModelDependencyResolutionOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());
	private final CompilationUnit unit;

	public ModelDependencyResolutionOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Model model) {
		try {
			System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Resolving dependencies");
			new DependencyResolver(model, unit.getConfiguration().getWordsPath()).resolve();
			new InheritanceResolver(model).resolve();
			new FacetTargetResolver(model).resolve();
			new TerminalResolver(model).resolve();
			new FinalResolver(model).resolve();
			new NativeResolver(model, unit.getConfiguration().getNativePath()).resolve();
		} catch (DependencyException e) {
			LOG.severe("Error during dependency resolution: " + e.getMessage());
			unit.getErrorCollector().addError(Message.create(e, unit.getSourceUnits().get(e.getElement().file())), true);
		}
	}
}
