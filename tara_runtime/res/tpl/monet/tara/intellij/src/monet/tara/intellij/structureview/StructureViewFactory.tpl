package monet.::projectName::.intellij.structureview;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ::projectProperName::StructureViewFactory implements PsiStructureViewFactory {
	@Nullable
	@Override
	public com.intellij.ide.structureView.StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
		return new TreeBasedStructureViewBuilder() {
			@NotNull
			public StructureViewModel createStructureViewModel(Editor editor) {
				return new ::projectProperName::StructureViewModel((::projectProperName::FileImpl) psiFile);
			}
		};
	}
}