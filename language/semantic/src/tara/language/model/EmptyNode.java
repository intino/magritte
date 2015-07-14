package tara.language.model;

import java.util.Collection;
import java.util.List;

public class EmptyNode implements Node {

	@Override
	public String name() {
		return null;
	}

	@Override
	public void name(String name) {

	}

	@Override
	public String language() {
		return null;
	}

	@Override
	public void language(String language) {

	}

	@Override
	public String doc() {
		return null;
	}

	@Override
	public void addDoc(String doc) {

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
	public boolean isFeatureInstance() {
		return false;
	}

	@Override
	public boolean isFinal() {
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

	@Override
	public String plate() {
		return null;
	}

	@Override
	public void plate(String address) {

	}

	@Override
	public List<Tag> annotations() {
		return null;
	}

	@Override
	public List<Tag> flags() {
		return null;
	}

	@Override
	public void addAnnotations(Tag... annotations) {

	}

	@Override
	public void addFlags(Tag... flags) {

	}

	@Override
	public void addImports(Collection<String> imports) {

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
	public List<Node> components() {
		return null;
	}

	@Override
	public String type() {
		return null;
	}

	@Override
	public void add(Node... nodes) {

	}

	@Override
	public void add(int pos, Node... nodes) {

	}

	@Override
	public Node components(String name) {
		return null;
	}

	@Override
	public boolean contains(Node node) {
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
	public List<Node> siblings() {
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
	public NodeContainer container() {
		return null;
	}

	@Override
	public void container(NodeContainer container) {

	}

	@Override
	public String qualifiedName() {
		return null;
	}

	@Override
	public List<String> types() {
		return null;
	}

	@Override
	public List<String> secondaryTypes() {
		return null;
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

	@Override
	public boolean isReference() {
		return false;
	}

	@Override
	public List<Node> getReferenceComponents() {
		return null;
	}

	@Override
	public Node destinyOfReference() {
		return null;
	}

	@Override
	public List<Node> children() {
		return null;
	}

	@Override
	public void addChild(Node node) {

	}

	@Override
	public List<Facet> facets() {
		return null;
	}

	@Override
	public Collection<String> allowedFacets() {
		return null;
	}

	@Override
	public void addAllowedFacets(String... facet) {

	}

	@Override
	public void addFacets(Facet... facets) {

	}

	@Override
	public List<FacetTarget> facetTargets() {
		return null;
	}

	@Override
	public void addFacetTargets(FacetTarget... targets) {

	}

	@Override
	public String file() {
		return null;
	}

	@Override
	public void file(String file) {

	}

	@Override
	public int line() {
		return 0;
	}

	@Override
	public void line(int line) {

	}

	@Override
	public List<Parameter> parameters() {
		return null;
	}

	@Override
	public void addParameter(String name, int position, String extension, Object... values) {

	}

	@Override
	public void addParameter(int position, String extension, Object... values) {

	}
}
