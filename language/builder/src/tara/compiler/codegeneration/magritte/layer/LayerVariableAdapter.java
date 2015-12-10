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

import java.util.List;
import java.util.stream.Collectors;


public class LayerVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {

	private final Language language;
	private final String generatedLanguage;
	private int modelLevel;
	private Frame rootFrame = null;

	public LayerVariableAdapter(String generatedLanguage, Language language, int modelLevel) {
		this.language = language;
		this.generatedLanguage = generatedLanguage;
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
		if (!variable.defaultValues().isEmpty() && !(variable.defaultValues().get(0) instanceof EmptyNode))
			addValues(frame, variable);
		if (variable.rule() != null) frame.addFrame(RULE, (Frame) ruleToFrame(variable.rule()));
		frame.addFrame(TYPE, getType(variable, generatedLanguage));
		if (Primitive.WORD.equals(variable.type())) fillWordVariable(frame, variable);
		else if (variable.type().equals(Primitive.FUNCTION) || variable.flags().contains(Tag.Native))
			fillFunctionVariable(frame, variable);
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
		final String nodeName = ((Node) facetTarget.container()).name();
		return generatedLanguage.toLowerCase() + DOT +
			nodeName.toLowerCase() + DOT + Format.firstUpperCase().format(nodeName) + Format.firstUpperCase().format(facetTarget.targetNode().name());
	}

	private void addValues(Frame frame, Variable variable) {
		if (Primitive.WORD.equals(variable.type()))
			frame.addFrame(WORD_VALUES, getWordValues(variable));
		else if (Primitive.STRING.equals(variable.type()))
			frame.addFrame(VALUES, asString(variable.defaultValues()));
		else frame.addFrame(VALUES, variable.defaultValues().toArray());
	}

	private String[] getWordValues(Variable variable) {
		List<String> wordValues = variable.defaultValues().stream().map(Object::toString).collect(Collectors.toList());
		return wordValues.toArray(new String[wordValues.size()]);
	}

	private String[] asString(List<Object> objects) {
		List<String> values = objects.stream().map(object -> '"' + object.toString() + '"').collect(Collectors.toList());
		return values.toArray(new String[values.size()]);
	}

	private void fillFunctionVariable(Frame frame, Variable variable) {
		final Object next = (variable.defaultValues().isEmpty() || !(variable.defaultValues().get(0) instanceof Primitive.Expression)) ?
			null : variable.defaultValues().get(0);
		final NativeFormatter adapter = new NativeFormatter(generatedLanguage, language, NativeFormatter.calculatePackage(variable.container()), modelLevel == 0);
		if (Primitive.FUNCTION.equals(variable.type())) {
			adapter.fillFrameForNativeVariable(frame, variable, next);
			for (String i : ((NativeRule) variable.rule()).imports())
				rootFrame.addFrame(IMPORTS, i);
		} else adapter.fillFrameExpressionVariable(frame, variable, next);
	}

	public void setRootFrame(Frame rootFrame) {
		this.rootFrame = rootFrame;
	}
}
