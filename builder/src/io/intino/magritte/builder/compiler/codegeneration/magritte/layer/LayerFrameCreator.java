package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.builder.CompilerConfiguration;
import io.intino.itrules.Adapter;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.Language;
import io.intino.tara.model.*;
import io.intino.tara.processors.model.Model;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.intino.tara.builder.utils.Format.firstUpperCase;
import static io.intino.tara.builder.utils.Format.javaValidName;
import static io.intino.tara.model.Annotation.Generalization;
import static io.intino.tara.model.Level.M1;


public class LayerFrameCreator implements TemplateTags {
	private final Map<Class<?>, Adapter> adapters;
	private final String outDsl;
	private final String workingPackage;
	private final MogramAdapter mogramAdapter;
	private final PropertyAdapter propertyAdapter;
	private Mogram initNode = null;

	private LayerFrameCreator(String outDsl, Language language, String workingPackage, String languageWorkingPackage) {
		this.outDsl = outDsl;
		this.workingPackage = workingPackage;
		this.adapters = new HashMap<>();
		this.adapters.put(Property.class, propertyAdapter = new PropertyAdapter(language, outDsl, workingPackage, languageWorkingPackage));
		this.adapters.put(Mogram.class, mogramAdapter = new MogramAdapter(outDsl, language, null, workingPackage, languageWorkingPackage));
	}

	public LayerFrameCreator(CompilerConfiguration conf, Language language) {
		this(conf.dsl().outDsl(), language, conf.generationPackage(), conf.dsl().generationPackage());
	}

	public Map.Entry<String, Frame> create(Mogram mogram) {
		initNode = mogram;
		mogramAdapter.getImports().clear();
		propertyAdapter.getImports().clear();
		mogramAdapter.setInitNode(initNode);
		final FrameBuilder builder = new FrameBuilder(LAYER).add(OUT_LANGUAGE, outDsl).add(WORKING_PACKAGE, workingPackage);
		addSlot(builder, mogram);
		addNodeImports(builder);
		return new SimpleEntry<>(calculateLayerPath(mogram, addWorkingPackage(builder)), builder.toFrame());
	}

	private void addSlot(FrameBuilder builder, Mogram mogram) {
		if (mogram instanceof MogramReference || mogram.level().equals(M1))
			return;
		builder.add(NODE, new FrameBuilder().put(adapters).append(mogram).toFrame());
	}

	public Map.Entry<String, Frame> createDecorable(Mogram mogram) {
		final FrameBuilder builder = new FrameBuilder(LAYER, DECORABLE);
		final String aPackage = addWorkingPackage(builder);
		builder.add(NAME, mogram.name());
		if (!(mogram.container() instanceof MogramRoot))
			builder.add(CONTAINER, ((Mogram) mogram.container()).qualifiedName());
		if (mogram.is(Generalization)) builder.add(ABSTRACT, true);
		builder.add("decorableNode", mogram.components().stream().filter(c -> !(c instanceof MogramReference)).map(this::createDecorableFrame).toArray(Frame[]::new));
		return new SimpleEntry<>(calculateDecorablePath(mogram, aPackage), builder.toFrame());
	}

	private Frame createDecorableFrame(Mogram mogram) {
		FrameBuilder builder = new FrameBuilder("decorableNode").add("name", mogram.name());
		if (mogram.is(Generalization)) builder.add("abstract", "abstract");
		builder.add("decorableNode", mogram.components().stream().filter(c -> !(c instanceof MogramReference)).map(this::createDecorableFrame).toArray(Frame[]::new));
		return builder.toFrame();
	}

	private String calculateLayerPath(Mogram mogram, String aPackage) {
		return aPackage + DOT + (mogram.is(Annotation.Decorable) ? "Abstract" : "") + firstUpperCase().format(javaValidName().format(mogram.name()).toString());
	}

	private String calculateDecorablePath(Mogram node, String aPackage) {
		return aPackage + (node.container() instanceof Model ? "" : DOT + ((Mogram) node.container()).qualifiedName().toLowerCase()) + DOT + firstUpperCase().format(javaValidName().format(node.name()).toString());
	}

	private void addNodeImports(FrameBuilder builder) {
		Set<String> set = new HashSet<>(mogramAdapter.getImports());
		set.addAll(propertyAdapter.getImports());
		if (!set.isEmpty()) builder.add(IMPORTS, set.toArray(new Object[0]));
	}

	private String addWorkingPackage(FrameBuilder builder) {
		String packagePath = workingPackage.toLowerCase();
		if (!packagePath.isEmpty()) builder.add(PACKAGE, packagePath);
		return packagePath.endsWith(".") ? packagePath.substring(0, packagePath.length() - 1) : packagePath;
	}
}