package tara.compiler.codegeneration.magritte.morph;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import tara.compiler.model.NodeReference;
import tara.compiler.model.VariableImpl;
import tara.language.model.*;

import java.util.List;
import java.util.stream.Collectors;

import static tara.compiler.codegeneration.magritte.NameFormatter.firstUpperCase;
import static tara.language.model.Variable.NATIVE_SEPARATOR;

public class MorphVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {

	private final Language language;
	private final String generatedLanguage;
	private int modelLevel;

	public MorphVariableAdapter(String generatedLanguage, Language language, int modelLevel) {
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
		if (variable.contract() != null) frame.addFrame(CONTRACT, format(variable.contract()));
		frame.addFrame(TYPE, getType(variable, generatedLanguage));
		if (variable.type().equals(Variable.WORD)) {
			if (((VariableImpl) variable).isOutDefined()) frame.addTypes(OUTDEFINED);
			else frame.addFrame(WORDS, variable.allowedValues().toArray(new String[(variable.allowedValues().size())]));
		} else if (variable.type().equals(Primitives.NATIVE)) fillNativeVariable(frame, variable);
		return frame;
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
			nodeName.toLowerCase() + DOT +
			firstUpperCase(nodeName) + "_" + firstUpperCase(facetTarget.targetNode().name());
	}

	private void addValues(Frame frame, Variable variable) {
		if (Primitives.WORD.equals(variable.type()))
			frame.addFrame(WORD_VALUES, getWordValues(variable));
		if (Primitives.STRING.equals(variable.type()))
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

	private void fillNativeVariable(Frame frame, Variable variable) {
		final NativeFormatter adapter = new NativeFormatter(generatedLanguage, language, false);
		final Object next = (variable.defaultValues().isEmpty() || !(variable.defaultValues().get(0) instanceof Primitives.Expression)) ?
			null : variable.defaultValues().get(0);
		if (Primitives.NATIVE.equals(variable.type())) adapter.fillFrameForNativeVariable(frame, variable, next);
		else adapter.fillFrameExpressionVariable(frame, variable, next);
	}

	private String format(String contract) {
		if (contract == null) return "";
		final int i = contract.indexOf(NATIVE_SEPARATOR);
		return (i >= 0) ? contract.substring(0, i) : contract;
	}

}
