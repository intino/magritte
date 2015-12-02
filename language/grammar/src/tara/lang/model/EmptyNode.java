package tara.lang.model;

import tara.lang.model.rules.CompositionRule;
import tara.lang.model.rules.Size;

import java.util.Collections;
import java.util.List;

public class EmptyNode implements Node {

	@Override
	public String name() {
		return null;
	}

	@Override
	public void name(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String language() {
		return null;
	}

	@Override
	public void language(String language) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String doc() {
		return null;
	}

	@Override
	public void addDoc(String doc) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSub() {
		return false;
	}

	@Override
	public boolean isComponent() {
		return false;
	}

	@Override
	public List<Node> subs() {
		return Collections.emptyList();
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
	public boolean isEnclosed() {
		return false;
	}

	@Override
	public boolean isTerminal() {
		return false;
	}

	@Override
	public boolean isPrototype() {
		return false;
	}

	@Override
	public boolean isDeclaration() {
		return false;
	}

	@Override
	public boolean intoComponent() {
		return false;
	}

	@Override
	public String anchor() {
		return null;
	}

	@Override
	public void anchor(String plate) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Tag> annotations() {
		return Collections.emptyList();
	}

	@Override
	public List<Tag> flags() {
		return Collections.emptyList();
	}

	@Override
	public void addAnnotations(Tag... annotations) {
		throw new UnsupportedOperationException();
	}

	public void addFlags(List<Tag> flags) {
		throw new UnsupportedOperationException();
	}

	public void addFlag(Tag flag) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addUses(List<String> imports) {
		throw new UnsupportedOperationException();
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
	public String simpleType() {
		return null;
	}

	@Override
	public List<Node> components() {
		return Collections.emptyList();
	}

	@Override
	public String type() {
		return null;
	}

	@Override
	public void add(Node node, CompositionRule size) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int pos, Node node, CompositionRule size) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Node component(String name) {
		return null;
	}

	@Override
	public Size ruleOf(Node component) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Node node) {
		return false;
	}

	@Override
	public void remove(Node node) {
	}

	@Override
	public List<Node> siblings() {
		return Collections.emptyList();
	}

	@Override
	public List<Variable> variables() {
		return Collections.emptyList();
	}

	@Override
	public void add(Variable... variables) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int pos, Variable... variables) {
		throw new UnsupportedOperationException();
	}

	@Override
	public NodeContainer container() {
		return null;
	}

	@Override
	public List<String> uses() {
		return Collections.emptyList();
	}

	@Override
	public void container(NodeContainer container) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String qualifiedName() {
		return null;
	}

	@Override
	public String qualifiedNameCleaned() {
		return null;
	}

	@Override
	public List<String> types() {
		return Collections.emptyList();
	}

	@Override
	public List<String> secondaryTypes() {
		return Collections.emptyList();
	}

	@Override
	public void type(String type) {
		throw new UnsupportedOperationException();
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
	public List<Node> referenceComponents() {
		return Collections.emptyList();
	}

	@Override
	public Node destinyOfReference() {
		return null;
	}

	@Override
	public List<Node> children() {
		return Collections.emptyList();
	}

	@Override
	public void addChild(Node node) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Facet> facets() {
		return Collections.emptyList();
	}

	@Override
	public List<String> allowedFacets() {
		return Collections.emptyList();
	}

	@Override
	public void addAllowedFacets(String... facet) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addFacets(Facet... facets) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<FacetTarget> facetTargets() {
		return Collections.emptyList();
	}

	@Override
	public void addFacetTargets(FacetTarget... targets) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String file() {
		return null;
	}

	@Override
	public void file(String file) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int line() {
		return 0;
	}

	@Override
	public void line(int line) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Parameter> parameters() {
		return Collections.emptyList();
	}

	@Override
	public void addParameter(String name, int position, String extension, int line, int column, List<Object> values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addParameter(int position, String extension, int line, int column, List<Object> values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "empty";
	}
}
