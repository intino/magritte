package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.builder.compiler.codegeneration.magritte.stash.StashCreator;
import io.intino.magritte.io.model.Stash;
import io.intino.tara.Language;
import io.intino.tara.Resolver;
import io.intino.tara.builder.core.CompilerConfiguration;
import io.intino.tara.builder.core.CompilerConfiguration.Level;
import io.intino.tara.builder.model.Model;
import io.intino.tara.builder.model.MogramImpl;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.language.model.Mogram;
import io.intino.tara.language.model.Variable;
import io.intino.tara.language.model.rules.Size;
import io.intino.tara.language.semantics.Documentation;

import java.util.Collection;
import java.util.stream.Collectors;

import static io.intino.tara.language.model.Tag.*;

public class AbstractGraphCreator extends Generator implements TemplateTags {
	private final Level modelLevel;
	private final CompilerConfiguration conf;

	public AbstractGraphCreator(Language language, CompilerConfiguration conf) {
		super(language, conf.model().outDsl(), conf.workingPackage(), conf.model().language().generationPackage());
		this.modelLevel = conf.model().level();
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
		return Format.customize(new GraphTemplate()).render(builder.toFrame());
	}

	private Frame createRootNodeFrame(Mogram node, Size size) {
		FrameBuilder builder = new FrameBuilder(NODE);
		if (size.isSingle()) builder.add(SINGLE);
		if (node.isTerminal()) builder.add(CONCEPT);
		if (node.is(Instance)) builder.add(INSTANCE);
		if (node.isAbstract()) builder.add(ABSTRACT);
		String qn = NameFormatter.getQn(node, workingPackage.toLowerCase());
		builder.add(QN, qn).add(STASH_QN, qn).add(OUT_LANGUAGE, outDsl).add(NAME, node.name());
		addType(node, size, builder);
		node.variables().stream().filter(variable -> variable.values().isEmpty()).forEach(variable -> builder.add(VARIABLE, frameOf(variable)));
		addTerminalVariables(node, builder);
		return builder.toFrame();
	}

	private void addType(Mogram node, Size rule, FrameBuilder builder) {
		Documentation doc = language.doc(node.type());
		if (doc == null) return;
		builder.add(CONCEPT_LAYER, doc.layer());
		builder.add(TYPE, nodeType(node, rule));
	}

	private String nodeType(Mogram node, Size rule) {
		return Resolver.shortType(node.type()) + (!rule.isSingle() ? "List" : "");
	}

	private Frame frameOf(Variable variable) {
		return new FrameBuilder(VARIABLE, variable.type().getName())
				.put(Variable.class, new LayerVariableAdapter(language, outDsl, modelLevel, workingPackage, languageWorkingPackage))
				.append(variable)
				.toFrame();
	}

	private Collection<Mogram> collectMainNodes(Model model) {
		return model.components().stream().filter(n -> !n.is(Component) && !n.into(Component) && !n.is(Feature) && !n.into(Feature) && !((MogramImpl) n).isVirtual()).collect(Collectors.toList());
	}
}