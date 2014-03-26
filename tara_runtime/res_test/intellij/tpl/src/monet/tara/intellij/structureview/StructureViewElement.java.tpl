package monet.::projectName::.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StructureViewElement implements StructureViewTreeElement {

	private final Definition definition;
	private String myPresentableName;

	public StructureViewElement(Definition ::projectName::Definition) {
		this.definition = ::projectName::Definition;
	}

	\@Override
	public Object getValue() {
		return definition;
	}

	public void navigate(boolean requestFocus) {
		definition.navigate(requestFocus);
	}

	public boolean canNavigate() {
		return definition.canNavigate();
	}

	public boolean canNavigateToSource() {
		return definition.canNavigateToSource();
	}


	\@Override
	public TreeElement[] getChildren() {
		if (definition != null) {
			Definition[] definitions = ::projectProperName::Util.getChildrenOf(definition);
			if (definitions != null && !(definitions.length == 0)) {
				List<TreeElement> treeElements = new ArrayList<>(definitions.length);
				for (Definition definition \: definitions)
					treeElements.add(new StructureViewElement(definition));
				return treeElements.toArray(new TreeElement[treeElements.size()]);
			}
		}
		return EMPTY_ARRAY;
	}

	\@NotNull
	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			public String getPresentableText() {
				if (myPresentableName == null) return definition.getName() == null ? "Anonymous" \: definition.getName();
				else return myPresentableName;
			}

			public String getLocationString() {
				return null;
			}

			public Icon getIcon(boolean open) {
				return definition.getIcon(0);
			}
		};
	}

	public void setPresentableName(final String presentableName) {
		myPresentableName = presentableName;
	}
}
