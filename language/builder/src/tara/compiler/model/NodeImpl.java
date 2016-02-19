package tara.compiler.model;

import tara.dsl.Proteo;
import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;
import tara.util.WordGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.addAll;
import static java.util.Collections.unmodifiableList;
import static tara.lang.model.Tag.Abstract;
import static tara.lang.model.Tag.Terminal;

public class NodeImpl implements Node {

	private String file;
	private int line;
	private NodeContainer container;
	private List<String> uses = new ArrayList<>();
	private String type;
	private String doc;
	private boolean sub;
	private Map<Node, CompositionRule> components = new LinkedHashMap<>();
	private List<Tag> flags = new ArrayList<>();
	private List<Tag> annotations = new ArrayList<>();
	private String anchor;
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

	private Table table;

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
	public String language() {
		return language;
	}

	@Override
	public void language(String language) {
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

	public void addDoc(String doc) {
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
	public NodeContainer container() {
		return container;
	}

	@Override
	public List<String> uses() {
		return uses;
	}

	@Override
	public void container(NodeContainer container) {
		this.container = container;
	}

	@Override
	public boolean isTerminal() {
		return flags.contains(Terminal);
	}

	@Override
	public boolean isAbstract() {
		return flags.contains(Abstract);
	}

	@Override
	public boolean isFacet() {
		return type().startsWith(Proteo.FACET + ":") || is(Tag.Facet);
	}

	public boolean is(Tag tag) {
		return flags.contains(tag);
	}

	public boolean into(Tag tag) {
		return annotations.contains(tag);
	}

	@Override
	public String anchor() {
		return anchor;
	}

	@Override
	public void anchor(String anchor) {
		this.anchor = anchor;
	}

	public Table table() {
		return table;
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
		return (containerQN.isEmpty() ? "" : containerQN + ".") + (name == null ? "[" + ANNONYMOUS + shortType() + "]" : name + facetName());
	}

	@Override
	public String qualifiedNameCleaned() {
		String containerQN = container.qualifiedNameCleaned();
		return (containerQN.isEmpty() ? "" : containerQN + "$") + (name == null ? getUID() : name + facetName()).replace(":", "");
	}

	private String facetName() {
		return facetTarget != null ? ":" + facetTarget.target() : "";
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
		Set<String> types = facets().stream().map(tara.lang.model.Facet::type).collect(Collectors.toSet());
		if (parent != null) types.addAll(parent.types());
		return unmodifiableList(new ArrayList(types));
	}

	@Override
	public void type(String type) {
		this.type = type;
	}

	public String setType() {
		return type;
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
	public void addParameter(String name, int position, String extension, int line, int column, List<Object> values) {
		ParameterImpl parameter = new ParameterImpl(name, position, extension, values);
		parameter.file(file);
		parameter.line(line);
		parameter.column(column);
		parameter.owner(this);
		parameters.add(parameter);
	}

	@Override
	public void addParameter(int position, String extension, int line, int column, List<Object> values) {
		addParameter("", position, extension, line, column, values);
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

	@Override
	public void add(Node node, CompositionRule compositionRule) {
		this.components.put(node, compositionRule);
	}

	@Override
	public void add(int pos, Node node, CompositionRule compositionRule) {
		this.components.put(node, compositionRule);
	}

	@Override
	public CompositionRule ruleOf(Node component) {
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
	public String tableName() {
		return table != null ? table.name() : "";
	}

	@Override
	public void table(String table, List<String> parameters) {
		this.table = new Table(table, parameters);
	}

	@Override
	public String toString() {
		return type + " " + qualifiedName();
	}

	public String getUID() {
		return uid == null ? (uid = WordGenerator.generate()) : uid;
	}

	public void absorb(NodeImpl node) {
		this.components.putAll(node.components);
		this.variables.addAll(node.variables);
		this.children.addAll(node.children);
		this.annotations.addAll(node.annotations);
		this.flags.addAll(node.flags.stream().filter(t -> !t.equals(Tag.Abstract)).collect(Collectors.toList()));
		this.facetTarget = node.facetTarget;
		this.facets.addAll(node.facets);
		this.flags.remove(Tag.Abstract);
	}
}
