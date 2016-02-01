package tara.compiler.core.operation.model;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
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
	}
}
