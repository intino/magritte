package monet.tara.intellij.structureview;

import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructureViewFactory implements PsiStructureViewFactory {
	@Nullable
	@Override
	public com.intellij.ide.structureView.StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
		return new TreeBasedStructureViewBuilder() {
			@NotNull
			public com.intellij.ide.structureView.StructureViewModel createStructureViewModel(Editor editor) {
				return new StructureViewModel((TaraFileImpl) psiFile);
			}
		};
	}
}