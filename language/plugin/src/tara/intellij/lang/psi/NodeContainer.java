package tara.intellij.lang.psi;

import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public interface NodeContainer extends tara.language.model.NodeContainer, TaraPsiElement {

	String qualifiedName();

	String type();

	List<Variable> variables();

	default Node container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	@NotNull
	default List<Node> components() {
		return unmodifiableList(TaraUtil.getComponentsOf(this));
	}

	default Node component(String name) {
		for (Node node : components()) if (name.equals(node.name())) return node;
		return null;
	}

	default List<Node> siblings() {
		return null;
	}

	default <T extends tara.language.model.Node> boolean contains(T node) {
		return components().contains(node);
	}

	default <T extends tara.language.model.NodeContainer> void container(T container) {
	}

	default String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}

	@Override
	default String doc() {
		return null;
	}

}
