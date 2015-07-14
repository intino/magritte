package tara.compiler.model.impl;

import tara.semantic.model.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

public class Model implements Node {

	private String name;
	private String file;
	private String language;
	private Map<String, List<SimpleEntry<String, String>>> metrics = new HashMap<>();
	private int level;
	private List<Node> includes = new ArrayList<>();

	public Model(String file) {
		this.file = file;
	}


	public String name() {
		return name;
	}

	public void name(String name) {
		this.name = name;
	}

	@Override
	public String file() {
		return file;
	}

	@Override
	public void file(String file) {
		this.file = file;
	}

	@Override
	public int line() {
		return 0;
	}

	@Override
	public void line(int line) {

	}

	@Override
	public String doc() {
		return null;
	}

	@Override
	public void addDoc(String doc) {

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
	public boolean isMain() {
		return false;
	}

	@Override
	public List<Node> subs() {
		return null;
	}

	@Override
	public Node container() {
		return null;
	}

	@Override
	public void container(NodeContainer container) {
	}

	@Override
	public boolean isFacet() {
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
	public boolean isFeature() {
		return false;
	}

	@Override
	public boolean isFinal() {
		return false;
	}

	@Override
	public boolean isFeatureInstance() {
		return false;
	}

	@Override
	public boolean isTerminal() {
		return false;
	}

	@Override
	public boolean isTerminalInstance() {
		return false;
	}

	@Override
	public boolean intoSingle() {
		return false;
	}

	@Override
	public boolean intoRequired() {
		return false;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String plate() {
		return null;
	}

	@Override
	public void plate(String address) {

	}

	@Override
	public List<Tag> annotations() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<Tag> flags() {
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
	public Node parent() {
		return null;
	}

	@Override
	public String parentName() {
		return null;
	}

	@Override
	public boolean isAnonymous() {
		return false;
	}

	@Override
	public String qualifiedName() {
		return "";
	}

	@Override
	public String type() {
		return "";
	}

	@Override
	public void type(String type) {

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

	public List<Parameter> parameters() {
		return null;
	}

	@Override
	public void addParameter(String name, int position, String extension, Object... values) {

	}

	@Override
	public void addParameter(int position, String extension, Object... values) {

	}

	@Override
	public List<Node> siblings() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<Node> components() {
		return Collections.unmodifiableList(includes);
	}

	@Override
	public String type() {
		return null;
	}

	@Override
	public void add(Node... nodes) {
		Collections.addAll(includes, nodes);
	}

	@Override
	public void add(int pos, Node... nodes) {
		includes.addAll(pos, Arrays.asList(nodes));
	}

	@Override
	public Node components(String name) {
		return null;
	}

	@Override
	public List<Variable> variables() {
		return null;
	}

	@Override
	public void add(Variable... variables) {
	}

	@Override
	public void add(int pos, Variable... variables) {
	}

	@Override
	public List<Node> getReferenceComponents() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<Node> children() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addChild(Node node) {

	}

	@Override
	public List<Facet> facets() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<String> allowedFacets() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addAllowedFacets(String... facet) {

	}

	@Override
	public void addFacets(Facet... facets) {
	}

	@Override
	public List<FacetTarget> facetTargets() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addFacetTargets(FacetTarget... targets) {

	}

	public String language() {
		return language;
	}

	@Override
	public void language(String language) {

	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
