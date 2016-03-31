package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import tara.compiler.model.NodeReference;
import tara.lang.model.*;
import tara.lang.model.rules.variable.CustomRule;
import tara.lang.model.rules.variable.NativeRule;
import tara.lang.model.rules.variable.WordRule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


class LayerVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {

	private final Set<String> imports = new HashSet<>();
	private int modelLevel;

	LayerVariableAdapter(Language language, String generatedLanguage, int modelLevel) {
		super(language, generatedLanguage);
		this.modelLevel = modelLevel;
	}

	@Override
	public void execute(Frame frame, Variable source, FrameContext<Variable> context) {
		createVarFrame(frame, source);
	}

	private Frame createVarFrame(Frame frame, final Variable variable) {
		frame.addTypes(TypesProvider.getTypes(variable, modelLevel));
		frame.addFrame(NAME, variable.name());
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame(LANGUAGE, language.languageName().toLowerCase());
		frame.addFrame(CONTAINER, findContainer(variable));
		frame.addFrame(CONTAINER_NAME, buildContainerName(variable));
		frame.addFrame(QN, containerQN(variable));
		if (variable.values().stream().filter(v -> v != null).count() > 0 && !(variable.values().get(0) instanceof EmptyNode))
			addValues(frame, variable);
		if (variable.rule() != null) frame.addFrame(RULE, (Frame) ruleToFrame(variable.rule()));
		frame.addFrame(TYPE, getType(variable, generatedLanguage));
		if (Primitive.WORD.equals(variable.type())) fillWordVariable(frame, variable);
		else if (variable.type().equals(Primitive.FUNCTION) || variable.flags().contains(Tag.Reactive)) fillNativeVariable(frame, variable);
		return frame;
	}

	private void fillWordVariable(Frame frame, Variable variable) {
		if (variable.rule() instanceof CustomRule ||
			variable.rule() instanceof WordRule && ((WordRule) variable.rule()).isCustom())
			frame.addTypes(OUTDEFINED);
		else {
			final List<String> allowedWords = ((WordRule) variable.rule()).words();
			frame.addFrame(WORDS, allowedWords.toArray(new String[allowedWords.size()]));
		}
	}

	private String findContainer(Variable variable) {
		final NodeContainer container = variable.container();
		if (container instanceof FacetTarget) return asFacetTarget((FacetTarget) container);
		else if (container instanceof Node) return ((Node) container).name();
		return container.qualifiedName();
	}

	private String buildContainerName(Variable variable) {
		final NodeContainer container = variable.container();
		if (container instanceof FacetTarget) return ((Node) container.container()).name();
		else if (container instanceof Node) return ((Node) container).name();
		return container.qualifiedName();
	}

	private String containerQN(Variable variable) {
		final NodeContainer container = variable.container();
		if (container instanceof FacetTarget) return asFacetTarget((FacetTarget) container);
		else if (container instanceof Node) return buildQN((Node) container);
		return container.qualifiedName();
	}

	private String buildQN(Node node) {
		return NameFormatter.getQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, generatedLanguage.toLowerCase());
	}

	private String asFacetTarget(FacetTarget facetTarget) {
		final String nodeName = facetTarget.owner().name();
		return generatedLanguage.toLowerCase() + DOT +
			nodeName.toLowerCase() + DOT + Format.firstUpperCase().format(nodeName) + Format.firstUpperCase().format(facetTarget.targetNode().name());
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
		final NativeFormatter adapter = new NativeFormatter(generatedLanguage, language, NativeFormatter.calculatePackage(variable.container()), modelLevel == 0, null);
		if (Primitive.FUNCTION.equals(variable.type())) {
			adapter.fillFrameForFunctionVariable(frame, variable, next);
			imports.addAll(((NativeRule) variable.rule()).imports().stream().collect(Collectors.toList()));
		} else adapter.fillFrameNativeVariable(frame, variable, next);
	}

	public Set<String> getImports() {
		return imports;
	}
}
