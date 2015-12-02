package tara.compiler.model;


import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;

import java.util.*;

import static java.util.Collections.unmodifiableList;
import static tara.lang.model.Tag.*;

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
	private List<String> uses = new ArrayList<>();
	private boolean has;
	private String language;

	public NodeReference(String reference) {
		this.reference = reference;
	}

	public NodeReference(NodeImpl destiny) {
		this.destiny = destiny;
		reference = destiny.qualifiedName();
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
	public String name() {
		return destiny != null ? destiny.name() : "";
	}

	@Override
	public void name(String name) {

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
	public boolean isComponent() {
		return false;
	}

	@Override
	public List<Node> subs() {
		return unmodifiableList(destiny.subs());
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
		return destiny.isTerminal() || flags.contains(Terminal);
	}

	@Override
	public boolean isPrototype() {
		return destiny.isPrototype() || flags.contains(Prototype);
	}

	@Override
	public boolean isFacet() {
		return destiny.isFacet() || flags.contains(Facet);
	}

	@Override
	public boolean isAbstract() {
		return destiny.isAbstract() || flags.contains(Abstract);
	}

	@Override
	public boolean isNamed() {
		return destiny.isNamed() || flags.contains(Named);
	}

	@Override
	public boolean isFeature() {
		return destiny.isFeature() || flags.contains(Tag.Feature);
	}

	@Override
	public boolean isFinal() {
		return destiny.isFinal() || flags.contains(Tag.Final);
	}

	@Override
	public boolean isEnclosed() {
		return destiny.isEnclosed() || flags.contains(Tag.Enclosed);
	}

	@Override
	public boolean isFeatureInstance() {
		return destiny.isFeatureInstance() || flags.contains(Tag.Feature);
	}

	@Override
	public boolean isDeclaration() {
		return destiny.isDeclaration() || flags.contains(Instance);
	}

	@Override
	public boolean intoComponent() {
		return destiny.intoComponent() || annotations.contains(Component);
	}

	@Override
	public String anchor() {
		return destiny.anchor();
	}

	@Override
	public void anchor(String plate) {
	}

	@Override
	public List<Tag> annotations() {
		List<Tag> tags = new ArrayList<>(destiny.annotations());
		annotations.stream().filter(flag -> !tags.contains(flag)).forEach(tags::add);
		return unmodifiableList(tags);
	}

	@Override
	public List<Tag> flags() {
		List<Tag> tags = new ArrayList<>();
		flags.stream().filter(flag -> !tags.contains(flag)).forEach(tags::add);
		if (isHas()) tags.addAll(destiny.flags());
		return unmodifiableList(tags);
	}

	@Override
	public void addAnnotations(Tag... annotations) {
		Collections.addAll(this.annotations, annotations);
	}

	public void addFlags(List<Tag> flags) {
		this.flags.addAll(flags);
	}

	public void addFlag(Tag flag) {
		this.flags.add(flag);
	}

	@Override
	public void addUses(List<String> uses) {
		this.uses.addAll(uses);
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
		return destiny.isAnonymous();
	}

	@Override
	public String qualifiedName() {
		return getContainerQualifiedName() + "." + destiny.name();
	}

	@Override
	public String qualifiedNameCleaned() {
		return getContainerQualifiedNameCleaned() + "$" + destiny.name();
	}

	private String getContainerQualifiedName() {
		NodeContainer nodeContainer = container;
		while (!(nodeContainer instanceof Node)) nodeContainer = nodeContainer.container();
		return nodeContainer.qualifiedName();
	}

	private String getContainerQualifiedNameCleaned() {
		NodeContainer nodeContainer = container;
		while (!(nodeContainer instanceof Node)) nodeContainer = nodeContainer.container();
		return nodeContainer.qualifiedNameCleaned();
	}

	@Override
	public String type() {
		return destiny.type();
	}

	@Override
	public List<String> types() {
		return destiny.types();
	}

	@Override
	public List<String> secondaryTypes() {
		return destiny.secondaryTypes();
	}


	@Override
	public void type(String type) {
	}

	@Override
	public Node resolve() {
		return this;
	}

	@Override
	public boolean isReference() {
		return true;
	}

	@Override
	public List<Parameter> parameters() {
		return Collections.emptyList();
	}

	@Override
	public List<Node> siblings() {
		final List<Node> components = new ArrayList<>(container.components());
		components.remove(this);
		return unmodifiableList(components);
	}

	@Override
	public List<Node> components() {
		return unmodifiableList(destiny.components());
	}

	@Override
	public Node component(String name) {
		for (Node include : destiny.components())
			if (name.equals(include.name())) return include;
		return null;
	}

	@Override
	public CompositionRule ruleOf(Node component) {
		return destiny.ruleOf(component);
	}

	@Override
	public boolean contains(Node nodeContainer) {
		return false;
	}

	@Override
	public List<Variable> variables() {
		return unmodifiableList(destiny.variables());
	}

	@Override
	public List<Node> referenceComponents() {
		return unmodifiableList(destiny.referenceComponents());
	}

	@Override
	public Node destinyOfReference() {
		return destiny;
	}

	@Override
	public List<Node> children() {
		return unmodifiableList(destiny.children());
	}

	@Override
	public List<Facet> facets() {
		return unmodifiableList(destiny.facets());
	}

	@Override
	public List<String> allowedFacets() {
		return Collections.unmodifiableList(new ArrayList<>(allowedFacets));
	}

	@Override
	public void addAllowedFacets(String... facet) {
		Collections.addAll(allowedFacets, facet);
	}

	@Override
	public List<FacetTarget> facetTargets() {
		return unmodifiableList(destiny.facetTargets());
	}

	@Override
	public String toString() {
		return destiny != null ? qualifiedName() : reference;
	}
}
