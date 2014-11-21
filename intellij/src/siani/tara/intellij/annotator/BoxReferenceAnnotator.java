package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;

public class BoxReferenceAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof TaraHeaderReference && element.getParent() instanceof TaraBox)
			isWellPlaced((TaraHeaderReference) element);
		else if (element instanceof TaraBoxFile)
			checkBoxExistence((TaraBoxFile) element);
	}

	private void checkBoxExistence(TaraBoxFile file) {
		if (file.getBoxReference() == null)
			holder.createErrorAnnotation(file.getNode(), MessageProvider.message("box.reference.error.message"));
	}

	private void isWellPlaced(TaraHeaderReference reference) {
		VirtualFile file = ReferenceManager.resolvePath(reference.getIdentifierList());
		if (!reference.getContainingFile().getVirtualFile().equals(file)) {
			holder.createErrorAnnotation(reference.getNode(), MessageProvider.message("box.reference.error.message"));
		}
	}
}
