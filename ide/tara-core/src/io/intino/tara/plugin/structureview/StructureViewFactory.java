package io.intino.tara.plugin.structureview;

import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.intino.tara.plugin.lang.psi.impl.TaraModelImpl;

public class StructureViewFactory implements PsiStructureViewFactory {
	@Nullable
	@Override
	public com.intellij.ide.structureView.StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
		return new TreeBasedStructureViewBuilder() {
			@NotNull
			public com.intellij.ide.structureView.StructureViewModel createStructureViewModel(Editor editor) {
				return new StructureViewModel((TaraModelImpl) psiFile);
			}
		};
	}
}