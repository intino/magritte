package siani.tara.compiler.core.operation;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraRtConstants;
import siani.tara.compiler.core.operation.model.ModelOperation;
import siani.tara.compiler.model.Facet;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.Parameter;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.Stash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelToStashOperation extends ModelOperation {
	private static final String STASH = ".stash";
	private File outFolder;

	public ModelToStashOperation(CompilationUnit compilationUnit) {
		super();
		outFolder = compilationUnit.getConfiguration().getOutDirectory();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		for (Node node : model.getIncludedNodes())
			write(model.getFile(), createRootStash(node, new Stash.Root()));
	}

	private Stash.Root createRootStash(Node node, Stash.Root root) {
		root.name = node.getName();
		root.types = collectTypes(node);
		root.variables = collectVariables(node);
		root.components = collectComponents(node.getIncludedNodes());
		return root;
	}

	private Stash.Case createCaseStash(Node node, Stash.Case aCase) {
		aCase.name = node.getName();
		aCase.types = collectTypes(node);
		aCase.variables = collectVariables(node);
		aCase.components = collectComponents(node.getIncludedNodes());
		return aCase;
	}

	private Stash.Case[] collectComponents(List<Node> components) {
		final List<Stash.Case> cases = components.stream().map(component -> createCaseStash(component, new Stash.Case())).collect(Collectors.toList());
		return cases.toArray(new Stash.Case[cases.size()]);
	}

	private Stash.Variable[] collectVariables(Node node) {
		List<Stash.Variable> variables = new ArrayList<>();
		for (Parameter parameter : node.getParameters())
			createVariable(variables, parameter);
		return variables.toArray(new Stash.Variable[variables.size()]);
	}

	private void createVariable(List<Stash.Variable> variables, Parameter parameter) {
		final Stash.Variable v = new Stash.Variable();
		v.name = parameter.getName();
		v.value = parameter.getValues().toArray();
		variables.add(v);
	}

	private String[] collectTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add(node.getType());
		types.addAll(node.getFacets().stream().map(Facet::getFacetType).collect(Collectors.toList()));
		return types.toArray(new String[types.size()]);
	}

	private void write(String name, Stash.Root content) {
		try {
			final byte[] bytes = StashSerializer.serialize(content);
			try (FileOutputStream stream = new FileOutputStream(new File(outFolder, name + STASH))) {
				stream.write(bytes);
				stream.close();
			}
		} catch (IOException ignored) {
			ignored.printStackTrace();
		}
	}


}
