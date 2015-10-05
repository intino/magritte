package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import tara.compiler.codegeneration.Format;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.language.model.*;
import tara.templates.NativeTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.io.File.separator;

public class NativesCreator {

	private static final Logger LOG = Logger.getLogger(NativesCreator.class.getName());

	private static final String JAVA = ".java";
	private final String nativesPackage;
	private final Model model;
	private final CompilerConfiguration conf;
	private final File outDirectory;
	private final String generatedLanguage;

	public NativesCreator(Model model, CompilerConfiguration conf) {
		this.model = model;
		this.conf = conf;
		this.outDirectory = conf.getOutDirectory();
		generatedLanguage = (conf.getGeneratedLanguage() != null ? conf.getGeneratedLanguage().toLowerCase() : conf.getModule());
		nativesPackage = generatedLanguage.toLowerCase() + separator + "natives" + separator;
	}

	public Map<String, String> serialize() {
		List<Parameter> parameters = new ArrayList<>();
		List<Variable> variables = new ArrayList<>();
		extractNativeParameters(model, parameters);
		extractNativeVariables(model, variables);
		return createNativeClasses(parameters, variables);
	}

	private Map<String, String> createNativeClasses(List<Parameter> parameters, List<Variable> variables) {
		Map<String, String> destinyToOrigin = new HashMap<>();
		Map<File, String> nativeCodes = new HashMap<>();
		if (parameters.isEmpty() && variables.isEmpty()) return destinyToOrigin;
		if (!parameters.isEmpty()) nativeCodes.putAll(createNativeParameterInnerClasses(parameters, destinyToOrigin));
		if (!variables.isEmpty()) nativeCodes.putAll(createNativeVariableInnerClasses(variables, destinyToOrigin));
		for (Map.Entry<File, String> nativeCode : nativeCodes.entrySet())
			writeJavaCode(nativeCode.getKey(), nativeCode.getValue());
		return destinyToOrigin;
	}

	private Map<File, String> createNativeParameterInnerClasses(List<Parameter> natives, Map<String, String> originToDestiny) {
		final Template template = NativeTemplate.create().add("javaValidName", Format.javaValidName());
		Map<File, String> nativeCodes = new LinkedHashMap<>();
		natives.forEach(n -> {
			FrameBuilder builder = new FrameBuilder();
			builder.register(Parameter.class, new NativeParameterAdapter(generatedLanguage, conf.getLanguage(), calculatePackage(n.container())));
			final File destiny = calculateDestiny(n);
			nativeCodes.put(destiny, template.format(builder.build(n)));
			if (!originToDestiny.containsKey(n.file()))
				originToDestiny.put(destiny.getAbsolutePath(), n.file());

		});
		return nativeCodes;
	}

	private Map<File, String> createNativeVariableInnerClasses(List<Variable> natives, Map<String, String> files) {
		final Template template = NativeTemplate.create().add("javaValidName", Format.javaValidName());
		Map<File, String> nativeCodes = new LinkedHashMap<>();
		natives.forEach(n -> {
			FrameBuilder builder = new FrameBuilder();
			builder.register(Variable.class, new NativeVariableAdapter(generatedLanguage, conf.getLanguage(), calculatePackage(n.container())));
			final File destiny = calculateDestiny(n);
			nativeCodes.put(destiny, template.format(builder.build(n)));
			if (!files.containsKey(n.file()))
				files.put(destiny.getAbsolutePath(), n.file());
		});
		return nativeCodes;
	}

	private File calculateDestiny(Parameter parameter) {
		return new File(outDirectory, nativesPackage + calculatePackage(parameter.container()).replace(".", File.separator) + separator + nativeName(parameter));
	}

	private File calculateDestiny(Variable variable) {
		return new File(outDirectory, nativesPackage + calculatePackage(variable.container()).replace(".", File.separator) + separator + nativeName(variable));
	}

	private String calculatePackage(NodeContainer container) {
		final NodeContainer nodeContainer = firstNamedContainer(container);
		return nodeContainer != null ? nodeContainer.qualifiedNameCleaned().replace("$", ".").toLowerCase() : "";
	}

	private NodeContainer firstNamedContainer(NodeContainer container) {
		List<NodeContainer> containers = collectStructure(container);
		NodeContainer candidate = null;
		for (NodeContainer nodeContainer : containers) {
			if (nodeContainer instanceof Node && !((Node) nodeContainer).isAnonymous()) candidate = nodeContainer;
			else if (nodeContainer instanceof Node) break;
			else candidate = nodeContainer;
		}
		return candidate;
	}

	private List<NodeContainer> collectStructure(NodeContainer container) {
		List<NodeContainer> containers = new ArrayList<>();
		NodeContainer current = container;
		while (current != null && !(current instanceof NodeRoot)) {
			containers.add(0, current);
			current = current.container();
		}
		return containers;
	}

	private String nativeName(Variable variable) {
		return Format.javaValidName().format(variable.name()).toString() + "_" + variable.getUID() + JAVA;
	}

	private String nativeName(Parameter parameter) {
		return Format.javaValidName().format(parameter.name()).toString() + "_" + parameter.getUID() + JAVA;
	}

	private File writeJavaCode(File file, String nativeText) {
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
			Files.write(file.toPath(), nativeText.getBytes());
		} catch (IOException e) {
			LOG.severe(e.getMessage());
		}
		return file;
	}

	private void extractNativeParameters(NodeContainer node, List<Parameter> natives) {
		if (node instanceof NodeReference) return;
		if (node instanceof Parametrized)
			natives.addAll(((Parametrized) node).parameters().stream().
				filter(parameter -> Primitives.NATIVE.equals(parameter.inferredType())).
				collect(Collectors.toList()));
		for (Node component : node.components())
			extractNativeParameters(component, natives);
		if (node instanceof Node) {
			for (FacetTarget facetTarget : ((Node) node).facetTargets()) extractNativeParameters(facetTarget, natives);
			for (Facet facet : ((Node) node).facets()) extractNativeParameters(facet, natives);
		}
	}

	private void extractNativeVariables(NodeContainer node, List<Variable> natives) {
		if (node instanceof NodeReference) return;
		natives.addAll(node.variables().stream().
			filter(variable -> Primitives.NATIVE.equals(variable.type()) && !variable.defaultValues().isEmpty()).
			collect(Collectors.toList()));
		for (Node component : node.components()) extractNativeVariables(component, natives);
		if (node instanceof Node) {
			for (FacetTarget facetTarget : ((Node) node).facetTargets()) extractNativeVariables(facetTarget, natives);
			for (Facet facet : ((Node) node).facets()) extractNativeVariables(facet, natives);
		}
	}
}
