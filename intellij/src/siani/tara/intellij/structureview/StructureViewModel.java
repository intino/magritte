package siani.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.TextEditorBasedStructureViewModel;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import org.jetbrains.annotations.NotNull;

public class StructureViewModel extends TextEditorBasedStructureViewModel implements com.intellij.ide.structureView.StructureViewModel.ElementInfoProvider {

	private final TaraBoxFileImpl taraFile;


	public StructureViewModel(TaraBoxFileImpl root) {
		super(root);
		taraFile = root;
	}

	@NotNull
	public Sorter[] getSorters() {
		return new Sorter[]{Sorter.ALPHA_SORTER};
	}


	@Override
	public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
		return false;
	}

	@Override
	public boolean isAlwaysLeaf(StructureViewTreeElement element) {
		return element instanceof TaraBoxFileImpl;
	}

	@NotNull
	@Override
	public StructureViewTreeElement getRoot() {
		return new FileStructureViewElement(taraFile);
	}

	protected PsiFile getPsiFile() {
		return taraFile;
	}

	@NotNull
	protected Class[] getSuitableClasses() {
		return new Class[]{Concept.class};
	}

}
