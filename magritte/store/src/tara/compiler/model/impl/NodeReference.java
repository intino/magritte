package tara.compiler.model.impl;

import tara.compiler.model.*;

import java.util.*;

import static java.util.Collections.unmodifiableList;

public class NodeReference implements Node {

	private NodeContainer container;
	private NodeImpl destiny;
	private String reference;
	private String file;
	private int line;
	private String doc;
	private List<Tag> flags = new ArrayList<>();
	private List<Tag> annotations = new ArrayList<>();
	private Set<String> allowedFacets = new HashSet<>();

	private List<String> imports = new ArrayList<>();
	private boolean has;

	public NodeReference(String reference) {
		this.reference = reference;
	}

	public NodeReference(NodeImpl destiny) {
		this.destiny = destiny;
		reference = destiny.getQualifiedName();
	}

	public String getReference() {
		return reference;
	}

	public NodeImpl getDestiny() {
		return destiny;
	}

	public void setDestiny(NodeImpl destiny) {
		this.destiny = destiny;
	}

	@Override
	public String getName() {
		return destiny.getName();
	}

	@Override
	public void setName(String name) {

	}

	@Override
	public String getFile() {
		return file;
	}

	@Override
	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String getLanguage() {
		return null;
	}

	@Override
	public void setLanguage(String language) {
	}

	@Override
	public int getLine() {
		return line;
	}

	@Override
	public void setLine(int line) {
		this.line = line;
	}

	@Override
	public String getDoc() {
		return doc;
	}

	@Override
	public void addDoc(String doc) {
		this.doc = doc;
	}

	@Override
	public boolean isSub() {
		return false;
	}

	public boolean isHas() {
		return has;
	}

	public void setHas(boolean has) {
		this.has = has;
	}

	@Override
	public boolean isMain() {
		return false;
	}

	@Override
	public List<Node> getSubNodes() {
		return unmodifiableList(destiny.getSubNodes());
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
		return destiny.isTerminal() || flags.contains(Tag.TERMINAL);
	}

	@Override
	public boolean isFacet() {
		return destiny.isFacet() || flags.contains(Tag.FACET);
	}

	@Override
	public boolean isAbstract() {
		return destiny.isAbstract() || flags.contains(Tag.ABSTRACT);
	}

	@Override
	public boolean isRequired() {
		return flags.contains(Tag.REQUIRED);
	}

	@Override
	public boolean isSingle() {
		return flags.contains(Tag.SINGLE);
	}

	@Override
	public boolean isNamed() {
		return destiny.isNamed() || flags.contains(Tag.NAMED);
	}

	@Override
	public boolean isFeature() {
		return destiny.isFeature() || flags.contains(Tag.FEATURE);
	}

	@Override
	public boolean isFinal() {
		return destiny.isFinal() || flags.contains(Tag.FINAL);
	}

	@Override
	public boolean isFeatureInstance() {
		return destiny.isFeatureInstance() || flags.contains(Tag.FEATURE);
	}

	@Override
	public boolean isTerminalInstance() {
		return destiny.isTerminalInstance() || flags.contains(Tag.TERMINAL_INSTANCE);
	}

	@Override
	public boolean intoSingle() {
		return flags.contains(Tag.SINGLE);
	}

	@Override
	public boolean intoRequired() {
		return flags.contains(Tag.REQUIRED);
	}

	@Override
	public String getPlate() {
		return destiny.getPlate();
	}

	@Override
	public void setPlate(String address) {
	}

	@Override
	public List<Tag> getAnnotations() {
		List<Tag> tags = new ArrayList<>(destiny.getAnnotations());
		annotations.stream().filter(flag -> !tags.contains(flag)).forEach(tags::add);
		return unmodifiableList(tags);
	}

	@Override
	public List<Tag> getFlags() {
		List<Tag> tags = new ArrayList<>(destiny.getFlags());
		flags.stream().filter(flag -> !tags.contains(flag)).forEach(tags::add);
		return unmodifiableList(tags);
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

	@Override
	public Node getParent() {
		return null;
	}

	@Override
	public String getParentName() {
		return null;
	}

	@Override
	public boolean isAnonymous() {
		return destiny.isAnonymous();
	}

	@Override
	public String getQualifiedName() {
		return getNodeQualifiedName() + "." + destiny.getName();
	}

	private String getNodeQualifiedName() {
		NodeContainer nodeContainer = container;
		while (!(nodeContainer instanceof Node)) nodeContainer = nodeContainer.getContainer();
		return nodeContainer.getQualifiedName();
	}

	@Override
	public String getType() {
		return destiny.getType();
	}

	@Override
	public void setType(String type) {

	}

	@Override
	public String getFullType() {
		if (container instanceof Node)
			return ((Node) container).getFullType() + "." + getType();
		else return "";
	}

	@Override
	public void setFullType(String type) {

	}

	@Override
	public Node resolve() {
		return this;
	}

	@Override
	public List<Parameter> getParameters() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addParameter(String name, int position, String extension, Object... values) {

	}

	@Override
	public void addParameter(int position, String extension, Object... values) {

	}

	@Override
	public List<Node> getNodeSiblings() {
		return unmodifiableList(container.getIncludedNodes()); //TODO Remove me
	}

	@Override
	public List<Node> getIncludedNodes() {
		return unmodifiableList(destiny.getIncludedNodes());
	}

	@Override
	public void addIncludedNodes(Node... nodes) {
	}

	@Override
	public void addIncludedNodes(int pos, Node... nodes) {

	}

	@Override
	public Node getInclude(String name) {
		for (Node include : destiny.getIncludedNodes())
			if (name.equals(include.getName()))
				return include;
		return null;
	}

	@Override
	public boolean contains(Node nodeContainer) {
		return false;
	}

	@Override
	public boolean remove(Node node) {
		return false;
	}

	@Override
	public void moveToTheTop() {

	}

	@Override
	public List<Variable> getVariables() {
		return unmodifiableList(destiny.getVariables());
	}

	@Override
	public void addVariables(Variable... variables) {

	}

	@Override
	public void addVariables(int pos, Variable... variables) {

	}

	@Override
	public List<NodeReference> getInnerNodeReferences() {
		return unmodifiableList(destiny.getInnerNodeReferences());
	}

	@Override
	public List<Node> getChildren() {
		return unmodifiableList(destiny.getChildren());
	}

	@Override
	public void addChild(Node node) {

	}

	@Override
	public List<Facet> getFacets() {
		return unmodifiableList(destiny.getFacets());
	}

	@Override
	public Collection<String> getAllowedFacets() {
		return Collections.unmodifiableCollection(allowedFacets);
	}

	@Override
	public void addAllowedFacets(String... facet) {
		Collections.addAll(allowedFacets, facet);
	}

	@Override
	public void addFacets(Facet... facets) {

	}

	@Override
	public List<FacetTarget> getFacetTargets() {
		return unmodifiableList(destiny.getFacetTargets());
	}

	@Override
	public void addFacetTargets(FacetTarget... targets) {

	}

	@Override
	public String toString() {
		return destiny != null ? getQualifiedName() : reference;
	}
}
