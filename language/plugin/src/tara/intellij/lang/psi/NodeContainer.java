package tara.intellij.lang.psi;

import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public interface NodeContainer extends tara.language.model.NodeContainer, TaraPsiElement {

	String qualifiedName();

	String type();

	List<? extends Variable> variables();

	default Node container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	@NotNull
	default List<Node> components() {
		return unmodifiableList(TaraUtil.getComponentsOf(this));
	}

	default tara.intellij.lang.psi.Node component(String name) {
		for (Node node : components()) {
			if (name.equals(node.name())) return node;
		}
		return null;
	}

	default List<? extends tara.intellij.lang.psi.Node> siblings() {
		return null;
	}

	default <T extends tara.language.model.Node> boolean contains(T node) {
		return components().contains(node);
	}

	default <T extends tara.language.model.NodeContainer> void container(T container) {
	}

	default void moveToTheTop() {
	}


	default void add(tara.language.model.Node... nodes) {
	}

	default <T extends tara.language.model.Node> void add(int pos, T... nodes) {
	}

	default <T extends tara.language.model.Variable> void add(T... variables) {
	}

	default <T extends tara.language.model.Variable> void add(int pos, T... variables) {
	}

	default <T extends tara.language.model.Node> boolean remove(T node) {
		return false;
	}

	@Override
	default String doc() {
		return null;
	}

	@Override
	default void addDoc(String doc) {
	}

}
