package tara.compiler.model;

import tara.language.model.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;
import static tara.language.model.Tag.*;

public class NodeImpl implements Node {

	private String file;
	private int line;
	private NodeContainer container;
	private List<String> uses = new ArrayList<>();
	private String type;
	private String doc;
	private boolean sub;
	private List<Node> components = new ArrayList<>();
	private List<Tag> flags = new ArrayList<>();
	private List<Tag> annotations = new ArrayList<>();
	private String plate;
	private String name;
	private String parentName;
	private Node parent;
	private List<Parameter> parameters = new ArrayList<>();
	private List<Variable> variables = new ArrayList<>();
	private Set<String> allowedFacets = new HashSet<>();
	private List<Facet> facets = new ArrayList<>();
	private List<FacetTarget> facetTargets = new ArrayList<>();
	private String language;
	private List<Node> children = new ArrayList<>();


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
	public boolean isMain() {
		return flags.contains(MAIN);
	}

	@Override
	public List<Node> subs() {
		List<Node> nodes = new ArrayList<>();
		children().stream().filter(Node::isSub).forEach(children -> {
			nodes.add(children);
			nodes.addAll(children.subs());
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
		return flags.contains(TERMINAL);
	}

	@Override
	public boolean isPrototype() {
		return flags.contains(PROTOTYPE);
	}

	@Override
	public boolean isFacet() {
		return flags.contains(FACET);
	}

	@Override
	public boolean isRequired() {
		return flags.contains(REQUIRED);
	}

	@Override
	public boolean isAbstract() {
		return flags.contains(ABSTRACT);
	}

	@Override
	public boolean isSingle() {
		return flags.contains(SINGLE);
	}

	@Override
	public boolean isNamed() {
		return flags.contains(NAMED);
	}

	@Override
	public boolean isFeature() {
		return flags.contains(FEATURE);
	}

	@Override
	public boolean isFinal() {
		return flags.contains(FINAL);
	}

	@Override
	public boolean isEnclosed() {
		return flags.contains(ENCLOSED);
	}

	@Override
	public boolean isFeatureInstance() {
		return flags.contains(FEATURE_INSTANCE);
	}

	@Override
	public boolean isTerminalInstance() {
		return flags.contains(TERMINAL_INSTANCE);
	}

	@Override
	public boolean intoSingle() {
		return annotations.contains(SINGLE);
	}

	@Override
	public boolean intoRequired() {
		return annotations.contains(REQUIRED);
	}

	@Override
	public String plate() {
		return plate;
	}

	@Override
	public void plate(String plate) {
		this.plate = plate;
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
		Collections.addAll(this.annotations, annotations);
	}

	@Override
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
		return name == null || name.isEmpty();
	}

	@Override
	public String qualifiedName() {
		String containerQN = container.qualifiedName();
		return isInFacet() ? "" : (containerQN.isEmpty() ? "" : containerQN + ".") + (name == null ? "[" + ANNONYMOUS + shortType() + "]" : name);
	}

	private String shortType() {
		return type.contains(".") ? type.substring(type.lastIndexOf(".") + 1) : type;
	}

	private boolean isInFacet() {
		NodeContainer context = this.container;
		while (context != null && (!(context instanceof Facet) || !(context instanceof FacetTarget)))
			context = context.container();
		return context != null;
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
		return unmodifiableList(types); //TODO Add language types
	}

	public List<String> secondaryTypes() {
		Set<String> types = facets().stream().map(Facet::type).collect(Collectors.toSet());
		if (parent != null) types.addAll(parent.types());
		return unmodifiableList(new ArrayList(types));
	}

	public void type(String type) {
		this.type = type;
	}

	public String setType() {
		return type;
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
	public void addParameter(String name, int position, String extension, Object... values) {
		ParameterImpl parameter = new ParameterImpl(name, position, extension, values);
		parameter.file(file);
		parameter.line(line);
		parameter.owner(this);
		parameters.add(parameter);
	}

	@Override
	public void addParameter(int position, String extension, Object... values) {
		addParameter("", position, extension, values);
	}

	@Override
	public List<Node> siblings() {
		ArrayList<Node> siblings = new ArrayList<>();
		siblings.addAll(container().components());
		siblings.remove(this);
		return unmodifiableList(siblings);
	}

	@Override
	public List<Node> components() {
		return unmodifiableList(components);
	}

	@Override
	public void add(Node... nodes) {
		Collections.addAll(components, nodes);
	}

	@Override
	public void add(int pos, Node... nodes) {
		this.components.addAll(pos, Arrays.asList(nodes));
	}

	@Override
	public Node component(String name) {
		for (Node include : components)
			if (name.equals(include.name()))
				return include;
		return null;
	}

	@Override
	public boolean contains(Node nodeContainer) {
		return nodeContainer != null && components.contains(nodeContainer);
	}

	@Override
	public boolean remove(Node node) {
		return node != null && components.remove(node);
	}

	@Override
	public void moveToTheTop() {
		Model model = searchModel();
		if (model.contains(this)) return;
		replaceForReference();
		this.container(model);
		model.add(this);
	}

	private void replaceForReference() {
		NodeContainer nodeContainer = this.container();
		NodeReference nodeReference = new NodeReference(this);
		nodeReference.container(nodeContainer);
		nodeReference.file(this.file);
		nodeReference.setHas(true);
		nodeContainer.add(nodeReference);
		nodeContainer.remove(this);
	}

	private Model searchModel() {
		NodeContainer node = this;
		while (node != null && !(node instanceof Model))
			node = node.container();
		return (Model) node;
	}

	@Override
	public List<Variable> variables() {
		return unmodifiableList(variables);
	}

	@Override
	public void add(Variable... variables) {
		Collections.addAll(this.variables, variables);
	}

	@Override
	public void add(int pos, Variable... variables) {
		this.variables.addAll(pos, Arrays.asList(variables));
	}

	@Override
	public List<Node> referenceComponents() {
		List<NodeReference> collect = components.stream().filter(include -> include instanceof NodeReference).map(include -> (NodeReference) include).collect(Collectors.toList());
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
		ArrayList<String> objects = new ArrayList<>();
		objects.addAll(allowedFacets);
		return unmodifiableList(objects);
	}

	@Override
	public void addAllowedFacets(String... facet) {
		Collections.addAll(allowedFacets, facet);
	}

	@Override
	public void addFacets(Facet... facets) {
		Collections.addAll(this.facets, facets);
	}

	@Override
	public List<FacetTarget> facetTargets() {
		return unmodifiableList(facetTargets);
	}

	@Override
	public void addFacetTargets(FacetTarget... targets) {
		Collections.addAll(this.facetTargets, targets);
	}

	@Override
	public String toString() {
		return qualifiedName() + "@" + type;
	}
}