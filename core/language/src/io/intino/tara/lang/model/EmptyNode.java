package io.intino.tara.lang.model;

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
	public String languageName() {
		return null;
	}

	@Override
	public void languageName(String language) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String doc() {
		return null;
	}

	@Override
	public void doc(String doc) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSub() {
		return false;
	}


	@Override
	public List<Node> subs() {
		return Collections.emptyList();
	}

	@Override
	public boolean isAspect() {
		return false;
	}

	@Override
	public boolean is(Tag tag) {
		return false;
	}

	@Override
	public boolean into(Tag tag) {
		return false;
	}

	@Override
	public boolean isAbstract() {
		return false;
	}

	@Override
	public boolean isTerminal() {
		return false;
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
	public void add(Node node, List<Rule> size) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Node> component(String name) {
		return Collections.emptyList();
	}

	@Override
	public List<Rule> rulesOf(Node component) {
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
	public Node container() {
		return null;
	}

	@Override
	public List<String> uses() {
		return Collections.emptyList();
	}

	@Override
	public void container(Node container) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String qualifiedName() {
		return null;
	}

	@Override
	public String layerQualifiedName() {
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
	public void stashNodeName(String name) {

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
	public List<Aspect> appliedAspects() {
		return Collections.emptyList();
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
	public String toString() {
		return "empty";
	}
}
