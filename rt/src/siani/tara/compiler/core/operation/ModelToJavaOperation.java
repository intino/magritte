package siani.tara.compiler.core.operation;

import siani.tara.compiler.codegeneration.java.BoxWriter;
import siani.tara.compiler.codegeneration.java.JavaClassWriter;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.core.operation.model.ModelDependencyResolutionOperation;
import siani.tara.compiler.core.operation.model.ModelOperation;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ModelToJavaOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());
	private CompilationUnit compilationUnit;
	private CompilerConfiguration configuration;

	public ModelToJavaOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		configuration = compilationUnit.getConfiguration();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		try {
			createBoxClasses(model);
			createModelClasses(model);
		} catch (TaraException e) {
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}

	}

	private void createModelClasses(Model model) throws TaraException {
		List<String> toProcess = new ArrayList<>();
		toProcess.addAll(model.getNodeTable().keySet());
		Collections.sort(toProcess);
		for (String toProcessNode : toProcess) {
			Node node = model.get(toProcessNode);
			if (node instanceof DeclaredNode)
				new JavaClassWriter(node).write(configuration.getOutDirectory());
		}
	}

	private void createBoxClasses(Model model) throws TaraException {
		List<String> toProcess = new ArrayList<>();
		toProcess.addAll(model.getNodeTable().keySet());
		Collections.sort(toProcess);
		BoxWriter writer = new BoxWriter();
		String actualBox = "";
		for (String toProcessNode : toProcess) {
			Node node = model.get(toProcessNode);
			if (!node.getBox().equals(actualBox)) {
				if (!actualBox.isEmpty()) writer.write(configuration.getOutDirectory());
				actualBox = node.getBox();
				writer = new BoxWriter();
			}
			writer.setName(actualBox);
			writer.addNode(node);
		}
		if (!actualBox.isEmpty()) writer.write(configuration.getOutDirectory());

	}
}
