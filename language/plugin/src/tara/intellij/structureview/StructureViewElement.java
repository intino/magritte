package tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.impl.TaraUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StructureViewElement implements StructureViewTreeElement {

	private final Node node;
	private String myPresentableName;

	public StructureViewElement(Node taraNode) {
		this.node = taraNode;
	}

	@Override
	public Object getValue() {
		return node;
	}

	public void navigate(boolean requestFocus) {
		node.navigate(requestFocus);
	}

	public boolean canNavigate() {
		return node.canNavigate();
	}

	public boolean canNavigateToSource() {
		return node.canNavigateToSource();
	}


	@NotNull
	@Override
	public TreeElement[] getChildren() {
		if (node != null) {
			Collection<Node> nodes = TaraUtil.getInnerNodesOf(node);
			if (!nodes.isEmpty()) {
				List<TreeElement> treeElements = new ArrayList<>(nodes.size());
				treeElements.addAll(nodes.stream().map(StructureViewElement::new).collect(Collectors.toList()));
				return treeElements.toArray(new TreeElement[treeElements.size()]);
			}
		}
		return EMPTY_ARRAY;
	}

	@NotNull
	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			public String getPresentableText() {
				if (myPresentableName == null) return node.getName() == null ? "Anonymous" : node.getName();
				else return myPresentableName;
			}

			public String getLocationString() {
				return null;
			}

			public Icon getIcon(boolean open) {
				return node.getIcon(0);
			}
		};
	}

}
