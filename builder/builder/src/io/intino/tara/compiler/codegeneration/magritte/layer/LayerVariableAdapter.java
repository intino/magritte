package io.intino.tara.compiler.codegeneration.magritte.layer;

import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.Generator;
import io.intino.tara.compiler.codegeneration.magritte.NameFormatter;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.lang.model.EmptyNode;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Primitive;
import io.intino.tara.lang.model.Variable;
import io.intino.tara.lang.model.rules.NativeCustomWordRule;
import io.intino.tara.lang.model.rules.NativeWordRule;
import io.intino.tara.lang.model.rules.variable.NativeRule;
import io.intino.tara.lang.model.rules.variable.VariableCustomRule;
import io.intino.tara.lang.model.rules.variable.WordRule;
import org.siani.itrules.Adapter;
import org.siani.itrules.engine.Context;
import org.siani.itrules.model.Frame;

import java.util.*;
import java.util.stream.Collectors;

import static io.intino.tara.lang.model.Tag.Reactive;


class LayerVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {

	private final Set<String> imports = new HashSet<>();
	private Level modelLevel;

	LayerVariableAdapter(Language language, String generatedLanguage, Level modelLevel, String workingPackage, String languageWorkingPackage) {
		super(language, generatedLanguage, workingPackage, languageWorkingPackage);
		this.modelLevel = modelLevel;
	}

	@Override
	public void adapt(Variable variable, Context context) {
		Frame frame = context.frame();
		frame.addTypes(TypesProvider.getTypes(variable, modelLevel));
		frame.addSlot(NAME, variable.name());
		frame.addSlot(OUT_LANGUAGE, outDsl.toLowerCase());
		frame.addSlot(WORKING_PACKAGE, workingPackage.toLowerCase());
		frame.addSlot(LANGUAGE, language.languageName().toLowerCase());
		Node container = variable.container();
		frame.addSlot(CONTAINER_NAME, container.name());
		frame.addSlot(QN, buildQN(container));
		if (variable.values().stream().filter(Objects::nonNull).count() > 0 && !(variable.values().get(0) instanceof EmptyNode))
			addValues(frame, variable);
		if (variable.rule() != null) {
			final Frame ruleFrame = ruleToFrame(variable.rule());
			if (ruleFrame != null) frame.addSlot(RULE, (Frame) ruleFrame);
		}
		frame.addSlot(TYPE, getType(variable));
		if (Primitive.WORD.equals(variable.type())) fillWordVariable(frame, variable);
		else if (variable.type().equals(Primitive.FUNCTION) || variable.flags().contains(Reactive))
			fillNativeVariable(frame, variable);
	}

	private void fillWordVariable(Frame frame, Variable variable) {
		if (variable.rule() instanceof VariableCustomRule || variable.rule() instanceof NativeCustomWordRule ||
				variable.rule() instanceof WordRule && ((WordRule) variable.rule()).isCustom())
			frame.addTypes(OUTDEFINED);
		else {
			final List<String> allowedWords = (variable.rule() instanceof NativeRule) ? ((NativeWordRule) variable.rule()).words() : ((WordRule) variable.rule()).words();
			frame.addSlot(WORDS, allowedWords.toArray(new String[allowedWords.size()]));
		}
	}

	private String buildQN(Node node) {
		return NameFormatter.getQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, workingPackage.toLowerCase());
	}

	private void addValues(Frame frame, Variable variable) {
		if (Primitive.WORD.equals(variable.type())) frame.addSlot(WORD_VALUES, getWordValues(variable));
		else if (Primitive.STRING.equals(variable.type())) frame.addSlot(VALUES, asString(variable.values()));
		else frame.addSlot(VALUES, variable.values().toArray());
	}

	private String[] getWordValues(Variable variable) {
		List<String> wordValues = variable.values().stream().map(Object::toString).collect(Collectors.toList());
		return wordValues.toArray(new String[wordValues.size()]);
	}

	private String[] asString(List<Object> objects) {
		List<String> values = objects.stream().map(object -> '"' + object.toString() + '"').collect(Collectors.toList());
		return values.toArray(new String[values.size()]);
	}

	private void fillNativeVariable(Frame frame, Variable variable) {
		final Object next = (variable.values().isEmpty() || !(variable.values().get(0) instanceof Primitive.Expression)) ?
				null : variable.values().get(0);
		final NativeFormatter adapter = new NativeFormatter(language, outDsl, NativeFormatter.calculatePackage(variable.container()), workingPackage, languageWorkingPackage, modelLevel.equals(Level.Solution), null);
		if (Primitive.FUNCTION.equals(variable.type())) {
			adapter.fillFrameForFunctionVariable(frame, variable, next);
			imports.addAll(new ArrayList<>(((NativeRule) variable.rule()).imports()));
		} else adapter.fillFrameNativeVariable(frame, variable, next);
	}

	public Set<String> getImports() {
		return imports;
	}
}
