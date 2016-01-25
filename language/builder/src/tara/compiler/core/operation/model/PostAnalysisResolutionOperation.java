package tara.compiler.core.operation.model;

import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.core.errorcollection.message.Message;
import tara.compiler.dependencyresolution.NativeParameterImportResolver;
import tara.compiler.model.Model;

import java.util.logging.Logger;

public class PostAnalysisResolutionOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());

	private final CompilationUnit unit;
	private final CompilerConfiguration conf;

	public PostAnalysisResolutionOperation(CompilationUnit unit) {
		this.unit = unit;
		this.conf = unit.getConfiguration();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		if (conf.isVerbose())
			System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "[" + conf.getModule() + "]" + " Resolving native imports");
		try {
			new NativeParameterImportResolver(model, conf.generatedLanguage(), conf.getImportsFile()).resolve();
		} catch (DependencyException e) {
			LOG.severe("Error during dependency resolution: " + e.getMessage());
			unit.getErrorCollector().addError(Message.create(e, unit.getSourceUnits().get(e.getElement().file())), true);
		}
	}
}
