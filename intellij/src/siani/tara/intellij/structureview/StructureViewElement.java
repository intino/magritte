package siani.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
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
			Collection<Concept> concepts = TaraUtil.getInnerConceptsOf(concept);
			if (!concepts.isEmpty()) {
				List<TreeElement> treeElements = new ArrayList<>(concepts.size());
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
				if (myPresentableName == null) return concept.getName() == null ? "Anonymous" : concept.getName();
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
