package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.*;
import siani.tara.semantic.model.Tag;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;
import static siani.tara.semantic.model.Tag.*;

public class NodeImpl extends Element implements Node {

	private String file;
	private int line;
	private NodeContainer container;
	private List<String> imports = new ArrayList<>();
	private String type;
	private String fullType;
	private String doc;
	private boolean sub;
	private List<Node> includes = new ArrayList<>();
	private List<Tag> flags = new ArrayList<>();
	private List<Tag> annotations = new ArrayList<>();
	private String plate;
	private String name;
	private String parentName;
	private Node parent;
	private List<Parameter> parameters = new ArrayList<>();
	private List<Variable> variables = new ArrayList();
	private Set<String> allowedFacets = new HashSet<>();
	private List<Facet> facets = new ArrayList<>();
	private List<FacetTarget> facetTargets = new ArrayList<>();
	private String language;
	private List<Node> children = new ArrayList<>();


	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String getLanguage() {
		return language;
	}

	@Override
	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public void setSub(boolean sub) {
		this.sub = sub;
	}

	@Override
	public String getDoc() {
		return doc;
	}

	@Override
	public boolean isSub() {
		return sub;
	}

	@Override
	public boolean isRoot() {
		return flags.contains(ROOT);
	}

	@Override
	public List<Node> getSubNodes() {
		List<Node> nodes = new ArrayList<>();
		includes.stream().filter(Node::isSub).forEach(include -> {
			nodes.add(include);
			nodes.addAll(include.getSubNodes());
		});
		return unmodifiableList(nodes);
	}

	@Override
	public NodeContainer getContainer() {
		return container;
	}

	@Override
	public void setContainer(NodeContainer container) {
		this.container = container;
	}

	@Override
	public boolean isTerminal() {
		return flags.contains(TERMINAL);
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
	public boolean isFeatureInstance() {
		return flags.contains(FEATURE_INSTANCE);
	}

	@Override
	public boolean isProperty() {
		return flags.contains(PROPERTY);
	}

	@Override
	public boolean isPropertyInstance() {
		return flags.contains(PROPERTY_INSTANCE);
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
	public String getPlate() {
		return plate;
	}

	@Override
	public void setPlate(String plate) {
		this.plate = plate;
	}

	@Override
	public List<Tag> getAnnotations() {
		return unmodifiableList(annotations);
	}

	@Override
	public List<Tag> getFlags() {
		return unmodifiableList(flags);
	}

	@Override
	public void addAnnotations(String... annotations) {
		for (String annotation : annotations)
			this.annotations.add(Tag.valueOf(annotation.toUpperCase()));
	}

	@Override
	public void addFlags(String... flags) {
		for (String flag : flags)
			this.flags.add(Tag.valueOf(flag.toUpperCase()));
	}

	@Override
	public void addImports(Collection<String> imports) {
		this.imports.addAll(imports);
	}

	public Collection<String> getImports() {
		return this.imports;
	}

	@Override
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	@Override
	public String getParentName() {
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
	public String getQualifiedName() {
		String containerQN = container.getQualifiedName();
		return isInFacet() ? "" : (containerQN.isEmpty() ? "" : containerQN + ".") + (name == null ? "[" + ANNONYMOUS + shortType() + "]" : name);
	}

	private String shortType() {
		return type.contains(".") ? type.substring(type.lastIndexOf(".") + 1) : type;
	}

	private boolean isInFacet() {
		NodeContainer context = this.container;
		while (context != null && (!(context instanceof Facet) || !(context instanceof FacetTarget)))
			context = context.getContainer();
		return context != null;
	}

	@Override
	public String getType() {
		return type;
	}

	public String setType() {
		return type;
	}

	@Override
	public String getFullType() {
		return fullType;
	}

	@Override
	public void setFullType(String fullType) {
		this.type = fullType;
		this.fullType = fullType;
	}

	@Override
	public Node resolve() {
		return this;
	}

	@Override
	public Collection<Parameter> getParameters() {
		return parameters;
	}

	@Override
	public void addParameter(String name, int position, String extension, Object... values) {
		ParameterImpl parameter = new ParameterImpl(name, position, extension, values);
		parameter.setFile(file);
		parameter.setLine(line);
		parameter.setOwner(this);
		parameters.add(parameter);
	}

	@Override
	public void addParameter(int position, String extension, Object... values) {
		addParameter("", position, extension, values);
	}

	@Override
	public Collection<Node> getNodeSiblings() {
		ArrayList<Node> siblings = new ArrayList<>();
		siblings.addAll(getContainer().getIncludedNodes());
		siblings.remove(this);
		return siblings;
	}

	@Override
	public Collection<Node> getIncludedNodes() {
		return includes;
	}

	@Override
	public void addIncludedNodes(Node... nodes) {
		Collections.addAll(includes, nodes);
	}

	@Override
	public void addIncludedNodes(int pos, Node... nodes) {
		this.includes.addAll(pos, Arrays.asList(nodes));
	}

	@Override
	public Node getInclude(String name) {
		for (Node include : includes)
			if (name.equals(include.getName()))
				return include;
		return null;
	}

	@Override
	public boolean contains(Node nodeContainer) {
		return nodeContainer != null && includes.contains(nodeContainer);
	}

	@Override
	public boolean remove(Node node) {
		return node != null && includes.remove(node);
	}

	@Override
	public void moveToTheTop() {
		Model model = searchModel();
		if (model.contains(this)) return;
		replaceForReference();
		this.setContainer(model);
		model.addIncludedNodes(this);
	}

	private void replaceForReference() {
		NodeContainer nodeContainer = this.getContainer();
		NodeReference nodeReference = new NodeReference(this);
		nodeReference.setContainer(nodeContainer);
		nodeReference.setFile(this.file);
		nodeReference.setHas(true);
		nodeContainer.addIncludedNodes(nodeReference);
		nodeContainer.remove(this);
	}

	private Model searchModel() {
		NodeContainer node = this;
		while (node != null && !(node instanceof Model))
			node = node.getContainer();
		return (Model) node;
	}

	@Override
	public Collection<Variable> getVariables() {
		return variables;
	}

	@Override
	public void addVariables(Variable... variables) {
		Collections.addAll(this.variables, variables);
	}

	@Override
	public void addVariables(int pos, Variable... variables) {
		this.variables.addAll(pos, Arrays.asList(variables));
	}

	@Override
	public List<NodeReference> getInnerNodeReferences() {
		List<NodeReference> collect = includes.stream().filter(include -> include instanceof NodeReference).map(include -> (NodeReference) include).collect(Collectors.toList());
		return unmodifiableList(collect);
	}

	@Override
	public List<Node> getChildren() {
		return unmodifiableList(this.children);
	}

	@Override
	public void addChild(Node node) {
		children.add(node);
	}

	@Override
	public List<Facet> getFacets() {
		return unmodifiableList(facets);
	}

	@Override
	public List<String> getAllowedFacets() {
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
	public List<FacetTarget> getFacetTargets() {
		return unmodifiableList(facetTargets);
	}

	@Override
	public void addFacetTargets(FacetTarget... targets) {
		Collections.addAll(this.facetTargets, targets);
	}

	@Override
	public String toString() {
		return getQualifiedName() + "@" + type;
	}
}
