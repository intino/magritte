package io.intino.magritte.compiler.model;


import io.intino.magritte.lang.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.intino.magritte.lang.model.Tag.Abstract;
import static io.intino.magritte.lang.model.Tag.Terminal;
import static java.util.Collections.unmodifiableList;

public class NodeReference implements Node {
	private Node container;
	private NodeImpl destination;
	private String reference;
	private String file;
	private int line;
	private String doc;
	private List<Tag> flags = new ArrayList<>();
	private List<Tag> annotations = new ArrayList<>();
	private List<String> uses = new ArrayList<>();
	private boolean has;
	private String language;

	public NodeReference(String reference) {
		this.reference = reference;
	}

	public NodeReference(NodeImpl destination) {
		this.destination = destination;
		reference = destination.qualifiedName();
	}

	public String getReference() {
		return reference;
	}

	public NodeImpl destination() {
		return destination;
	}

	public void destination(NodeImpl destination) {
		this.destination = destination;
	}

	public String layerQualifiedName() {
		return ((NodeImpl) container).layerQualifiedName() + "$" + destination.name();
	}

	@Override
	public String name() {
		return destination != null ? destination.name() : "";
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
	public String languageName() {
		return language;
	}

	@Override
	public void languageName(String language) {
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
	public void doc(String doc) {
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
	public List<Node> subs() {
		return unmodifiableList(destination.subs());
	}

	@Override
	public Node container() {
		return container;
	}

	@Override
	public List<String> uses() {
		return uses;
	}

	@Override
	public void container(Node container) {
		this.container = container;
	}

	@Override
	public boolean isTerminal() {
		return is(Terminal);
	}

	@Override
	public boolean isAbstract() {
		return is(Abstract);
	}

	@Override
	public boolean isAspect() {
		return destination.isAspect();
	}

	@Override
	public boolean isMetaAspect() {
		return destination.isMetaAspect();
	}

	@Override
	public boolean is(Tag tag) {
		return destination.is(tag) || flags().contains(tag);
	}

	@Override
	public boolean into(Tag tag) {
		return destination.into(tag) || annotations().contains(tag);
	}

	@Override
	public List<Tag> annotations() {
		List<Tag> tags = new ArrayList<>(destination.annotations());
		annotations.stream().filter(flag -> !tags.contains(flag)).forEach(tags::add);
		return unmodifiableList(tags);
	}

	@Override
	public List<Tag> flags() {
		List<Tag> tags = new ArrayList<>();
		flags.stream().filter(flag -> !tags.contains(flag)).forEach(tags::add);
		if (isHas()) tags.addAll(destination.flags());
		return unmodifiableList(tags);
	}

	@Override
	public void addAnnotations(Tag... annotations) {
		Collections.addAll(this.annotations, annotations);
	}

	public void addFlags(Tag... flags) {
		Collections.addAll(this.flags, flags);
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
		return destination.isAnonymous();
	}

	@Override
	public String qualifiedName() {
		return container.qualifiedName() + "." + destination.name();
	}

	@Override
	public String type() {
		return destination.type();
	}

	@Override
	public List<String> types() {
		return destination.types();
	}

	@Override
	public List<String> secondaryTypes() {
		return destination.secondaryTypes();
	}


	@Override
	public void type(String type) {
	}

	@Override
	public void stashNodeName(String name) {

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
		return unmodifiableList(destination.components());
	}

	@Override
	public List<Rule> rulesOf(Node component) {
		return destination.rulesOf(component);
	}

	@Override
	public boolean contains(Node nodeContainer) {
		return false;
	}

	@Override
	public List<Variable> variables() {
		return unmodifiableList(destination.variables());
	}

	@Override
	public List<Node> referenceComponents() {
		return unmodifiableList(destination.referenceComponents());
	}

	@Override
	public Node destinyOfReference() {
		return destination;
	}

	@Override
	public List<Node> children() {
		return unmodifiableList(destination.children());
	}

	@Override
	public List<Aspect> appliedAspects() {
		return unmodifiableList(destination.appliedAspects());
	}

	@Override
	public String toString() {
		return destination != null ? qualifiedName() : reference;
	}
}
