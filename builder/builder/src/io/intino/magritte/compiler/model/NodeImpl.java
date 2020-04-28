package io.intino.magritte.compiler.model;

import io.intino.magritte.dsl.ProteoConstants;
import io.intino.magritte.lang.model.Aspect;
import io.intino.magritte.lang.model.*;

import java.util.*;
import java.util.stream.Collectors;

import static io.intino.magritte.compiler.codegeneration.Format.firstUpperCase;
import static io.intino.magritte.lang.model.Tag.*;
import static java.util.Collections.addAll;
import static java.util.Collections.unmodifiableList;

public class NodeImpl implements Node {
	private String file;
	private int line;
	private Node container;
	private List<String> uses = new ArrayList<>();
	private String type;
	private String doc;
	private boolean sub;
	private Map<Node, List<Rule>> components = new LinkedHashMap<>();
	private List<Tag> flags = new ArrayList<>();
	private List<Tag> annotations = new ArrayList<>();
	private String name;
	private String parentName;
	private Node parent;
	private List<Parameter> parameters = new ArrayList<>();
	private List<Variable> variables = new ArrayList<>();
	private List<Aspect> aspects = new ArrayList<>();
	private List<AspectConstraint> aspectConstraints;
	private String language;
	private String uid;
	private List<Node> children = new ArrayList<>();
	private List<String> context = new ArrayList<>();
	private boolean anonymous = true;
	private boolean dirty;
	private boolean virtual;
	private String stashNodeName;
	private String hashCode;

	@Override
	public String name() {
		return name;
	}

	@Override
	public void name(String name) {
		this.name = name;
		this.anonymous = false;
	}

	@Override
	public String file() {
		return file;
	}

	public void file(String file) {
		this.file = file;
	}

	@Override
	public String languageName() {
		return language;
	}

	@Override
	public void languageName(String language) {
		this.language = language;
	}

	@Override
	public int line() {
		return line;
	}

	@Override
	public void line(int line) {
		this.line = line;
	}

	@Override
	public String doc() {
		return doc;
	}

	public void doc(String doc) {
		this.doc = this.doc == null ? doc : this.doc + "\\n" + doc.trim();
	}

	@Override
	public boolean isSub() {
		return sub;
	}

	public void setSub(boolean sub) {
		this.sub = sub;
	}

	@Override
	public List<Node> subs() {
		List<Node> nodes = new ArrayList<>();
		children().stream().filter(Node::isSub).forEach(c -> {
			nodes.add(c);
			nodes.addAll(c.subs());
		});
		return unmodifiableList(nodes);
	}

	@Override
	public Node container() {
		return container;
	}

	@Override
	public List<String> uses() {
		return uses;
	}

	@Override
	public void container(Node container) {
		this.container = container;
	}

	@Override
	public boolean isTerminal() {
		return flags.contains(Terminal);
	}

	@Override
	public boolean isAbstract() {
		return flags.contains(Abstract) || children().stream().anyMatch(Node::isSub);
	}

	@Override
	public boolean isAspect() {
		return ProteoConstants.ASPECT.equals(type()) || is(Tag.Aspect);
	}

	@Override
	public boolean isMetaAspect() {
		return ProteoConstants.META_ASPECT.equals(type());
	}

	public List<Node.AspectConstraint> aspectConstraints() {
		return Collections.emptyList();
	}

	public void aspectConstraints(List<String> constraints) {
		this.aspectConstraints = constraints.stream().map(AspectConstraint::new).collect(Collectors.toList());
	}

	public boolean is(Tag tag) {
		return flags.contains(tag);
	}

	public boolean into(Tag tag) {
		return annotations.contains(tag);
	}

	@Override
	public List<Tag> annotations() {
		return unmodifiableList(annotations);
	}

	@Override
	public List<Tag> flags() {
		return unmodifiableList(flags);
	}

	@Override
	public void addAnnotations(Tag... annotations) {
		addAll(this.annotations, annotations);
	}


	public void addFlags(Tag... flags) {
		Collections.addAll(this.flags, flags);
	}

	@Override
	public void addUses(List<String> imports) {
		this.uses.addAll(imports);
	}

	@Override
	public Node parent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	@Override
	public String parentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Override
	public boolean isAnonymous() {
		return anonymous;
	}

	public NodeImpl isAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
		return this;
	}

	@Override
	public String qualifiedName() {
		String containerQN = container.qualifiedName();
		String name = is(Instance) || isAnonymous() ? name() : firstUpperCase().format(name()).toString();
		return (containerQN.isEmpty() ? "" : containerQN + ".") + (name == null ? "[" + ANONYMOUS + shortType() + "]" : name);
	}

	public String layerQualifiedName() {
		String containerQn = container instanceof Model ? "" : ((NodeImpl) container).layerQualifiedName();
		String name = is(Instance) || isAnonymous() ? name() : firstUpperCase().format(name()).toString();
		return (containerQn.isEmpty() ? "" : containerQn + "$") + (name == null ? newUUID() : name);
	}

	public String layerQn() {
		String containerQn = container instanceof Model ? "" : ((NodeImpl) container).layerQn();
		String name = is(Instance) || isAnonymous() ? name() : firstUpperCase().format(name()).toString();
		if (name != null && is(Decorable)) name = "Abstract" + name;
		return (containerQn.isEmpty() ? "" : containerQn + "$") + (name == null ? newUUID() : name);
	}

	private String shortType() {
		return type.contains(".") ? type.substring(type.lastIndexOf(".") + 1) : type;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public List<String> types() {
		List<String> types = new ArrayList<>();
		types.add(type());
		types.addAll(secondaryTypes());
		return unmodifiableList(types);
	}

	@Override
	public List<String> secondaryTypes() {
		Set<String> types = appliedAspects().stream().map(io.intino.magritte.lang.model.Aspect::type).collect(Collectors.toSet());
		if (parent != null) types.addAll(parent.types());
		return List.copyOf(types);
	}

	@Override
	public void type(String type) {
		this.type = type;
	}

	@Override
	public void stashNodeName(String name) {
		this.stashNodeName = name;
	}

	@Override
	public List<String> metaTypes() {
		return this.context;
	}

	@Override
	public void metaTypes(List<String> context) {
		this.context = new ArrayList<>(context);
	}

	@Override
	public Node resolve() {
		return this;
	}

	@Override
	public boolean isReference() {
		return false;
	}

	@Override
	public List<Parameter> parameters() {
		return Collections.unmodifiableList(parameters);
	}

	@Override
	public void addParameter(String name, String facet, int position, String extension, int line, int column, List<Object> values) {
		ParameterImpl parameter = new ParameterImpl(name, position, extension, values);
		parameter.aspect(facet);
		parameter.file(file);
		parameter.line(line);
		parameter.column(column);
		parameter.owner(this);
		parameters.add(parameter);
	}

	public void add(Parameter parameter) {
		parameters.add(parameter);
	}

	@Override
	public List<Node> siblings() {
		List<Node> siblings = new ArrayList<>(container().components());
		siblings.remove(this);
		return unmodifiableList(siblings);
	}

	@Override
	public List<Node> components() {
		return unmodifiableList(new ArrayList<>(components.keySet()));
	}

	public void add(Node node, List<Rule> rules) {
		this.components.put(node, rules == null ? new ArrayList<>() : new ArrayList<>(rules));
	}

	@Override
	public List<Rule> rulesOf(Node component) {
		return this.components.get(component);
	}

	@Override
	public boolean contains(Node nodeContainer) {
		return nodeContainer != null && components.containsKey(nodeContainer);
	}

	@Override
	public void remove(Node node) {
		if (node != null) components.remove(node);
	}

	@Override
	public List<Variable> variables() {
		return unmodifiableList(variables);
	}

	@Override
	public void add(Variable... variables) {
		addAll(this.variables, variables);
	}

	@Override
	public void add(int pos, Variable... variables) {
		this.variables.addAll(pos, Arrays.asList(variables));
	}

	@Override
	public List<Node> referenceComponents() {
		return components.keySet().stream().filter(include -> include instanceof NodeReference).map(include -> (NodeReference) include).collect(Collectors.toUnmodifiableList());
	}

	@Override
	public Node destinyOfReference() {
		return this;
	}

	@Override
	public List<Node> children() {
		return unmodifiableList(this.children);
	}

	@Override
	public void addChild(Node node) {
		children.add(node);
	}

	@Override
	public List<Aspect> appliedAspects() {
		return unmodifiableList(aspects);
	}

	@Override
	public void applyAspects(io.intino.magritte.lang.model.Aspect... aspects) {
		Collections.addAll(this.aspects, aspects);
	}

	@Override
	public String toString() {
		return type + " " + qualifiedName();
	}

	private String newUUID() {
		if (uid == null) uid = UUID.randomUUID().toString();
		return is(Instance) ? uid : firstUpperCase().format(uid).toString();
	}

	public void absorb(NodeImpl node) {
		this.components.putAll(node.components);
		this.variables.addAll(node.variables);
		this.children.addAll(node.children);
		for (Node child : node.children) ((NodeImpl) child).setParent(this);
		this.annotations.addAll(node.annotations);
		this.flags.addAll(node.flags.stream().filter(t -> !t.equals(Tag.Abstract)).collect(Collectors.toList()));
		this.aspects.addAll(node.aspects);
		this.flags.remove(Tag.Abstract);
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public boolean isVirtual() {
		return virtual;
	}

	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public String getHashCode() {
		return hashCode;
	}

	private static class AspectConstraint implements Node.AspectConstraint, Cloneable {
		private Node node;
		private boolean negated = false;
		private String name;

		AspectConstraint(String name) {
			this.name = name;
		}

		public String name() {
			return this.name;
		}

		public Node node() {
			return this.node;
		}

		public void node(Node node) {
			this.node = node;
		}

		@Override
		public AspectConstraint clone() throws CloneNotSupportedException {
			return (AspectConstraint) super.clone();
		}

		public String toString() {
			return "with " + node().qualifiedName();
		}
	}
}
