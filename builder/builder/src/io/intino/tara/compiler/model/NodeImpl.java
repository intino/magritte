package io.intino.tara.compiler.model;

import io.intino.tara.lang.model.*;
import io.intino.tara.dsl.ProteoConstants;
import io.intino.tara.util.WordGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.addAll;
import static java.util.Collections.unmodifiableList;
import static io.intino.tara.compiler.codegeneration.Format.firstUpperCase;
import static io.intino.tara.lang.model.Tag.*;

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
	private Set<String> allowedFacets = new HashSet<>();
	private List<Facet> facets = new ArrayList<>();
	private FacetTarget facetTarget;
	private String language;
	private String uid;
	private List<Node> children = new ArrayList<>();
	private List<String> context = new ArrayList<>();
	private boolean dirty;
	private boolean virtual;

	@Override
	public String name() {
		return name;
	}

	@Override
	public void name(String name) {
		this.name = name;
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
		return flags.contains(Abstract) || children().stream().filter(Node::isSub).findAny().isPresent();
	}

	@Override
	public boolean isFacet() {
		return type().startsWith(ProteoConstants.FACET + ":") || is(Tag.Facet);
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

	public void addFlags(List<Tag> flags) {
		this.flags.addAll(flags);
	}

	public void addFlag(Tag flag) {
		this.flags.add(flag);
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
		return name == null || name.isEmpty();
	}

	@Override
	public String qualifiedName() {
		String containerQN = container.qualifiedName();
		String name = is(Instance) || isAnonymous() ? name() : firstUpperCase().format(name()).toString();
		return (containerQN.isEmpty() ? "" : containerQN + ".") + (name == null ? "[" + ANONYMOUS + shortType() + "]" : name + (facetTarget != null ? ":" + facetTarget.target().replace(".", ":") : ""));
	}

	@Override
	public String cleanQn() {
		String containerQN = container.cleanQn();
		String name = is(Instance) || isAnonymous() ? name() : firstUpperCase().format(name()).toString();
		return (containerQN.isEmpty() ? "" : containerQN + "$") + (name == null ? getUID() : name + (facetTarget != null ? "#" + facetTarget.targetNode().cleanQn() : ""));
	}

	private String shortType() {
		return type.contains(".") ? type.substring(type.lastIndexOf(".") + 1) : type;
	}

	@Override
	public String type() {
		return type + (type.contains(":") ? "" : facetTargetType());
	}

	private String facetTargetType() {
		FacetTarget target = isSub() ? parent().facetTarget() : facetTarget;
		if (target == null) return "";
		if (target.targetNode() == null) return "";
		final String type = target.targetNode().type();
		return ":" + (type.contains(":") ? type.substring(0, type.indexOf(":")) : type);
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
		Set<String> types = facets().stream().map(io.intino.tara.lang.model.Facet::type).collect(Collectors.toSet());
		if (parent != null) types.addAll(parent.types());
		return unmodifiableList(new ArrayList(types));
	}

	@Override
	public void type(String type) {
		this.type = type;
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
		parameter.facet(facet);
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
		List<Node> siblings = new ArrayList<>();
		siblings.addAll(container().components());
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
		return nodeContainer != null && components.keySet().contains(nodeContainer);
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
		List<NodeReference> collect = components.keySet().stream().filter(include -> include instanceof NodeReference).map(include -> (NodeReference) include).collect(Collectors.toList());
		return unmodifiableList(collect);
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
	public List<Facet> facets() {
		return unmodifiableList(facets);
	}

	@Override
	public List<String> allowedFacets() {
		return unmodifiableList(new ArrayList<>(allowedFacets));
	}

	@Override
	public void addAllowedFacets(String... facet) {
		addAll(allowedFacets, facet);
	}

	@Override
	public void addFacets(Facet... facets) {
		addAll(this.facets, facets);
	}

	@Override
	public FacetTarget facetTarget() {
		return facetTarget;
	}

	@Override
	public void facetTarget(FacetTarget target) {
		this.facetTarget = target;
	}

	@Override
	public String toString() {
		return type + " " + qualifiedName();
	}

	private String getUID() {
		if (uid == null) uid = WordGenerator.generate();
		return is(Instance) ? uid : firstUpperCase().format(uid).toString();
	}

	public void absorb(NodeImpl node) {
		this.components.putAll(node.components);
		this.variables.addAll(node.variables);
		this.children.addAll(node.children);
		this.annotations.addAll(node.annotations);
		this.flags.addAll(node.flags.stream().filter(t -> !t.equals(Tag.Abstract)).collect(Collectors.toList()));
		if (this.facetTarget() == null) this.facetTarget = node.facetTarget;
		this.facets.addAll(node.facets);
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
}
