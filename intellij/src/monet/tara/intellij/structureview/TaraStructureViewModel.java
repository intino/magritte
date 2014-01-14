package monet.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import monet.tara.intellij.metamodel.file.TaraFile;
import org.jetbrains.annotations.NotNull;

public class TaraStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {

	public TaraStructureViewModel(PsiFile psiFile) {
		super(psiFile, new TaraStructureViewElement(psiFile));
	}

	@NotNull
	public Sorter[] getSorters() {
		return new Sorter[]{ Sorter.ALPHA_SORTER };
	}


	@Override
	public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
		return false;
	}

	@Override
	public boolean isAlwaysLeaf(StructureViewTreeElement element) {
		return element instanceof TaraFile;
	}

}