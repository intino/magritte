package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.Format;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.language.model.*;
import tara.templates.NativeTemplate;
import tara.templates.NativesContainerTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NativeClassCreator {

	private static final String JAVA = ".java";
	private final Model model;
	private final CompilerConfiguration conf;
	private final File outDirectory;
	private final String nativePackage = "magritte" + File.separator + "natives" + File.separator;

	public NativeClassCreator(Model model, CompilerConfiguration conf) {
		this.model = model;
		this.conf = conf;
		this.outDirectory = conf.getOutDirectory();
	}

	public Map<String, String> serialize() {
		List<Parameter> natives = new ArrayList<>();
		extractNativeParameters(model, natives);
		return createNativeClasses(natives);
	}

	private Map<String, String> createNativeClasses(List<Parameter> natives) {
		Map<String, String> files = new HashMap<>();
		List<String> nativeCodes = createNativeInnerClasses(natives, files);
		writeJavaCode(createNativeFile(), createNativeContainerClass(nativeCodes)).toFile().getAbsolutePath();
		return files;
	}

	private List<String> createNativeInnerClasses(List<Parameter> natives, Map<String, String> files) {
		final Template template = NativeTemplate.create().add("javaValidName", Format.javaValidName());
		List<String> nativeCodes = new ArrayList<>();
		natives.forEach(n -> {
			FrameBuilder builder = new FrameBuilder();
			builder.register(Parameter.class, new NativeParameterAdapter(conf.getGeneratedLanguage(), conf.getLanguage()));
			nativeCodes.add(template.format(builder.build(n)));
			files.put(n.file(), createNativeFile().toFile().getAbsolutePath());
		});
		return nativeCodes;
	}

	private String createNativeContainerClass(List<String> nativeCodes) {
		Template nativeContainerTemplate = NativesContainerTemplate.create().add("javaValidName", Format.javaValidName());
		Frame nativeContainer = new Frame();
		nativeContainer.addTypes("nativeContainer");
		nativeContainer.addFrame("native", nativeCodes.toArray(new String[nativeCodes.size()]));
		nativeContainer.addFrame("generatedLanguage", conf.getGeneratedLanguage());
		nativeContainer.addFrame("name", getPresentableName(new File(nativeName())));
		return nativeContainerTemplate.format(nativeContainer);
	}

	private String getPresentableName(File file) {
		return file.getName().substring(0, file.getName().lastIndexOf("."));
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

	private Path createNativeFile() {
		return new File(outDirectory, nativePackage + nativeName()).toPath();
	}

	private String nativeName() {
		return Format.javaValidName().format(conf.getGeneratedLanguage()).toString() + "Natives" + JAVA;
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
