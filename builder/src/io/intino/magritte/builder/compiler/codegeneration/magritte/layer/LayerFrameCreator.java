package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Adapter;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.Language;
import io.intino.tara.builder.core.CompilerConfiguration;
import io.intino.tara.builder.core.CompilerConfiguration.Level;
import io.intino.tara.builder.model.Model;
import io.intino.tara.builder.model.MogramReference;
import io.intino.tara.language.model.Mogram;
import io.intino.tara.language.model.MogramRoot;
import io.intino.tara.language.model.Tag;
import io.intino.tara.language.model.Variable;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.intino.tara.builder.utils.Format.firstUpperCase;
import static io.intino.tara.builder.utils.Format.javaValidName;


public class LayerFrameCreator implements TemplateTags {
	private final Map<Class, Adapter> adapters;
	private final String outDsl;
	private final String workingPackage;
	private final MogramAdapter mogramAdapter;
	private final LayerVariableAdapter variableAdapter;
	private Mogram initNode = null;

	private LayerFrameCreator(String outDsl, Language language, Level modelLevel, String workingPackage, String languageWorkingPackage) {
		this.outDsl = outDsl;
		this.workingPackage = workingPackage;
		this.adapters = new HashMap<>();
		this.adapters.put(Variable.class, variableAdapter = new LayerVariableAdapter(language, outDsl, modelLevel, workingPackage, languageWorkingPackage));
		this.adapters.put(Mogram.class, mogramAdapter = new MogramAdapter(outDsl, modelLevel, language, null, workingPackage, languageWorkingPackage));
	}

	public LayerFrameCreator(CompilerConfiguration conf, String language) {
		this(conf.model().outDsl(), conf.model().language().get(), conf.model().level(), conf.workingPackage(), conf.model().language().generationPackage());
	}

	public Map.Entry<String, Frame> create(Mogram node) {
		initNode = node;
		mogramAdapter.getImports().clear();
		variableAdapter.getImports().clear();
		mogramAdapter.setInitNode(initNode);
		final FrameBuilder builder = new FrameBuilder(LAYER).add(OUT_LANGUAGE, outDsl).add(WORKING_PACKAGE, workingPackage);
		addSlot(builder, node);
		addNodeImports(builder);
		return new SimpleEntry<>(calculateLayerPath(node, addWorkingPackage(builder)), builder.toFrame());
	}

	private void addSlot(FrameBuilder builder, Mogram node) {
		if (node instanceof MogramReference || node.is(Tag.Instance)) return;
		builder.add(NODE, new FrameBuilder().put(adapters).append(node).toFrame());
	}

	public Map.Entry<String, Frame> createDecorable(Mogram node) {
		final FrameBuilder builder = new FrameBuilder(LAYER, DECORABLE);
		final String aPackage = addWorkingPackage(builder);
		builder.add(NAME, node.name());
		if (!(node.container() instanceof MogramRoot)) builder.add(CONTAINER, node.container().qualifiedName());
		if (node.isAbstract()) builder.add(ABSTRACT, true);
		builder.add("decorableNode", node.components().stream().filter(c -> !c.isReference()).map(this::createDecorableFrame).toArray(Frame[]::new));
		return new SimpleEntry<>(calculateDecorablePath(node, aPackage), builder.toFrame());
	}

	private Frame createDecorableFrame(Mogram node) {
		FrameBuilder builder = new FrameBuilder("decorableNode").add("name", node.name());
		if (node.isAbstract()) builder.add("abstract", "abstract");
		builder.add("decorableNode", node.components().stream().filter(c -> !c.isReference()).map(this::createDecorableFrame).toArray(Frame[]::new));
		return builder.toFrame();
	}

	private String calculateLayerPath(Mogram node, String aPackage) {
		return aPackage + DOT + (node.is(Tag.Decorable) ? "Abstract" : "") + firstUpperCase().format(javaValidName().format(node.name()).toString());
	}

	private String calculateDecorablePath(Mogram node, String aPackage) {
		return aPackage + (node.container() instanceof Model ? "" : DOT + node.container().qualifiedName().toLowerCase()) + DOT + firstUpperCase().format(javaValidName().format(node.name()).toString());
	}

	private void addNodeImports(FrameBuilder builder) {
		Set<String> set = new HashSet<>(mogramAdapter.getImports());
		set.addAll(variableAdapter.getImports());
		if (!set.isEmpty()) builder.add(IMPORTS, set.toArray(new Object[0]));
	}

	private String addWorkingPackage(FrameBuilder builder) {
		String packagePath = workingPackage.toLowerCase();
		if (!packagePath.isEmpty()) builder.add(PACKAGE, packagePath);
		return packagePath.endsWith(".") ? packagePath.substring(0, packagePath.length() - 1) : packagePath;
	}
}