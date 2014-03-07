package monet.::projectName::.intellij.structureview;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.TextEditorBasedStructureViewModel;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::StructureViewModel extends TextEditorBasedStructureViewModel implements StructureViewModel.ElementInfoProvider {

	private final ::projectProperName::FileImpl ::projectName::File;


	public ::projectProperName::StructureViewModel(::projectProperName::FileImpl root) {
		super(root);
		::projectName::File = root;
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
		return element instanceof ::projectProperName::FileImpl;
	}

	@NotNull
	@Override
	public StructureViewTreeElement getRoot() {
		return new ::projectProperName::FileStructureViewElement(::projectName::File);
	}

	protected PsiFile getPsiFile() {
		return ::projectName::File;
	}

	@NotNull
	protected Class[] getSuitableClasses() {
		return new Class[]{Definition.class};
	}

}
