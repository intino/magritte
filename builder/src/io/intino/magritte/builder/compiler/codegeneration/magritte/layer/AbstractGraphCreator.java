package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.builder.CompilerConfiguration;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.builder.compiler.codegeneration.magritte.stash.StashCreator;
import io.intino.magritte.io.model.Stash;
import io.intino.tara.Language;
import io.intino.tara.model.Level;
import io.intino.tara.model.Mogram;
import io.intino.tara.model.Property;
import io.intino.tara.model.rules.Size;
import io.intino.tara.processors.Resolver;
import io.intino.tara.processors.model.Model;

import java.util.Collection;
import java.util.stream.Collectors;

import static io.intino.tara.model.Annotation.*;


public class AbstractGraphCreator extends Generator implements TemplateTags {
	private final CompilerConfiguration conf;

	public AbstractGraphCreator(Language language, CompilerConfiguration conf) {
		super(language, conf.dsl().outDsl(), conf.generationPackage(), conf.dsl().generationPackage());
		this.conf = conf;
	}

	public String create(Model model) {
		FrameBuilder builder = new FrameBuilder("graph");
		builder.add(WORKING_PACKAGE, workingPackage);
		builder.add(NAME, outDsl);
		collectMainNodes(model).stream().filter(node -> node.name() != null).
				forEach(node -> builder.add(NODE, createRootNodeFrame(node, model.sizeOf(node))));
		Stash stash = new StashCreator(model.components(), language, outDsl, conf).create();
		builder.add("stash", stashFrame(stash));
		builder.add("parentPackage", languageWorkingPackage);
		return Generator.customize(new GraphTemplate()).render(builder.toFrame());
	}

	private Frame createRootNodeFrame(Mogram node, Size size) {
		FrameBuilder builder = new FrameBuilder(NODE);
		if (size.isSingle()) builder.add(SINGLE);
		if (node.level() == Level.M1) builder.add(INSTANCE);
		if (node.is(Generalization)) builder.add(ABSTRACT);
		String qn = NameFormatter.getQn(node, workingPackage.toLowerCase());
		builder.add(QN, qn).add(STASH_QN, qn).add(OUT_LANGUAGE, outDsl).add(NAME, node.name());
		addType(node, size, builder);
		node.properties().stream().filter(variable -> variable.values().isEmpty()).forEach(variable -> builder.add(PROPERTY, frameOf(variable)));
		addTerminalProperties(node, builder);
		return builder.toFrame();
	}

	private void addType(Mogram node, Size rule, FrameBuilder builder) {
		String doc = node.metaMograms().get(0).doc();
		if (doc == null) return;
//		builder.add(CONCEPT_LAYER, doc.layer());
		builder.add(TYPE, nodeType(node, rule));
	}

	private String nodeType(Mogram node, Size rule) {
		return Resolver.shortType(node.types().get(0)) + (!rule.isSingle() ? "List" : "");
	}

	private Frame frameOf(Property property) {
		return new FrameBuilder(PROPERTY, property.type().getName())
				.put(Property.class, new LayerPropertyAdapter(language, outDsl, workingPackage, languageWorkingPackage))
				.append(property)
				.toFrame();
	}

	private Collection<Mogram> collectMainNodes(Model model) {
		return model.components().stream()
				.filter(n -> !n.is(Component) && !n.is(Feature))
				.collect(Collectors.toList());
	}
}