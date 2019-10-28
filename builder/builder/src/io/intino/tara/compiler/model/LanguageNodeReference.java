package io.intino.tara.compiler.model;


import io.intino.tara.lang.model.*;

import java.util.Collections;
import java.util.List;

public class LanguageNodeReference implements Node {

	private List<String> types;
	private String qualifiedName;

	public LanguageNodeReference(List<String> types, String qualifiedName) {
		this.types = types;
		this.qualifiedName = qualifiedName;
	}

	@Override
	public String name() {
		final String[] names = qualifiedName.split("\\.");
		return names[names.length - 1];
	}

	@Override
	public String qualifiedName() {
		return this.qualifiedName;
	}

	@Override
	public String file() {
		return null;
	}

	@Override
	public List<Parameter> parameters() {
		return null;
	}

	@Override
	public void name(String name) {
	}

	@Override
	public Node container() {
		return null;
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
	public boolean isMetaAspect() {
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
		return true;
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

	}

	@Override
	public void addFlags(Tag... flags) {

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
	public List<String> types() {
		return this.types;
	}

	@Override
	public List<String> secondaryTypes() {
		return Collections.emptyList();
	}

	@Override
	public void type(String type) {

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
		return true;
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
	public List<Aspect> appliedAspects() {
		return Collections.emptyList();
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
	public List<Rule> rulesOf(Node component) {
		return Collections.emptyList();
	}

	@Override
	public <T extends Node> boolean contains(T node) {
		return false;
	}

	@Override
	public List<String> uses() {
		return Collections.emptyList();
	}

	@Override
	public String doc() {
		return null;
	}
}
