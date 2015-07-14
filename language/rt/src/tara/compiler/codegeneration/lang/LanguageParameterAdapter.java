package tara.compiler.codegeneration.lang;

import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.VariableReference;
import tara.language.model.Node;
import tara.language.model.Tag;
import tara.language.model.Variable;
import tara.language.semantics.Allow;
import tara.language.semantics.constraints.ReferenceParameterAllow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static tara.language.model.Tag.TERMINAL_INSTANCE;

public class LanguageParameterAdapter implements TemplateTags {
	private final Language language;

	LanguageParameterAdapter(Language language) {
		this.language = language;
	}

	void addParameter(Frame frame, int i, Variable variable, String relation) {
		if (variable.type().equals("word"))
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

	private boolean isRedefined(Allow.Parameter allow, Collection<Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	private boolean isTerminal(Allow.Parameter allow) {
		for (String flag : allow.flags())
			if (flag.equalsIgnoreCase(Tag.TERMINAL.name())) return true;
		return false;
	}

	private Frame wordParameter(int i, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, "parameter", "word").
			addFrame("name", variable.name() + ":word").
			addFrame("words", renderWord(variable));
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private void addDefaultInfo(int i, Variable variable, Frame frame) {
		frame.addFrame("multiple", variable.isMultiple()).
			addFrame("position", i).
			addFrame("annotations", getFlags(variable)).
			addFrame("contract", variable.contract() == null ? "" : variable.contract());
	}

	private Frame referenceParameter(int i, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, "parameter", "reference").
			addFrame("name", variable.name()).
			addFrame("types", renderReference((VariableReference) variable));
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private Frame primitiveParameter(int i, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, "parameter").
			addFrame("name", variable.name()).
			addFrame("type", variable.type());
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private void addParameter(Frame frame, Allow.Parameter parameter, int position) {
		if (parameter.type().equals("word"))
			frame.addFrame(REQUIRE, wordParameter(parameter, position));
		else if (parameter instanceof ReferenceParameterAllow)
			frame.addFrame(REQUIRE, referenceParameter((ReferenceParameterAllow) parameter, position));
		else frame.addFrame(REQUIRE, primitiveParameter(parameter, position));
	}

	private Frame wordParameter(Allow.Parameter parameter, int position) {
		Frame frame = new Frame().addTypes(REQUIRE, "parameter", "word").
			addFrame("name", parameter.name() + ":word").
			addFrame("words", parameter.allowedValues());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private Frame referenceParameter(ReferenceParameterAllow parameter, int position) {
		Frame frame = new Frame().addTypes(REQUIRE, "parameter", "reference").
			addFrame("name", parameter.name());
		for (String allowedType : parameter.allowedValues())

			frame.addFrame("types", allowedType);
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private Frame primitiveParameter(Allow.Parameter parameter, int position) {
		Frame frame = new Frame().addTypes(REQUIRE, "parameter").
			addFrame("name", parameter.name()).
			addFrame("type", parameter.type());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private void addDefaultInfo(Allow.Parameter parameter, Frame frame, int position) {
		frame.addFrame("multiple", parameter.multiple()).
			addFrame("position", position).
			addFrame("annotations", getFlags(parameter)).
			addFrame("contract", parameter.contract());
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
