package monet.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StructureViewElement implements StructureViewTreeElement {

	private final Concept concept;
	private String myPresentableName;

	public StructureViewElement(Concept taraConcept) {
		this.concept = taraConcept;
	}

	@Override
	public Object getValue() {
		return concept;
	}

	public void navigate(boolean requestFocus) {
		concept.navigate(requestFocus);
	}

	public boolean canNavigate() {
		return concept.canNavigate();
	}

	public boolean canNavigateToSource() {
		return concept.canNavigateToSource();
	}


	@Override
	public TreeElement[] getChildren() {
		if (concept != null) {
			Concept[] concepts = TaraUtil.getChildrenOf(concept);
			if (concepts != null && !(concepts.length == 0)) {
				List<TreeElement> treeElements = new ArrayList<>(concepts.length);
				for (Concept concept : concepts)
					treeElements.add(new StructureViewElement(concept));
				return treeElements.toArray(new TreeElement[treeElements.size()]);
			}
		}
		return EMPTY_ARRAY;
	}

	@NotNull
	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			public String getPresentableText() {
				if (myPresentableName == null) return (concept.getName() == null ? "Anonymous" : concept.getName());
				else return myPresentableName;
			}

			public String getLocationString() {
				return null;
			}

			public Icon getIcon(boolean open) {
				return concept.getIcon(0);
			}
		};
	}

	public void setPresentableName(final String presentableName) {
		myPresentableName = presentableName;
	}
}
