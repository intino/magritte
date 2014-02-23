package monet.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import monet.tara.intellij.metamodel.psi.IConcept;
import monet.tara.intellij.metamodel.psi.TaraComponent;
import monet.tara.intellij.metamodel.psi.TaraConcept;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TaraStructureViewElement implements StructureViewTreeElement {

	private final IConcept concept;
	private String myPresentableName;

	public TaraStructureViewElement(IConcept taraConcept) {
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
		if (concept instanceof TaraConcept) {
			List<TaraComponent> components = TaraUtil.getChildrenOf(concept);
			if (components!= null && !components.isEmpty()) {
				List<TreeElement> treeElements = new ArrayList<>(components.size());
				for (IConcept component : components)
					treeElements.add(new TaraStructureViewElement(component));
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
