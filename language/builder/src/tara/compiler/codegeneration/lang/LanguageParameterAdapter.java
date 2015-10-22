package tara.compiler.codegeneration.lang;

import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.VariableReference;
import tara.language.model.Node;
import tara.language.model.Primitives;
import tara.language.model.Tag;
import tara.language.model.Variable;
import tara.language.semantics.Allow;
import tara.language.semantics.constraints.allowed.ReferenceParameterAllow;

import java.util.*;
import java.util.stream.Collectors;

import static tara.language.model.Tag.TERMINAL_INSTANCE;

public class LanguageParameterAdapter implements TemplateTags {
	private final Language language;
	private final Map<String, List<String>> metrics;
	private final String generatedLanguage;

	LanguageParameterAdapter(Language language, Map<String, List<String>> metrics, String generatedLanguage) {
		this.language = language;
		this.metrics = metrics;
		this.generatedLanguage = generatedLanguage;
	}

	void addParameterRequire(Frame frame, int i, Variable variable, String relation) {
		if (Primitives.WORD.equals(variable.type()))
			frame.addFrame(relation, wordParameter(i, variable, relation));
		else if (variable instanceof VariableReference)
			frame.addFrame(relation, referenceParameter(i, variable, relation));
		else frame.addFrame(relation, primitiveParameter(i, variable, relation));
	}

	int addTerminalParameterRequires(Node node, Frame requires) {
		int index = 0;
		Collection<Allow> allows = language.allows(node.type());
		if (allows == null) return 0;
		for (Allow allow : allows)
			if (allow instanceof Allow.Parameter && isTerminal((Allow.Parameter) allow) && !isRedefined((Allow.Parameter) allow, node.variables()) && isRequired((Allow.Parameter) allow)) {
				addParameter(requires, (Allow.Parameter) allow, index, REQUIRE);
				index++;
			}
		return index;
	}

	private boolean isRequired(Allow.Parameter allow) {
		return allow.defaultValue() == null;
	}

	int addTerminalParameterAllows(Node node, Frame allowsFrame) {
		int index = 0;
		Collection<Allow> nodeAllows = language.allows(node.type());
		if (nodeAllows == null) return 0;
		for (Allow allow : nodeAllows)
			if (allow instanceof Allow.Parameter && isTerminal((Allow.Parameter) allow) && !isRedefined((Allow.Parameter) allow, node.variables()) && !isRequired((Allow.Parameter) allow)) {
				addParameter(allowsFrame, (Allow.Parameter) allow, index, ALLOW);
				index++;
			}
		return index;
	}

	private boolean isRedefined(Allow.Parameter allow, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	private boolean isTerminal(Allow.Parameter allow) {
		for (String flag : allow.flags())
			if (flag.equalsIgnoreCase(Tag.TERMINAL.name())) return true;
		return false;
	}

	private Frame wordParameter(int i, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, PARAMETER, WORD).
			addFrame(NAME, variable.name() + ":word").
			addFrame(WORDS, renderWord(variable));
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private void addDefaultInfo(int i, Variable variable, Frame frame) {
		frame.addFrame(MULTIPLE, variable.isMultiple()).
			addFrame(POSITION, i).
			addFrame(ANNOTATIONS, getFlags(variable)).
			addFrame(CONTRACT, calculateContract(variable));
		if (variable.defaultValues() != null && !variable.defaultValues().isEmpty())
			frame.addFrame(DEFAULT, String.join(",", getStrings(variable)));
	}

	private List<String> getStrings(Variable variable) {
		return variable.allowedValues().stream().map(Object::toString).collect(Collectors.toList());
	}

	private String calculateContract(Variable variable) {
		if (variable.contract() == null) return "";
		if (variable.type().equals(Primitives.NATIVE)) return asNativeContract(variable);
		if (variable.type().equals(Primitives.MEASURE)) return asMeasureContract(variable);
		return variable.contract() + Variable.NATIVE_SEPARATOR + Variable.NATIVE_SEPARATOR + generatedLanguage;
	}

	private String asNativeContract(Variable variable) {
		return variable.contract();
	}

	private String asMeasureContract(Variable variable) {
		List<String> allowedMetrics = metrics.get(variable.contract());
		if (allowedMetrics == null) return variable.contract();
		return variable.contract() + Arrays.toString(allowedMetrics.toArray(new String[allowedMetrics.size()]));
	}

	private Frame referenceParameter(int i, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, PARAMETER, REFERENCE).
			addFrame(NAME, variable.name()).
			addFrame(TYPES, renderReference((VariableReference) variable));
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private Frame primitiveParameter(int i, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, PARAMETER).
			addFrame(NAME, variable.name()).
			addFrame(DEFINITION, variable.type());
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private void addParameter(Frame frame, Allow.Parameter parameter, int position, String type) {
		if (Primitives.WORD.equals(parameter.type()))
			frame.addFrame(type, wordParameter(parameter, position, type));
		else if (parameter instanceof ReferenceParameterAllow)
			frame.addFrame(type, referenceParameter((ReferenceParameterAllow) parameter, position, type));
		else frame.addFrame(type, primitiveParameter(parameter, position, type));
	}


	private Frame wordParameter(Allow.Parameter parameter, int position, String type) {
		Frame frame = new Frame().addTypes(type, PARAMETER, WORD).
			addFrame(NAME, parameter.name() + ":" + WORD).
			addFrame(WORDS, parameter.allowedValues());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private Frame referenceParameter(ReferenceParameterAllow parameter, int position, String type) {
		Frame frame = new Frame().addTypes(type, PARAMETER, REFERENCE).
			addFrame(NAME, parameter.name());
		for (String allowedType : parameter.allowedValues())
			frame.addFrame(TYPES, allowedType);
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private Frame primitiveParameter(Allow.Parameter parameter, int position, String type) {
		Frame frame = new Frame().addTypes(type, PARAMETER).
			addFrame(NAME, parameter.name()).
			addFrame(DEFINITION, parameter.type());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private void addDefaultInfo(Allow.Parameter parameter, Frame frame, int position) {
		frame.addFrame(MULTIPLE, parameter.multiple()).
			addFrame(POSITION, position).
			addFrame(ANNOTATIONS, getFlags(parameter)).
			addFrame(CONTRACT, parameter.contract());
	}

	private String[] getFlags(Variable variable) {
		List<String> flags = variable.flags().stream().map(Tag::name).collect(Collectors.toList());
		return flags.toArray(new String[flags.size()]);
	}

	private String[] getFlags(Allow.Parameter variable) {
		List<String> flags = new ArrayList<>();
		for (String tag : variable.flags())
			if (tag.equalsIgnoreCase(Tag.TERMINAL.name())) flags.add(TERMINAL_INSTANCE.name());
			else flags.add(tag);
		return flags.toArray(new String[flags.size()]);
	}

	private String[] renderWord(Variable variable) {
		return variable.allowedValues().toArray(new String[variable.allowedValues().size()]);
	}

	private String[] renderReference(VariableReference reference) {
		Node node = reference.getDestiny();
		if (node == null) return new String[0];
		Set<String> types = collectTypes(node);
		return types.toArray(new String[types.size()]);
	}

	private Set<String> collectTypes(Node node) {
		Set<String> set = new HashSet<>();
		if (!node.isAbstract()) set.add(node.qualifiedName());
		for (Node child : node.children())
			set.addAll(collectTypes(child));
		return set;
	}

}
