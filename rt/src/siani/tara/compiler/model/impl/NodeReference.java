package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.*;

import java.util.*;

import static siani.tara.compiler.model.Tag.*;

public class NodeReference extends Element implements Node {

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
	public void setDoc(String doc) {
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
	public boolean isRoot() {
		return false;
	}

	@Override
	public Collection<Node> getSubNodes() {
		return destiny.getSubNodes();
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
		return destiny.isTerminal() || flags.contains(TERMINAL);
	}

	@Override
	public boolean isFacet() {
		return destiny.isFacet() || flags.contains(FACET);
	}

	@Override
	public boolean isAddressed() {
		return destiny.isAddressed() || flags.contains(ADDRESSED);
	}

	@Override
	public boolean isAbstract() {
		return destiny.isAbstract() || flags.contains(ABSTRACT);
	}

	@Override
	public boolean isRequired() {
		return destiny.isRequired() || flags.contains(REQUIRED);
	}

	@Override
	public boolean isSingle() {
		return destiny.isSingle() || flags.contains(SINGLE);
	}

	@Override
	public boolean isNamed() {
		return destiny.isNamed() || flags.contains(NAMED);
	}

	@Override
	public boolean isAggregated() {
		return destiny.isAggregated() || flags.contains(Tag.AGGREGATED);
	}

	@Override
	public boolean isAssociated() {
		return destiny.isAssociated() || flags.contains(Tag.ASSOCIATED);
	}

	@Override
	public boolean isProperty() {
		return destiny.isProperty() || flags.contains(PROPERTY);
	}

	@Override
	public boolean isPropertyInstance() {
		return destiny.isPropertyInstance() || flags.contains(PROPERTY_INSTANCE);
	}

	@Override
	public boolean isComponent() {
		return destiny.isComponent() || flags.contains(COMPONENT);
	}

	@Override
	public boolean isTerminalInstance() {
		return destiny.isTerminalInstance() || flags.contains(TERMINAL_INSTANCE);
	}

	@Override
	public Long getAddress() {
		return destiny.getAddress();
	}

	@Override
	public void setAddress(Long address) {
	}

	@Override
	public Collection<Tag> getAnnotations() {
		List<Tag> tags = new ArrayList<>(destiny.getAnnotations());
		tags.addAll(this.annotations);
		return tags;
	}

	@Override
	public Collection<Tag> getFlags() {
		List<Tag> tags = new ArrayList<>(destiny.getFlags());
		tags.addAll(flags);
		return tags;
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
		return container.getQualifiedName() + "." + destiny.getName();
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
	public Collection<Parameter> getParameters() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addParameter(String name, int position, String extension, Object... values) {

	}

	@Override
	public void addParameter(int position, String extension, Object... values) {

	}

	@Override
	public Collection<Node> getNodeSiblings() {
		return container.getIncludedNodes(); //TODO Remove me
	}

	@Override
	public Collection<Node> getIncludedNodes() {
		return destiny.getIncludedNodes();
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
	public Collection<Variable> getVariables() {
		return destiny.getVariables();
	}

	@Override
	public void addVariables(Variable... variables) {

	}

	@Override
	public void addVariables(int pos, Variable... variables) {

	}

	@Override
	public Collection<NodeReference> getInnerNodeReferences() {
		return destiny.getInnerNodeReferences();
	}

	@Override
	public Collection<Node> getChildren() {
		return destiny.getChildren();
	}

	@Override
	public void addChild(Node node) {

	}

	@Override
	public Collection<Facet> getFacets() {
		return destiny.getFacets();
	}

	@Override
	public Collection<String> getAllowedFacets() {
		return allowedFacets;
	}

	@Override
	public void addAllowedFacets(String... facet) {
		Collections.addAll(allowedFacets, facet);
	}

	@Override
	public void addFacets(Facet... facets) {

	}

	@Override
	public Collection<FacetTarget> getFacetTargets() {
		return destiny.getFacetTargets();
	}

	@Override
	public void addFacetTargets(FacetTarget... targets) {

	}

	@Override
	public String toString() {
		return destiny != null ? getQualifiedName() : reference;
	}
}