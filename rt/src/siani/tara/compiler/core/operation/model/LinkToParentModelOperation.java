package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.dependencyresolver.ParentModelDependencyResolver;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Model;
import siani.tara.lang.util.ModelLoader;

import java.io.File;
import java.util.logging.Logger;

public class LinkToParentModelOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());
	private static final String PROTEO = "Proteo";
	private CompilationUnit compilationUnit;

	public LinkToParentModelOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
	}

	@Override
	public void call(Model model) {
		String parent = model.getParentModelName();
		if (parent == null || parent.equals(PROTEO)) return;
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Catching info from metamodel");
			Model parentModel = ModelLoader.load(new File(compilationUnit.getConfiguration().getMetamodelFile()));
			if (parentModel == null) throw new TaraException("Error finding metamodel.", true);
			new ParentModelDependencyResolver(model, parentModel).resolve();
		} catch (TaraException e) {
			LOG.severe("Error linking with metamodel: " + e.getMessage());
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}
}
