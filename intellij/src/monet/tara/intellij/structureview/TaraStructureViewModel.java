package monet.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.TextEditorBasedStructureViewModel;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import monet.tara.intellij.metamodel.psi.IConcept;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.NotNull;

public class TaraStructureViewModel extends TextEditorBasedStructureViewModel implements StructureViewModel.ElementInfoProvider {

	private final TaraFileImpl taraFile;


	public TaraStructureViewModel(TaraFileImpl root) {
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
		return element instanceof TaraFileImpl;
	}

	@NotNull
	@Override
	public StructureViewTreeElement getRoot() {
		return new TaraFileStructureViewElement(taraFile);
	}

	protected PsiFile getPsiFile() {
		return taraFile;
	}

	@NotNull
	protected Class[] getSuitableClasses() {
		return new Class[]{IConcept.class};
	}

}
