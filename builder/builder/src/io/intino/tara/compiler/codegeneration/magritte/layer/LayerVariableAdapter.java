package io.intino.tara.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Adapter;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.Generator;
import io.intino.tara.compiler.codegeneration.magritte.NameFormatter;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.shared.Configuration.Model.Level;
import io.intino.tara.lang.model.EmptyNode;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Primitive;
import io.intino.tara.lang.model.Variable;
import io.intino.tara.lang.model.rules.variable.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.tara.lang.model.Tag.Reactive;


class LayerVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {

	private Level modelLevel;

	LayerVariableAdapter(Language language, String generatedLanguage, Level modelLevel, String workingPackage, String languageWorkingPackage) {
		super(language, generatedLanguage, workingPackage, languageWorkingPackage);
		this.modelLevel = modelLevel;
	}

	@Override
	public void adapt(Variable variable, FrameBuilderContext context) {
		TypesProvider.getTypes(variable, modelLevel).forEach(context::add);
		context.add(NAME, variable.name());
		context.add(OUT_LANGUAGE, outDsl.toLowerCase());
		context.add(WORKING_PACKAGE, workingPackage.toLowerCase());
		context.add(LANGUAGE, language.languageName().toLowerCase());
		Node container = variable.container();
		context.add(CONTAINER_NAME, container.name());
		context.add(QN, cleanQn(buildQN(container)));
		if (variable.values().stream().anyMatch(Objects::nonNull) && !(variable.values().get(0) instanceof EmptyNode))
			addValues(variable, context);
		if (variable.rule() != null) {
			final FrameBuilder ruleBuilder = ruleToFrame(variable.rule());
			if (ruleBuilder != null) context.add(RULE, ruleBuilder.toFrame());
		}
		context.add(TYPE, getType(variable));
		if (Primitive.WORD.equals(variable.type())) fillWordVariable(variable, context);
		else if (variable.type().equals(Primitive.FUNCTION) || variable.flags().contains(Reactive))
			fillNativeVariable(context, variable);
	}

	private void fillWordVariable(Variable variable, FrameBuilderContext context) {
		if (variable.rule() instanceof VariableCustomRule || variable.rule() instanceof NativeCustomWordRule ||
				variable.rule() instanceof WordRule && ((WordRule) variable.rule()).isCustom())
			context.add(OUTDEFINED);
		else {
			final List<String> allowedWords = (variable.rule() instanceof NativeRule) ? ((NativeWordRule) variable.rule()).words() : ((WordRule) variable.rule()).words();
			context.add(WORDS, allowedWords.toArray(new Object[0]));
		}
	}

	private String buildQN(Node node) {
		return NameFormatter.getQn(node instanceof NodeReference ? ((NodeReference) node).destination() : node, workingPackage.toLowerCase());
	}

	private void addValues(Variable variable, FrameBuilderContext context) {
		if (Primitive.WORD.equals(variable.type())) context.add(WORD_VALUES, getWordValues(variable));
		else if (Primitive.STRING.equals(variable.type())) context.add(VALUES, asString(variable.values()));
		else context.add(VALUES, variable.values().toArray());
	}

	private String[] getWordValues(Variable variable) {
		return variable.values().stream().map(Object::toString).toArray(String[]::new);
	}

	private String[] asString(List<Object> objects) {
		return objects.stream().map(object -> '"' + object.toString() + '"').toArray(String[]::new);
	}

	private void fillNativeVariable(FrameBuilderContext context, Variable variable) {
		final Object next = (variable.values().isEmpty() || !(variable.values().get(0) instanceof Primitive.Expression)) ?
				null : variable.values().get(0);
		final NativeFormatter adapter = new NativeFormatter(language, outDsl, NativeFormatter.calculatePackage(variable.container()), workingPackage, languageWorkingPackage, modelLevel.equals(Level.Solution), null);
		if (Primitive.FUNCTION.equals(variable.type())) {
			adapter.fillFrameForFunctionVariable(variable, next, context);
			imports.addAll(new ArrayList<>(((NativeRule) variable.rule()).imports()));
		} else adapter.fillFrameNativeVariable(context, variable, next);
	}

}
