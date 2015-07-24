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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NativeClassSerializer {

	private static final String JAVA = ".java";
	private final Model model;
	private final CompilerConfiguration conf;
	private final File outDirectory;
	private final String nativePackage = "magritte" + File.separator + "natives" + File.separator;

	public NativeClassSerializer(Model model, CompilerConfiguration conf) {
		this.model = model;
		this.conf = conf;
		this.outDirectory = conf.getOutDirectory();
	}

	public Map<String, List<String>> serialize() {
		List<Parameter> natives = new ArrayList<>();
		extractNativeParameters(model, natives);
		return createNativeClasses(natives);
	}

	private Map<String, List<String>> createNativeClasses(List<Parameter> natives) {
		Map<String, List<String>> files = new HashMap<>();
		final Template template = NativeTemplate.create().add("javaValidName", Format.javaValidName());
		natives.forEach(n -> {
			FrameBuilder builder = new FrameBuilder();
			builder.register(Parameter.class, new NativeParameterAdapter(conf.getGeneratedLanguage(), conf.getLanguage()));
			if (!files.containsKey(n.file()))
				files.put(n.file(), new ArrayList<>());
				files.get(n.file()).add(writeJavaCode(createNativeFile(n), template.format(builder.build(n))).toFile().getAbsolutePath());
		});
		return files;
	}

	private Path writeJavaCode(Path path, String nativeText) {
		try {
			path.toFile().getParentFile().mkdirs();
			path.toFile().createNewFile();
			return Files.write(path, nativeText.getBytes());
		} catch (IOException ignored) {
			ignored.printStackTrace();
		}
		return path;
	}

	private Path createNativeFile(Parameter parameter) {
		return new File(outDirectory, nativePackage + nativeName(parameter)).toPath();
	}

	private String nativeName(Parameter n) {
		return Format.javaValidName().format(n.name()) + "_" + n.getUID() + JAVA;
	}


	private void extractNativeParameters(NodeContainer node, List<Parameter> natives) {
		if (node instanceof NodeReference) return;
		if (node instanceof Parametrized) {
			Parametrized parametrized = (Parametrized) node;
			natives.addAll(parametrized.parameters().stream().filter(parameter -> Primitives.NATIVE.equals(parameter.inferredType())).collect(Collectors.toList()));
		}
		for (Node component : node.components())
			extractNativeParameters(component, natives);
		if (node instanceof Node) {
			for (FacetTarget facetTarget : ((Node) node).facetTargets()) extractNativeParameters(facetTarget, natives);
			for (Facet facet : ((Node) node).facets()) extractNativeParameters(facet, natives);
		}
	}
}
