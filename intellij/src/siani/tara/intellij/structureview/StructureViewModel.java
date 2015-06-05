package siani.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.TextEditorBasedStructureViewModel;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;

public class StructureViewModel extends TextEditorBasedStructureViewModel implements com.intellij.ide.structureView.StructureViewModel.ElementInfoProvider {

	private final TaraModelImpl taraFile;


	public StructureViewModel(TaraModelImpl root) {
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
		return element instanceof TaraModelImpl;
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
		return new Class[]{Node.class};
	}

}
