package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.codegeneration.model.ModelProvider;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.dependencyresolver.ParentModelDependencyResolver;
import siani.tara.lang.Model;

import java.io.File;
import java.util.logging.Logger;

public class LinkToParentModelOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());
	private CompilationUnit compilationUnit;

	public LinkToParentModelOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
	}


	@Override
	public void call(Model model) throws TaraException {
		String parent = model.getModelName();
		if (parent == null) return;
		String[] split = parent.split("\\.");
		if (split.length != 2) throw new TaraException("Error finding parent model.", true);
		String pathname = compilationUnit.getConfiguration().getModelsDirectory();
		Model parentModel = ModelProvider.getModel(pathname + File.separator + split[0], split[1]);
		if (parentModel == null) throw new TaraException("Error finding parent model.", true);
		ParentModelDependencyResolver resolver = new ParentModelDependencyResolver(model, parentModel);

	}
}
