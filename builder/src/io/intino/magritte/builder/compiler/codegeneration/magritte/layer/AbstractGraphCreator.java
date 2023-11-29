package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.Language;
import io.intino.tara.Resolver;
import io.intino.tara.builder.core.CompilerConfiguration.Level;
import io.intino.tara.builder.model.Model;
import io.intino.tara.builder.model.MogramImpl;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.dsls.Meta;
import io.intino.tara.dsls.Proteo;
import io.intino.tara.language.model.Mogram;
import io.intino.tara.language.model.Variable;
import io.intino.tara.language.model.rules.Size;

import java.util.Collection;
import java.util.stream.Collectors;

import static io.intino.tara.language.model.Tag.*;

public class AbstractGraphCreator extends Generator implements TemplateTags {

	private final Level modelLevel;

	public AbstractGraphCreator(Language language, String outDSL, Level modelLevel, String workingPackage, String languageWorkingPackage) {
		super(language, outDSL, workingPackage, languageWorkingPackage);
		this.modelLevel = modelLevel;
	}

	public String create(Model model) {
		FrameBuilder builder = new FrameBuilder("graph");
		builder.add(WORKING_PACKAGE, workingPackage);
		builder.add(NAME, outDsl);
		collectMainNodes(model).stream().filter(node -> node.name() != null).
				forEach(node -> builder.add(NODE, createRootNodeFrame(node, model.sizeOf(node))));
		return Format.customize(new GraphTemplate()).render(builder.toFrame());
	}

	private Frame createRootNodeFrame(Mogram node, Size size) {
		FrameBuilder builder = new FrameBuilder(NODE);
		if (size.isSingle()) builder.add(SINGLE);
		if (node.isTerminal()) builder.add(CONCEPT);
		if (node.is(Instance)) builder.add(INSTANCE);
		if (node.isAbstract()) builder.add(ABSTRACT);
		String qn = NameFormatter.getQn(node, workingPackage.toLowerCase());
		builder.add(QN, qn);
		builder.add(STASH_QN, qn);
		builder.add(OUT_LANGUAGE, outDsl);
		addType(node, size, builder);
		builder.add(NAME, node.name());
		node.variables().stream().filter(variable -> variable.values().isEmpty()).forEach(variable -> builder.add(VARIABLE, frameOf(variable)));
		addTerminalVariables(node, builder);
		return builder.toFrame();
	}

	private void addType(Mogram node, Size rule, FrameBuilder builder) {
		if (!(language instanceof Proteo) && !(language instanceof Meta))
			builder.add(CONCEPT_LAYER, language.doc(node.type()).layer());
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
