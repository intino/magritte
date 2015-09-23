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

	LanguageParameterAdapter(Language language, Map<String, List<String>> metrics) {
		this.language = language;
		this.metrics = metrics;
	}

	void addParameter(Frame frame, int i, Variable variable, String relation) {
		if (Primitives.WORD.equals(variable.type()))
			frame.addFrame(relation, wordParameter(i, variable, relation));
		else if (variable instanceof VariableReference)
			frame.addFrame(relation, referenceParameter(i, variable, relation));
		else frame.addFrame(relation, primitiveParameter(i, variable, relation));
	}

	int addTerminalParameters(Node node, Frame requires) {
		int index = 0;
		Collection<Allow> allows = language.allows(node.type());
		if (allows == null) return 0;
		for (Allow allow : allows)
			if (allow instanceof Allow.Parameter && isTerminal((Allow.Parameter) allow) && !isRedefined((Allow.Parameter) allow, node.variables())) {
				Allow.Parameter parameter = (Allow.Parameter) allow;
				addParameter(requires, parameter, index);
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
	}

	private String calculateContract(Variable variable) {
		if (variable.contract() == null) return "";
		if (!variable.type().equals(Primitives.MEASURE))
			return variable.contract();
		List<String> strings = metrics.get(variable.contract());
		if (strings == null) return variable.contract();
		return variable.contract() + Arrays.toString(strings.toArray(new String[strings.size()]));
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
			addFrame(TYPE, variable.type());
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private void addParameter(Frame frame, Allow.Parameter parameter, int position) {
		if (Primitives.WORD.equals(parameter.type()))
			frame.addFrame(REQUIRE, wordParameter(parameter, position));
		else if (parameter instanceof ReferenceParameterAllow)
			frame.addFrame(REQUIRE, referenceParameter((ReferenceParameterAllow) parameter, position));
		else frame.addFrame(REQUIRE, primitiveParameter(parameter, position));
	}

	private Frame wordParameter(Allow.Parameter parameter, int position) {
		Frame frame = new Frame().addTypes(REQUIRE, PARAMETER, WORD).
			addFrame(NAME, parameter.name() + ":" + WORD).
			addFrame(WORDS, parameter.allowedValues());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private Frame referenceParameter(ReferenceParameterAllow parameter, int position) {
		Frame frame = new Frame().addTypes(REQUIRE, PARAMETER, REFERENCE).
			addFrame(NAME, parameter.name());
		for (String allowedType : parameter.allowedValues())

			frame.addFrame(TYPES, allowedType);
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private Frame primitiveParameter(Allow.Parameter parameter, int position) {
		Frame frame = new Frame().addTypes(REQUIRE, PARAMETER).
			addFrame(NAME, parameter.name()).
			addFrame(TYPE, parameter.type());
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
		List<String> types = node.children().stream().map(Node::qualifiedName).collect(Collectors.toList());
		if (!node.isAbstract()) types.add(node.qualifiedName());
		return types.toArray(new String[types.size()]);
	}

}
