package monet.tara.metamodel.structureview;

import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraStructureViewFactory implements PsiStructureViewFactory {
	@Nullable
	@Override
	public com.intellij.ide.structureView.StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
		return new TreeBasedStructureViewBuilder() {
			@NotNull
			@Override
			public com.intellij.ide.structureView.StructureViewModel createStructureViewModel() {
				return new TaraStructureViewModel(psiFile);
			}
		};
	}
}