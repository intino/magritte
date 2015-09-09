package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.Format;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.dsl.Proteo;
import tara.language.model.*;
import tara.templates.NativeTemplate;
import tara.templates.NativesContainerTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class NativeClassCreator {

	private static final Logger LOG = Logger.getLogger(NativeClassCreator.class.getName());

	private static final String JAVA = ".java";
	private final String nativePackage;
	private final Model model;
	private final CompilerConfiguration conf;
	private final File outDirectory;
	private final String generatedLanguage;

	public NativeClassCreator(Model model, CompilerConfiguration conf) {
		this.model = model;
		this.conf = conf;
		this.outDirectory = conf.getOutDirectory();
		generatedLanguage = (conf.getGeneratedLanguage() != null ? conf.getGeneratedLanguage().toLowerCase() : conf.getModule());
		nativePackage = generatedLanguage + File.separator + "natives" + File.separator;
	}

	public Map<String, String> serialize() {
		List<Parameter> parameters = new ArrayList<>();
		List<Variable> variables = new ArrayList<>();
		extractNativeParameters(model, parameters);
		extractNativeVariables(model, variables);
		return createNativeClasses(parameters, variables);
	}

	private Map<String, String> createNativeClasses(List<Parameter> parameters, List<Variable> variables) {
		Map<String, String> files = new HashMap<>();
		List<String> nativeCodes = new ArrayList<>();
		if (parameters.isEmpty() && variables.isEmpty()) return files;
		if (!parameters.isEmpty()) nativeCodes.addAll(createNativeParameterInnerClasses(parameters, files));
		if (!variables.isEmpty()) nativeCodes.addAll(createNativeVariableInnerClasses(variables, files));
		writeJavaCode(getNativeFile(), createNativeContainerClass(nativeCodes)).getAbsolutePath();
		return files;
	}

	private List<String> createNativeParameterInnerClasses(List<Parameter> natives, Map<String, String> files) {
		final Template template = NativeTemplate.create().add("javaValidName", Format.javaValidName());
		List<String> nativeCodes = new ArrayList<>();
		natives.forEach(n -> {
			FrameBuilder builder = new FrameBuilder();
			builder.register(Parameter.class, new NativeParameterAdapter(generatedLanguage, conf.getLanguage()));
			nativeCodes.add(template.format(builder.build(n)));
			if (!files.containsKey(n.file())) files.put(n.file(), getNativeFile().getAbsolutePath());

		});
		return nativeCodes;
	}

	private List<String> createNativeVariableInnerClasses(List<Variable> natives, Map<String, String> files) {
		final Template template = NativeTemplate.create().add("javaValidName", Format.javaValidName());
		List<String> nativeCodes = new ArrayList<>();
		natives.forEach(n -> {
			FrameBuilder builder = new FrameBuilder();
			builder.register(Variable.class, new NativeVariableAdapter(generatedLanguage, conf.getLanguage()));
			nativeCodes.add(template.format(builder.build(n)));
			if (!files.containsKey(n.file()))
				files.put(n.file(), getNativeFile().getAbsolutePath());
		});
		return nativeCodes;
	}

	private String createNativeContainerClass(List<String> nativeCodes) {
		Template nativeContainerTemplate = NativesContainerTemplate.create().add("javaValidName", Format.javaValidName());
		Frame nativeContainer = new Frame();
		nativeContainer.addTypes("nativeContainer");
		nativeContainer.addFrame("native", nativeCodes.toArray(new String[nativeCodes.size()]));
		nativeContainer.addFrame("generatedLanguage", generatedLanguage);
		if (!(conf.getLanguage() instanceof Proteo))
			nativeContainer.addFrame("language", conf.getLanguage().languageName());
		nativeContainer.addFrame("name", getPresentableName(new File(nativeName())));
		return nativeContainerTemplate.format(nativeContainer);
	}

	private String getPresentableName(File file) {
		return file.getName().substring(0, file.getName().lastIndexOf("."));
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

	private File getNativeFile() {
		return new File(outDirectory, nativePackage + nativeName());
	}

	private String nativeName() {
		return Format.javaValidName().format(generatedLanguage).toString() + "Natives" + JAVA;
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
