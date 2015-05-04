package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.*;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class Model extends Element implements Node {

	private String name;
	private String file;
	private String language;

	private Map<String, List<SimpleEntry<String, String>>> metrics = new HashMap<>();

	private boolean terminal;
	private List<Node> includes = new ArrayList<>();

	public Model(String file) {
		this.file = file;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public int getLine() {
		return 0;
	}

	@Override
	public void setLine(int line) {

	}

	@Override
	public String getDoc() {
		return null;
	}

	@Override
	public void setDoc(String doc) {

	}

	public Map<String, List<SimpleEntry<String, String>>> getMetrics() {
		return metrics;
	}

	public void addMetrics(Map<String, List<SimpleEntry<String, String>>> metrics) {
		this.metrics.putAll(metrics);
	}

	@Override
	public boolean isSub() {
		return false;
	}

	@Override
	public boolean isRoot() {
		return false;
	}

	@Override
	public Collection<Node> getSubNodes() {
		return null;
	}

	@Override
	public Node getContainer() {
		return null;
	}

	@Override
	public void setContainer(NodeContainer container) {
	}

	@Override
	public boolean isFacet() {
		return false;
	}

	@Override
	public boolean isAddressed() {
		return false;
	}

	@Override
	public boolean isAbstract() {
		return false;
	}

	@Override
	public boolean isRequired() {
		return false;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public boolean isNamed() {
		return false;
	}

	@Override
	public boolean isAggregated() {
		return false;
	}

	@Override
	public boolean isAssociated() {
		return false;
	}

	@Override
	public boolean isProperty() {
		return false;
	}

	@Override
	public boolean isPropertyInstance() {
		return false;
	}

	@Override
	public boolean isComponent() {
		return false;
	}

	@Override
	public boolean isTerminalInstance() {
		return false;
	}

	public boolean isTerminal() {
		return terminal;
	}

	@Override
	public Long getAddress() {
		return null;
	}

	@Override
	public void setAddress(Long address) {

	}

	@Override
	public List<Tag> getAnnotations() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public Collection<Tag> getFlags() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addAnnotations(String... annotations) {

	}

	@Override
	public void addFlags(String... flags) {

	}

	@Override
	public void addImports(Collection<String> imports) {

	}

	@Override
	public boolean contains(Node nodeContainer) {
		return includes.contains(nodeContainer);
	}

	@Override
	public boolean remove(Node node) {
		return node != null && includes.remove(node);
	}

	@Override
	public void moveToTheTop() {

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
		return false;
	}

	@Override
	public String getQualifiedName() {
		return "";
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public void setType(String type) {

	}

	@Override
	public String getFullType() {
		return null;
	}

	@Override
	public void setFullType(String type) {

	}

	@Override
	public Node resolve() {
		return null;
	}

	@Override
	public Collection<Parameter> getParameters() {
		return null;
	}

	@Override
	public void addParameter(String name, int position, String extension, Object... values) {

	}

	@Override
	public void addParameter(int position, String extension, Object... values) {

	}

	@Override
	public Collection<Node> getNodeSiblings() {
		return Collections.EMPTY_LIST;
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
		includes.addAll(pos, Arrays.asList(nodes));
	}

	@Override
	public Node getInclude(String name) {
		return null;
	}

	@Override
	public Collection<Variable> getVariables() {
		return null;
	}

	@Override
	public void addVariables(Variable... variables) {

	}

	@Override
	public void addVariables(int pos, Variable... variables) {

	}

	@Override
	public Collection<NodeReference> getInnerNodeReferences() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public Collection<Node> getChildren() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addChild(Node node) {

	}

	@Override
	public Collection<Facet> getFacets() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public Collection<String> getAllowedFacets() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addAllowedFacets(String... facet) {

	}

	@Override
	public void addFacets(Facet... facets) {
	}

	@Override
	public Collection<FacetTarget> getFacetTargets() {
		return null;
	}

	@Override
	public void addFacetTargets(FacetTarget... targets) {

	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}
}