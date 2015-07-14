package tara.intellij.lang.psi;

import java.util.List;

public interface NodeContainer extends tara.language.model.NodeContainer, TaraPsiElement {

	List<? extends Node> components();

	String getQualifiedName();

	String type();

	<T extends tara.language.model.Node> void add(T... nodes);

	<T extends tara.language.model.Node> void add(int pos, T... nodes);

	Node components(String name);

	<T extends tara.language.model.Node> boolean contains(T node);

	<T extends tara.language.model.Node> boolean remove(T node);

	void moveToTheTop();

	List<? extends Node> siblings();

	List<? extends Variable> variables();

	<T extends tara.language.model.Variable> void add(T... variables);

	<T extends tara.language.model.Variable> void add(int pos, T... variables);

	NodeContainer container();

	<T extends tara.language.model.NodeContainer> void container(T container);

	String qualifiedName();

	String doc();

	void addDoc(String doc);
}
