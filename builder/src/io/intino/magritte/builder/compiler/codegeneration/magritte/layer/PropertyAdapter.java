package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Adapter;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.builder.compiler.codegeneration.magritte.natives.NativeFormatter;
import io.intino.tara.Language;
import io.intino.tara.model.*;
import io.intino.tara.model.rules.CustomRule;
import io.intino.tara.model.rules.property.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.tara.model.Annotation.Reactive;


class PropertyAdapter extends Generator implements Adapter<Property>, TemplateTags {

	PropertyAdapter(Language language, String generatedLanguage, String workingPackage, String languageWorkingPackage) {
		super(language, generatedLanguage, workingPackage, languageWorkingPackage);
	}

	@Override
	public void adapt(Property property, FrameBuilderContext context) {
		TypesProvider.getTypes(property).forEach(context::add);
		context.add(NAME, property.name());
		context.add(OUT_LANGUAGE, outDsl.toLowerCase());
		context.add(WORKING_PACKAGE, workingPackage.toLowerCase());
		context.add(LANGUAGE, language.languageName().toLowerCase());
		Mogram container = property.container();
		context.add(CONTAINER_NAME, container.name());
		context.add(QN, cleanQn(buildQN(container)));
		if (property.values().stream().anyMatch(Objects::nonNull) && !(property.values().get(0) instanceof EmptyMogram))
			addValues(property, context);
		Rule<?> rule = rule(property);
		if (rule != null) {
			final FrameBuilder ruleBuilder = ruleToFrame(rule);
			if (ruleBuilder != null) context.add(RULE, ruleBuilder.toFrame());
		}
		context.add(TYPE, getType(property));
		if (Primitive.WORD.equals(property.type())) fillWordVariable(property, context);
		else if (property.type().equals(Primitive.FUNCTION) || property.annotations().contains(Reactive))
			fillNativeProperty(context, property);
	}

	private static Rule<?> rule(Property property) {
		return property.rules().stream()
				.filter(r -> r instanceof WordRule || r instanceof CustomRule || r instanceof FunctionRule)
				.findFirst()
				.orElse(null);
	}

	private void fillWordVariable(Property property, FrameBuilderContext context) {
		if (property.rule(PropertyCustomRule.class) != null || property.rule(NativeCustomWordRule.class) != null ||
				property.rule(WordRule.class) != null && property.rule(WordRule.class).isCustom())
			context.add(OUTDEFINED);
		else {
			final List<String> allowedWords = property.rule(FunctionRule.class) != null ?
					property.rule(NativeWordRule.class).words() :
					property.rule(WordRule.class).words();
			context.add(WORDS, allowedWords.toArray(new Object[0]));
		}
	}

	private String buildQN(Mogram mogram) {
		return NameFormatter.getQn(mogram, workingPackage.toLowerCase());
	}

	private void addValues(Property property, FrameBuilderContext context) {
		if (Primitive.WORD.equals(property.type())) context.add(WORD_VALUES, getWordValues(property));
		else if (Primitive.STRING.equals(property.type())) context.add(VALUES, asString(property.values()));
		else context.add(VALUES, property.values().toArray());
	}

	private String[] getWordValues(Property property) {
		return property.values().stream().map(Object::toString).toArray(String[]::new);
	}

	private String[] asString(List<Object> objects) {
		return objects.stream().map(object -> '"' + object.toString() + '"').toArray(String[]::new);
	}

	private void fillNativeProperty(FrameBuilderContext context, Property property) {
		final Object next = (property.values().isEmpty() || !(property.values().get(0) instanceof Primitive.Expression)) ?
				null : property.values().get(0);
		final NativeFormatter adapter = new NativeFormatter(language, outDsl, NativeFormatter.calculatePackage(property.container()), workingPackage, languageWorkingPackage, false, null);
		if (Primitive.FUNCTION.equals(property.type())) {
			adapter.fillFrameForFunctionProperty(property, next, context);
			imports.addAll(new ArrayList<>(property.rule(FunctionRule.class).imports()));
		} else adapter.fillFrameFunctionProperty(context, property, next);
	}
}
