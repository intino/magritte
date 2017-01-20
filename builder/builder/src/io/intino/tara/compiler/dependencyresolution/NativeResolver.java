package io.intino.tara.compiler.dependencyresolution;

import io.intino.tara.compiler.core.errorcollection.DependencyException;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.Primitive.Expression;
import io.intino.tara.lang.model.Primitive.MethodReference;
import io.intino.tara.lang.model.rules.NativeWordRule;
import io.intino.tara.lang.model.rules.variable.NativeReferenceRule;
import io.intino.tara.lang.model.rules.variable.NativeRule;
import io.intino.tara.lang.model.rules.variable.ReferenceRule;
import io.intino.tara.lang.model.rules.variable.WordRule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class NativeResolver {
	private static final Logger LOG = Logger.getGlobal();

	private final Model model;
	private final File functionsDirectory;

	public NativeResolver(Model model, File functionsDirectory) {
		this.model = model;
		this.functionsDirectory = functionsDirectory;
	}

	public void resolve() throws DependencyException {
		for (Node node : model.components())
			resolve(node);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		resolveNative(node.variables());
		resolveNative(node.parameters());
		for (Node include : node.components()) resolve(include);
	}


	private void resolveNative(List<? extends Valued> valuedList) throws DependencyException {
		for (Valued valued : valuedList)
			if (valued.rule() instanceof NativeRule ||
					(!valued.values().isEmpty() && (valued.values().get(0) instanceof Expression || valued.values().get(0) instanceof MethodReference)) ||
					valued.flags().contains(Tag.Reactive))
				fillRule(valued);
	}

	private void fillRule(Valued valued) throws DependencyException {
		if (valued.rule() == null) valued.rule(new NativeRule("", "", new ArrayList<>()));
		else if (valued.rule() instanceof ReferenceRule)
			valued.rule(new NativeReferenceRule(((ReferenceRule) valued.rule()).allowedReferences()));
		else if (valued.rule() instanceof WordRule) valued.rule(new NativeWordRule(((WordRule) valued.rule()).words()));
		else if (!(valued.rule() instanceof NativeRule)) valued.rule(null);
		fillInfo(valued, (NativeRule) valued.rule());
	}

	private void fillInfo(Valued valued, NativeRule rule) throws DependencyException {
		if (valued instanceof Variable && valued.type().equals(Primitive.FUNCTION))
			fillVariableInfo((Variable) valued, rule);
	}

	private void fillVariableInfo(Variable variable, NativeRule rule) throws DependencyException {
		if (functionsDirectory == null || !functionsDirectory.exists())
			throw new DependencyException("reject.nonexisting.functions.directory", null);
		File[] files = functionsDirectory.listFiles((dir, filename) -> filename.endsWith(".java") && filename.substring(0, filename.lastIndexOf(".")).equalsIgnoreCase(rule.interfaceClass()));
		if ((files != null ? files.length : 0) == 0)
			throw new DependencyException("reject.nonexisting.variable.rule", variable);
		final String text = readFile(files[0]);
		final String signature = getSignature(text);
		if (signature.isEmpty()) throw new DependencyException("reject.native.signature.not.found", variable);
		else rule.signature(signature);
		rule.imports(getInterfaceImports(Arrays.asList(text.split("\n"))));
	}

	private String getSignature(String text) {
		text = text.substring(text.indexOf("{") + 1, text.indexOf(";", text.indexOf("{") + 1)).trim();
		text = text.replaceAll("//.*\n", "");
		if (!text.startsWith("public")) text = "public " + text;
		return text;
	}

	private List<String> getInterfaceImports(List<String> text) {
		return new ArrayList<>(text.stream().filter(line -> line.trim().startsWith("import ")).map(String::trim).collect(Collectors.toSet()));
	}

	private String readFile(File file) {
		try {
			return new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			LOG.severe("File cannot be read: " + file.getAbsolutePath());
			return "";
		}
	}
}
