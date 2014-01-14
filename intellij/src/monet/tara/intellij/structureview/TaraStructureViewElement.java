package monet.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.metamodel.file.TaraFile;
import monet.tara.intellij.psi.TaraConcept;

import java.util.ArrayList;
import java.util.List;

public class TaraStructureViewElement implements StructureViewTreeElement, SortableTreeElement {

	private PsiElement element;

	public TaraStructureViewElement(PsiElement element) {
		this.element = element;
	}

	@Override
	public Object getValue() {
		return element;
	}

	@Override
	public void navigate(boolean requestFocus) {
		if (element instanceof NavigationItem)
			((NavigationItem) element).navigate(requestFocus);
	}

	@Override
	public boolean canNavigate() {
		return element instanceof NavigationItem &&
			((NavigationItem) element).canNavigate();
	}

	@Override
	public boolean canNavigateToSource() {
		return element instanceof NavigationItem && ((NavigationItem) element).canNavigateToSource();
	}

	@Override
	public String getAlphaSortKey() {
		return element instanceof PsiNamedElement ? ((PsiNamedElement) element).getName() : null;
	}

	@Override
	public ItemPresentation getPresentation() {
		return element instanceof NavigationItem ? ((NavigationItem) element).getPresentation() : null;
	}

	@Override
	public TreeElement[] getChildren() {
		if (element instanceof TaraFile) {
			TaraConcept[] concepts = PsiTreeUtil.getChildrenOfType(element, TaraConcept.class);
			List<TreeElement> treeElements = new ArrayList<>(concepts.length);
			for (TaraConcept concept : concepts)
				treeElements.add(new TaraStructureViewElement(concept));
			return treeElements.toArray(new TreeElement[treeElements.size()]);
		}
		return EMPTY_ARRAY;
	}
}
