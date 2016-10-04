package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import tara.compiler.core.CompilerConfiguration.Level;
import tara.compiler.model.NodeReference;
import tara.lang.model.*;
import tara.lang.model.rules.NativeWordRule;
import tara.lang.model.rules.variable.NativeRule;
import tara.lang.model.rules.variable.VariableCustomRule;
import tara.lang.model.rules.variable.WordRule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


class LayerVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {

	private final Set<String> imports = new HashSet<>();
	private Level modelLevel;

	LayerVariableAdapter(Language language, String generatedLanguage, Level modelLevel, String workingPackage) {
		super(language, generatedLanguage, workingPackage);
		this.modelLevel = modelLevel;
	}

	@Override
	public void execute(Frame frame, Variable source, FrameContext<Variable> context) {
		createVarFrame(frame, source);
	}

	private Frame createVarFrame(Frame frame, final Variable variable) {
		frame.addTypes(TypesProvider.getTypes(variable, modelLevel));
		frame.addFrame(NAME, variable.name());
		frame.addFrame(OUT_LANGUAGE, outDsl.toLowerCase());
		frame.addFrame(WORKING_PACKAGE, workingPackage.toLowerCase());
		frame.addFrame(LANGUAGE, language.languageName().toLowerCase());
		frame.addFrame(CONTAINER, variable.container().name());
		frame.addFrame(CONTAINER_NAME, variable.container().name());
		frame.addFrame(QN, buildQN(variable.container()));
		if (variable.values().stream().filter(v -> v != null).count() > 0 && !(variable.values().get(0) instanceof EmptyNode))
			addValues(frame, variable);
		if (variable.rule() != null) frame.addFrame(RULE, (Frame) ruleToFrame(variable.rule()));
		frame.addFrame(TYPE, getType(variable, workingPackage));
		if (Primitive.WORD.equals(variable.type())) fillWordVariable(frame, variable);
		else if (variable.type().equals(Primitive.FUNCTION) || variable.flags().contains(Tag.Reactive)) fillNativeVariable(frame, variable);
		return frame;
	}

	private void fillWordVariable(Frame frame, Variable variable) {
		if (variable.rule() instanceof VariableCustomRule ||
			variable.rule() instanceof WordRule && ((WordRule) variable.rule()).isCustom())
			frame.addTypes(OUTDEFINED);
		else {
			final List<String> allowedWords = (variable.rule() instanceof NativeRule) ? ((NativeWordRule) variable.rule()).words() : ((WordRule) variable.rule()).words();
			frame.addFrame(WORDS, allowedWords.toArray(new String[allowedWords.size()]));
		}
	}

	private String buildQN(Node node) {
		return NameFormatter.getQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, workingPackage.toLowerCase());
	}

	private void addValues(Frame frame, Variable variable) {
		if (Primitive.WORD.equals(variable.type())) frame.addFrame(WORD_VALUES, getWordValues(variable));
		else if (Primitive.STRING.equals(variable.type())) frame.addFrame(VALUES, asString(variable.values()));
		else frame.addFrame(VALUES, variable.values().toArray());
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
		final NativeFormatter adapter = new NativeFormatter(language, outDsl, NativeFormatter.calculatePackage(variable.container()), workingPackage, modelLevel.equals(Level.System), null);
		if (Primitive.FUNCTION.equals(variable.type())) {
			adapter.fillFrameForFunctionVariable(frame, variable, next);
			imports.addAll(((NativeRule) variable.rule()).imports().stream().collect(Collectors.toList()));
		} else adapter.fillFrameNativeVariable(frame, variable, next);
	}

	public Set<String> getImports() {
		return imports;
	}
}
