package monet.::projectName::.intellij.structureview;

import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructureViewFactory implements PsiStructureViewFactory {
	\@Nullable
	\@Override
	public com.intellij.ide.structureView.StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
		return new TreeBasedStructureViewBuilder() {
			\@NotNull
			public com.intellij.ide.structureView.StructureViewModel createStructureViewModel(Editor editor) {
				return new StructureViewModel((::projectProperName::FileImpl) psiFile);
			}
		};
	}
}